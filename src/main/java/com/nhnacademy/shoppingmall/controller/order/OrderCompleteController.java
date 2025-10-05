package com.nhnacademy.shoppingmall.controller.order;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@RequestMapping(method = RequestMapping.Method.GET, value = "/order/complete.do")
public class OrderCompleteController implements BaseController {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String orderId = req.getParameter("orderId");
        req.setAttribute("orderId", orderId);
        return "shop/order/order_complete";
    }
}
