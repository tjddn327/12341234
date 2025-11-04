package com.nhnacademy.shoppingmall.controller.cart;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.model.cart.domain.Cart;
import com.nhnacademy.shoppingmall.model.cart.service.CartService;
import com.nhnacademy.shoppingmall.model.product.domain.Product;
import com.nhnacademy.shoppingmall.model.product.service.ProductService;
import com.nhnacademy.shoppingmall.model.user.domain.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RequestMapping(method = RequestMapping.Method.GET, value = "/cart/view.do")
@Controller
public class CartViewController implements BaseController {

    private final CartService cartService;
    private final ProductService productService;

    @Autowired
    public CartViewController(CartService cartService, ProductService productService) {
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

        Map<Product, Integer> productWithQuantity = new HashMap<>();
        int totalPrice = 0;

        for (Cart item : cartItems) {
            Product product = productService.getProduct(item.getPk().getProductId());
            if (Objects.nonNull(product)) {
                productWithQuantity.put(product, item.getQuantity());
                totalPrice += product.getUnitPrice() * item.getQuantity();
            }
        }

        req.setAttribute("productWithQuantity", productWithQuantity);
        req.setAttribute("totalPrice", totalPrice);

        return "shop/cart/cart_view";
    }
}