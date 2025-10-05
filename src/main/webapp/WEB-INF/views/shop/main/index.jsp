<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">
    <c:forEach var="product" items="${productList}">
        <div class="col">
            <div class="card shadow-sm">
                <img src="${not empty product.productImage ? product.productImage : '/resources/images/default_product.png'}" class="bd-placeholder-img card-img-top" width="100%" height="225" alt="${product.modelName}">
                <div class="card-body">
                    <h5 class="card-title">${product.modelName}</h5>
                    <p class="card-text">${product.description}</p>
                    <div class="d-flex justify-content-between align-items-center">
                        <form method="post" action="/cart/add.do" class="d-flex">
                            <input type="hidden" name="productId" value="${product.productId}" />
                            <input type="number" name="quantity" class="form-control form-control-sm me-2" value="1" min="1" style="width: 70px;">
                            <button type="submit" class="btn btn-sm btn-outline-primary">장바구니 담기</button>
                        </form>
                        <small class="text-muted">${product.unitPrice}원</small>
                    </div>
                </div>
            </div>
        </div>
    </c:forEach>
</div>