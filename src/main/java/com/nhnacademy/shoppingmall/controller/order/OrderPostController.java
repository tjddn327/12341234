package com.nhnacademy.shoppingmall.controller.order;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.model.cart.domain.Cart;
import com.nhnacademy.shoppingmall.model.cart.service.CartService;
import com.nhnacademy.shoppingmall.model.cart.service.impl.CartServiceImpl;
import com.nhnacademy.shoppingmall.model.orderProduct.domain.OrderProduct;
import com.nhnacademy.shoppingmall.model.orderProduct.repository.impl.OrderProductRepositoryImpl;
import com.nhnacademy.shoppingmall.model.orderProduct.service.OrderProductService;
import com.nhnacademy.shoppingmall.model.orderProduct.service.impl.OrderProductServiceImpl;
import com.nhnacademy.shoppingmall.model.orders.domain.Orders;
import com.nhnacademy.shoppingmall.model.orders.repository.impl.OrdersRepositoryImpl;
import com.nhnacademy.shoppingmall.model.orders.service.OrdersService;
import com.nhnacademy.shoppingmall.model.orders.service.impl.OrdersServiceImpl;
import com.nhnacademy.shoppingmall.model.product.domain.Product;
import com.nhnacademy.shoppingmall.model.product.repository.impl.ProductRepositoryImpl;
import com.nhnacademy.shoppingmall.model.product.service.ProductService;
import com.nhnacademy.shoppingmall.model.product.service.impl.ProductServiceImpl;
import com.nhnacademy.shoppingmall.model.user.domain.User;
import com.nhnacademy.shoppingmall.model.user.repository.impl.UserRepositoryImpl;
import com.nhnacademy.shoppingmall.model.user.service.UserService;
import com.nhnacademy.shoppingmall.model.user.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RequestMapping(method = RequestMapping.Method.POST, value = "/order/submit.do")
public class OrderPostController implements BaseController {
    private final OrdersService ordersService = new OrdersServiceImpl(new OrdersRepositoryImpl());
    private final UserService userService = new UserServiceImpl(new UserRepositoryImpl());
    private final CartService cartService = new CartServiceImpl();
    private final ProductService productService = new ProductServiceImpl(new ProductRepositoryImpl());
    private final OrderProductService orderProductService = new OrderProductServiceImpl(new OrderProductRepositoryImpl());
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession(false);
        User user = (User)session.getAttribute("user");

        String shipAddress =  req.getParameter("shipAddress");

        List<Cart> cartItems = cartService.getCartItems(user.getUserId());
        if(cartItems.isEmpty()) {
            return "redirect:/index.do";
        }

        int totalPrice = 0;
        for (Cart cartItem : cartItems) {
            Product product = productService.getProduct(cartItem.getProductId());
            int itemPrice = product.getUnitPrice();
            int quantity = cartItem.getQuantity();
            totalPrice += itemPrice * quantity;
        }

        if (user.getUserPoint() < totalPrice) {
            log.warn("포인트 부족으로 주문실패 : 포인트 = {}, 결재금액 = {}", user.getUserId(), totalPrice);
            return "redirect:/order/form.do?error=point_not_enough";
        }

        Orders newOrder = new Orders(0, user.getUserId(), LocalDateTime.now(), shipAddress, totalPrice);
        ordersService.saveOrder(newOrder);
        int orderId = newOrder.getOrderId();

        for (Cart item : cartItems) {
            Product product = productService.getProduct(item.getProductId());
            OrderProduct orderProduct = new OrderProduct(orderId, item.getProductId(), item.getQuantity(), product.getUnitPrice());
            orderProductService.saveOrderProduct(orderProduct);
        }

        user.setUserPoint(user.getUserPoint() - totalPrice);
        userService.updateUser(user);

        for (Cart item : cartItems) {
            cartService.deleteCartItem(user.getUserId(), item.getProductId());
        }
        log.info("주문 성공: orderId={}, userId={}, totalPrice={}", orderId, user.getUserId(), totalPrice);

        return "redirect:/order/complete.do?orderId=" + orderId;
    }
}
