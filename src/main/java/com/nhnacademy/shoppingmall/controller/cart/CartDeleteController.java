package com.nhnacademy.shoppingmall.controller.cart;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.model.cart.service.CartService;
import com.nhnacademy.shoppingmall.model.user.domain.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Objects;

@Slf4j
@RequestMapping(method = RequestMapping.Method.POST, value = "/cart/delete.do")
@Controller
public class CartDeleteController implements BaseController {
    private final CartService cartService;

    @Autowired
    public CartDeleteController(CartService cartService) {
        this.cartService = cartService;
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession(false);
        if (Objects.isNull(session) || Objects.isNull(session.getAttribute("user"))) {
            return "redirect:/login.do";
        }

        User user = (User) session.getAttribute("user");
        int productId = Integer.parseInt(req.getParameter("productId"));

        cartService.deleteCartItem(user.getUserId(), productId);
        log.info("User '{}' deleted product '{}' from cart.", user.getUserId(), productId);

        return "redirect:/cart/view.do";
    }
}