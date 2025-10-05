<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>

<div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
    <h1 class="h2">주문 목록</h1>
</div>
<div class="table-responsive">
    <table class="table table-striped table-sm">
        <thead>
        <tr>
            <th>주문번호</th>
            <th>주문자 ID</th>
            <th>주문일시</th>
            <th>총액</th>
            <th>배송지</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="order" items="${orderList}">
            <tr>
                <td>${order.orderId}</td>
                <td>${order.userId}</td>
                <td>${fn:substring(fn:replace(order.orderDate.toString(), 'T', ' '), 0, 19)}</td>
                <td>${order.totalPrice}</td>
                <td>${order.shipAddress}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>