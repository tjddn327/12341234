package com.nhnacademy.shoppingmall.controller.order;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.model.cart.domain.Cart;
import com.nhnacademy.shoppingmall.model.cart.domain.CartItemView;
import com.nhnacademy.shoppingmall.model.cart.service.CartService;
import com.nhnacademy.shoppingmall.model.product.domain.Product;
import com.nhnacademy.shoppingmall.model.product.service.ProductService;
import com.nhnacademy.shoppingmall.model.user.domain.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RequestMapping(method = RequestMapping.Method.GET, value = "/order/form.do")
@Controller
public class OrderFormController implements BaseController {

    private final CartService cartService;
    private final ProductService productService;

    @Autowired
    public OrderFormController(CartService cartService, ProductService productService) {
        this.cartService = cartService;
        this.productService = productService;
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession(false);
        if (Objects.isNull(session) || Objects.isNull(session.getAttribute("user"))) {
            return "redirect:/login.do";
        }
        User user = (User) session.getAttribute("user");

        List<Cart> cartItems = cartService.getCartItems(user.getUserId());

        List<CartItemView> cartItemViews = new ArrayList<>();
        int totalAmount = 0;

        for (Cart cart : cartItems) {
            Product product = productService.getProduct(cart.getPk().getProductId());
            if (product != null) {
                int itemTotalPrice = product.getUnitPrice() * cart.getQuantity();
                cartItemViews.add(new CartItemView(
                        product.getProductId(),
                        product.getModelName(),
                        product.getUnitPrice(),
                        cart.getQuantity(),
                        itemTotalPrice
                ));
                totalAmount += itemTotalPrice;
            }
        }

        req.setAttribute("cartItemViews", cartItemViews);
        req.setAttribute("totalAmount", totalAmount);

        return "shop/order/order_form";
    }
}