<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
    <h1 class="h2">관리자 대시보드</h1>
</div>
<div class="row">
    <div class="col-md-4">
        <div class="card">
            <div class="card-body">
                <h5 class="card-title">상품 관리</h5>
                <p class="card-text">상품을 등록, 수정, 삭제할 수 있습니다.</p>
                <a href="/admin/products.do" class="btn btn-primary">상품 관리 바로가기</a>
            </div>
        </div>
    </div>
    <div class="col-md-4">
        <div class="card">
            <div class="card-body">
                <h5 class="card-title">회원 관리</h5>
                <p class="card-text">가입된 모든 회원의 목록을 조회할 수 있습니다.</p>
                <a href="/admin/users.do" class="btn btn-primary">회원 관리 바로가기</a>
            </div>
        </div>
    </div>
    <div class="col-md-4">
        <div class="card">
            <div class="card-body">
                <h5 class="card-title">주문 관리</h5>
                <p class="card-text">접수된 모든 주문 내역을 조회할 수 있습니다.</p>
                <a href="/admin/orders.do" class="btn btn-primary">주문 관리 바로가기</a>
            </div>
        </div>
    </div>
</div>

