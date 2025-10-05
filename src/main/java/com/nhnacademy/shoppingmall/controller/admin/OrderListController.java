package com.nhnacademy.shoppingmall.controller.admin;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.model.orders.domain.Orders;
import com.nhnacademy.shoppingmall.model.orders.repository.impl.OrdersRepositoryImpl;
import com.nhnacademy.shoppingmall.model.orders.service.OrdersService;
import com.nhnacademy.shoppingmall.model.orders.service.impl.OrdersServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

@RequestMapping(method = RequestMapping.Method.GET, value = "/admin/orders.do")
public class OrderListController implements BaseController {

    private final OrdersService ordersService = new OrdersServiceImpl(new OrdersRepositoryImpl());

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        List<Orders> orderList = ordersService.getAllOrders();
        req.setAttribute("orderList", orderList);
        return "admin/order/order_list";
    }
}
