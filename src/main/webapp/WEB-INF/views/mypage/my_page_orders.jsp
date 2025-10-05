<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<div class="container mt-5">
    <h2>주문 내역 조회</h2>
    <hr>
    <table class="table table-hover">
        <thead>
        <tr>
            <th scope="col">주문번호</th>
            <th scope="col">주문일자</th>
            <th scope="col">배송지</th>
            <th scope="col">총 결제금액</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="order" items="${ordersList}">
            <tr>
                <td>${order.orderId}</td>
                <td><fmt:formatDate value="${order.orderDate}" pattern="yyyy-MM-dd HH:mm"/></td>
                <td>${order.shipAddress}</td>
                <td><fmt:formatNumber value="${order.totalPrice}" type="currency" currencySymbol=""/> 원</td>
            </tr>
        </c:forEach>
        <c:if test="${empty ordersList}">
            <tr>
                <td colspan="4" class="text-center">주문 내역이 없습니다.</td>
            </tr>
        </c:if>
        </tbody>
    </table>

    <nav aria-label="Page navigation">
        <ul class="pagination justify-content-center">
            <c:if test="${currentPage > 1}">
                <li class="page-item">
                    <a class="page-link" href="/mypage/orders.do?page=${currentPage - 1}" aria-label="Previous">
                        <span aria-hidden="true">&laquo;</span>
                    </a>
                </li>
            </c:if>

            <c:forEach var="i" begin="1" end="${totalPages}">
                <li class="page-item <c:if test='${i == currentPage}'>active</c:if>">
                    <a class="page-link" href="/mypage/orders.do?page=${i}">${i}</a>
                </li>
            </c:forEach>

            <c:if test="${currentPage < totalPages}">
                <li class="page-item">
                    <a class="page-link" href="/mypage/orders.do?page=${currentPage + 1}" aria-label="Next">
                        <span aria-hidden="true">&raquo;</span>
                    </a>
                </li>
            </c:if>
        </ul>
    </nav>
</div>