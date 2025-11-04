package com.nhnacademy.shoppingmall.controller.cart;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.model.cart.domain.Cart;
import com.nhnacademy.shoppingmall.model.cart.domain.CartPk;
import com.nhnacademy.shoppingmall.model.cart.service.CartService;
import com.nhnacademy.shoppingmall.model.product.domain.Product;
import com.nhnacademy.shoppingmall.model.product.service.ProductService;
import com.nhnacademy.shoppingmall.model.user.domain.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
import java.util.Objects;

@Slf4j
@RequestMapping(method = RequestMapping.Method.POST, value = "/cart/add.do")
@Controller
public class CartAddController implements BaseController {
    private final CartService cartService;
    private final ProductService productService;

    @Autowired
    public CartAddController(CartService cartService, ProductService productService) {
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
        int productId = Integer.parseInt(req.getParameter("productId"));
        int quantity = Integer.parseInt(req.getParameter("quantity"));

        Product product = productService.getProduct(productId);
        CartPk cartPk = new CartPk(user.getUserId(), productId);

        Cart cartItem = new Cart(cartPk, quantity, LocalDateTime.now(), user, product);
        cartService.addCartItem(cartItem);

        log.info("User '{}' added product '{}' (quantity: {}) to cart.", user.getUserId(), productId, quantity);

        return "redirect:/index.do";
    }
}