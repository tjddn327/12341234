package com.nhnacademy.shoppingmall.controller.cart;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.model.cart.domain.Cart;
import com.nhnacademy.shoppingmall.model.cart.service.CartService;
import com.nhnacademy.shoppingmall.model.cart.service.impl.CartServiceImpl;
import com.nhnacademy.shoppingmall.model.product.domain.Product;
import com.nhnacademy.shoppingmall.model.product.repository.impl.ProductRepositoryImpl;
import com.nhnacademy.shoppingmall.model.product.service.ProductService;
import com.nhnacademy.shoppingmall.model.product.service.impl.ProductServiceImpl;
import com.nhnacademy.shoppingmall.model.user.domain.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RequestMapping(method = RequestMapping.Method.GET, value = "/cart/view.do")
public class CartViewController implements BaseController {

    private final CartService cartService = new CartServiceImpl();
    private final ProductService productService = new ProductServiceImpl(new ProductRepositoryImpl());

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
            Product product = productService.getProduct(item.getProductId());
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
