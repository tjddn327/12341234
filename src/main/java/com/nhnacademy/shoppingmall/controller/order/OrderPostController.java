package com.nhnacademy.shoppingmall.controller.order;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.model.cart.domain.Cart;
import com.nhnacademy.shoppingmall.model.cart.service.CartService;
import com.nhnacademy.shoppingmall.model.orderDetails.domain.OrderDetail;
import com.nhnacademy.shoppingmall.model.orderDetails.service.OrderDetailService;
import com.nhnacademy.shoppingmall.model.orders.domain.Orders;
import com.nhnacademy.shoppingmall.model.orders.service.OrdersService;
import com.nhnacademy.shoppingmall.model.product.domain.Product;
import com.nhnacademy.shoppingmall.model.product.service.ProductService;
import com.nhnacademy.shoppingmall.model.user.domain.User;
import com.nhnacademy.shoppingmall.model.user.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RequestMapping(method = RequestMapping.Method.POST, value = "/order/submit.do")
@Controller
public class OrderPostController implements BaseController {
    private final OrdersService ordersService;
    private final UserService userService;
    private final CartService cartService;
    private final ProductService productService;
    private final OrderDetailService orderDetailService;

    @Autowired
    public OrderPostController(OrdersService ordersService, UserService userService, CartService cartService, ProductService productService, OrderDetailService orderDetailService) {
        this.ordersService = ordersService;
        this.userService = userService;
        this.cartService = cartService;
        this.productService = productService;
        this.orderDetailService = orderDetailService;
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession(false);
        User user = (User) session.getAttribute("user");

        String shipAddress = req.getParameter("shipAddress");

        List<Cart> cartItems = cartService.getCartItems(user.getUserId());
        if (cartItems.isEmpty()) {
            return "redirect:/index.do";
        }

        int totalPrice = 0;
        for (Cart cartItem : cartItems) {
            Product product = productService.getProduct(cartItem.getPk().getProductId());
            int itemPrice = product.getUnitPrice();
            int quantity = cartItem.getQuantity();
            totalPrice += itemPrice * quantity;
        }

        if (user.getUserPoint() < totalPrice) {
            log.warn("포인트 부족으로 주문실패 : 포인트 = {}, 결재금액 = {}", user.getUserId(), totalPrice);
            return "redirect:/order/form.do?error=point_not_enough";
        }

        Orders newOrder = new Orders(0, user, LocalDateTime.now(), shipAddress, totalPrice);
        ordersService.saveOrder(newOrder);

        for (Cart item : cartItems) {
            Product product = productService.getProduct(item.getPk().getProductId());
            OrderDetail orderDetail = new OrderDetail(0, newOrder, product, item.getQuantity(), product.getUnitPrice());
            orderDetailService.saveOrderDetail(orderDetail);
        }

        user.setUserPoint(user.getUserPoint() - totalPrice);
        userService.updateUser(user);

        for (Cart item : cartItems) {
            cartService.deleteCartItem(user.getUserId(), item.getPk().getProductId());
        }
        log.info("주문 성공: orderId={}, userId={}, totalPrice={}", newOrder.getOrderId(), user.getUserId(), totalPrice);

        return "redirect:/order/complete.do?orderId=" + newOrder.getOrderId();
    }
}