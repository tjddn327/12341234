package com.nhnacademy.shoppingmall.controller.mypage;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.model.orders.domain.Orders;
import com.nhnacademy.shoppingmall.model.orders.repository.impl.OrdersRepositoryImpl;
import com.nhnacademy.shoppingmall.model.orders.service.OrdersService;
import com.nhnacademy.shoppingmall.model.orders.service.impl.OrdersServiceImpl;
import com.nhnacademy.shoppingmall.model.user.domain.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.util.List;

@RequestMapping(method = RequestMapping.Method.GET, value = "/mypage/orders.do")
public class MyPageOrdersController implements BaseController {

    private final OrdersService ordersService = new OrdersServiceImpl(new OrdersRepositoryImpl());

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession(false);
        User user = (User) session.getAttribute("user");
        String userId = user.getUserId();

        List<Orders> allOrders = ordersService.findByUserId(userId);

        int page = req.getParameter("page") != null ? Integer.parseInt(req.getParameter("page")) : 1;
        int pageSize = 10;
        int totalOrders = allOrders.size();
        int totalPages = (int) Math.ceil((double) totalOrders / pageSize);
        int start = (page - 1) * pageSize;
        int end = Math.min(start + pageSize, totalOrders);

        List<Orders> ordersForPage = allOrders.subList(start, end);

        req.setAttribute("ordersList", ordersForPage);
        req.setAttribute("currentPage", page);
        req.setAttribute("totalPages", totalPages);

        return "mypage/my_page_orders";
    }
}