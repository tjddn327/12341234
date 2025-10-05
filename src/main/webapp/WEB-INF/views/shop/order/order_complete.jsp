<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="container mt-5 text-center">
    <h2 class="mb-4">주문이 성공적으로 완료되었습니다.</h2>
    <p>고객님의 주문 번호는 <strong>${orderId}</strong> 입니다.</p>
    <div class="mt-4">
        <a href="/index.do" class="btn btn-secondary">계속 쇼핑하기</a>
        <a href="/mypage/orders.do" class="btn btn-primary">주문 내역 확인하기</a>
    </div>
</div>