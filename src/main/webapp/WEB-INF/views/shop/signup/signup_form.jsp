<%@ page contentType="text/html;charset=UTF-8" language="java" session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div style="margin: auto; width: 400px;">
    <div class="p-2">
        <form method="post" action="/signupAction.do">

            <h1 class="h3 mb-3 fw-normal">회원가입</h1>

            <c:if test="${not empty error_message}">
                <div class="alert alert-danger" role="alert">
                        ${error_message}
                </div>
            </c:if>

            <div class="form-floating mb-2">
                <input type="text" name="user_id" class="form-control" id="user_id" placeholder="아이디" required>
                <label for="user_id">아이디</label>
            </div>

            <div class="form-floating mb-2">
                <input type="text" name="user_name" class="form-control" id="user_name" placeholder="이름" required>
                <label for="user_name">이름</label>
            </div>

            <div class="form-floating mb-2">
                <input type="password" name="user_password" class="form-control" id="user_password" placeholder="비밀번호" required>
                <label for="user_password">비밀번호</label>
            </div>

            <div class="form-floating mb-2">
                <input type="text" name="user_birth" class="form-control" id="user_birth" placeholder="생년월일 (8자리)" required>
                <label for="user_birth">생년월일 (예: 20010101)</label>
            </div>

            <button class="w-100 btn btn-lg btn-primary mt-3" type="submit">가입하기</button>

            <p class="mt-5 mb-3 text-muted">© 2022-2024</p>

        </form>
    </div>
</div>