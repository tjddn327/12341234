<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h2><c:out value="${empty product ? '상품 등록' : '상품 수정'}"/></h2>

<form method="post" action="${pageContext.request.contextPath}${empty product ? '/admin/product/addAction.do' : '/admin/product/updateAction.do'}">
    <c:if test="${!empty product}">
        <input type="hidden" name="productId" value="${product.productId}"/>
    </c:if>

    <div>
        <label>모델명</label>
        <input type="text" name="modelName" value="${product.modelName}"/>
    </div>
    <div>
        <label>모델번호</label>
        <input type="text" name="modelNumber" value="${product.modelNumber}"/>
    </div>
    <div>
        <label>카테고리ID</label>
        <input type="number" name="categoryId" value="${product.categoryId}"/>
    </div>
    <div>
        <label>가격</label>
        <input type="number" name="unitPrice" value="${product.unitPrice}"/>
    </div>
    <div>
        <label>이미지 URL</label>
        <input type="text" name="productImage" value="${product.productImage}"/>
    </div>
    <div>
        <label>설명</label>
        <textarea name="description" rows="4">${product.description}</textarea>
    </div>

    <div style="margin-top:8px;">
        <button type="submit">저장</button>
        <a href="${pageContext.request.contextPath}/admin/products.do">목록</a>
    </div>
</form>
