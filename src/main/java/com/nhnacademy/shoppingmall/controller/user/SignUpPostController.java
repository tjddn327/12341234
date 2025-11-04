package com.nhnacademy.shoppingmall.controller.user;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.model.user.domain.User;
import com.nhnacademy.shoppingmall.model.user.exception.UserAlreadyExistsException;
import com.nhnacademy.shoppingmall.model.user.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;

@RequestMapping(method = RequestMapping.Method.POST, value = "/signupAction.do")
@Controller
public class SignUpPostController implements BaseController {

    private final UserService userService;

    @Autowired
    public SignUpPostController(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String userId = req.getParameter("user_id");
        String userName = req.getParameter("user_name");
        String userPassword = req.getParameter("user_password");
        String userBirth = req.getParameter("user_birth");

        if (userId == null || userId.isEmpty() ||
                userName == null || userName.isEmpty() ||
                userPassword == null || userPassword.isEmpty() ||
                userBirth == null || userBirth.length() != 8) {

            req.setAttribute("error_message", "모든 정보를 올바르게 입력해주세요.");
            return "shop/signup/signup_form";
        }

        User newUser = new User(
                userId,
                userName,
                userPassword,
                userBirth,
                User.Auth.ROLE_USER,
                1000000,
                LocalDateTime.now(),
                null
        );

        try {
            userService.saveUser(newUser);
        } catch (UserAlreadyExistsException e) {
            req.setAttribute("error_message", "이미 사용 중인 아이디입니다.");
            return "shop/signup/signup_form";
        } catch (Exception e) {
            req.setAttribute("error_message", "회원가입 중 오류가 발생했습니다.");
            return "shop/signup/signup_form";
        }

        return "redirect:/login.do";
    }
}