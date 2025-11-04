package com.nhnacademy.shoppingmall.controller.auth;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.model.user.domain.User;
import com.nhnacademy.shoppingmall.model.user.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@RequestMapping(method = RequestMapping.Method.POST,value = "/loginAction.do")
@Controller
public class LoginPostController implements BaseController {

    private final UserService userService;

    @Autowired
    public LoginPostController(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String userId = req.getParameter("user_id");
        String userPassword = req.getParameter("user_password");
        try {
            User user = userService.doLogin(userId, userPassword);
            req.getSession(true).setAttribute("user", user);
            req.getSession().setMaxInactiveInterval(60 * 60);
            return "redirect:/index.do";
        } catch(Exception e) {
            req.setAttribute("errorMessage", "로그인 실패, 아이디/비밀번호 확인");
            return "shop/login/login_form";
        }
    }
}