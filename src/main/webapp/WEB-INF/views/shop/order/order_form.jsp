<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<div class="container mt-5">
    <h2>주문서 작성</h2>
    <form action="/order/submit.do" method="post" class="needs-validation" novalidate>
        <div class="card mb-4">
            <div class="card-header">
                주문 상품
            </div>
            <ul class="list-group list-group-flush">
                <c:forEach var="item" items="${cartItemViews}">
                    <li class="list-group-item d-flex justify-content-between align-items-center">
                        <div>
                            <h6 class="my-0">${item.productName} (Product ID: ${item.productId})</h6>
                            <small class="text-muted">수량: ${item.quantity}개</small>
                        </div>
                        <span class="text-muted">${item.totalPrice}원</span>
                    </li>
                </c:forEach>
                <li class="list-group-item d-flex justify-content-between">
                    <span>총 결제금액 (Total)</span>
                    <strong>${totalAmount}원</strong>
                </li>
            </ul>
        </div>

        <div class="card mb-4">
            <div class="card-header">배송지 정보</div>
            <div class="card-body">
                <div class="mb-3">
                    <label for="shipAddress" class="form-label">주소</label>
                    <input type="text" class="form-control" id="shipAddress" name="shipAddress" placeholder="배송 받으실 주소를 입력해주세요." required>
                    <div class="invalid-feedback">
                        배송지 주소는 필수입니다.
                    </div>
                </div>
            </div>
        </div>

        <button type="submit" class="w-100 btn btn-primary btn-lg">결제하기</button>
    </form>
</div>

<script>
    // Bootstrap form validation script
    (function () {
        'use strict'
        var forms = document.querySelectorAll('.needs-validation')
        Array.prototype.slice.call(forms)
            .forEach(function (form) {
                form.addEventListener('submit', function (event) {
                    if (!form.checkValidity()) {
                        event.preventDefault()
                        event.stopPropagation()
                    }
                    form.classList.add('was-validated')
                }, false)
            })
    })()
</script>