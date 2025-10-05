<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h2>상품 목록</h2>

<div style="margin:8px 0;">
    <a href="${pageContext.request.contextPath}/admin/product/add.do">상품 등록</a>
</div>

<table border="1" cellspacing="0" cellpadding="6" width="100%">
    <thead>
    <tr>
        <th>ID</th>
        <th>모델명</th>
        <th>모델번호</th>
        <th>가격</th>
        <th>관리</th>
    </tr>
    </thead>
    <tbody>
    <c:choose>
        <c:when test="${empty productList}">
            <tr><td colspan="5" style="text-align:center;">등록된 상품이 없습니다.</td></tr>
        </c:when>
        <c:otherwise>
            <c:forEach var="p" items="${productList}">
                <tr>
                    <td>${p.productId}</td>
                    <td>${p.modelName}</td>
                    <td>${p.modelNumber}</td>
                    <td>${p.unitPrice}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/admin/product/update.do?productId=${p.productId}">수정</a>
                        |
                        <a href="${pageContext.request.contextPath}/admin/product/delete.do?productId=${p.productId}"
                           onclick="return confirm('삭제하시겠습니까?');">삭제</a>
                    </td>
                </tr>
            </c:forEach>
        </c:otherwise>
    </c:choose>
    </tbody>
</table>
