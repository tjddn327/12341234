<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<div class="container mt-5">
    <h2>장바구니</h2>
    <hr>
    <c:choose>
        <c:when test="${empty productWithQuantity}">
            <div class="alert alert-secondary text-center" role="alert">
                장바구니가 비어있습니다.
            </div>
        </c:when>
        <c:otherwise>
            <table class="table">
                <thead>
                <tr>
                    <th scope="col">상품 이미지</th>
                    <th scope="col">상품명</th>
                    <th scope="col">단가</th>
                    <th scope="col">수량</th>
                    <th scope="col">합계</th>
                    <th scope="col">관리</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="entry" items="${productWithQuantity}">
                    <tr>
                        <td><img src="${not empty entry.key.productImage ? entry.key.productImage : '/resources/images/default_product.png'}" alt="${entry.key.modelName}" style="width: 100px; height: auto;"></td>
                        <td>${entry.key.modelName}</td>
                        <td>${entry.key.unitPrice}원</td>
                        <td>${entry.value}</td>
                        <td>${entry.key.unitPrice * entry.value}원</td>
                        <td>
                            <form action="/cart/delete.do" method="post">
                                <input type="hidden" name="productId" value="${entry.key.productId}">
                                <button type="submit" class="btn btn-danger btn-sm">삭제</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <hr>
            <div class="d-flex justify-content-end align-items-center">
                <h4>총 결제 금액: ${totalPrice}원</h4>
                <a href="/order/form.do" class="btn btn-primary ms-3">주문하기</a>
            </div>
        </c:otherwise>
    </c:choose>
</div>
