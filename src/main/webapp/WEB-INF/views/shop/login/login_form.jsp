<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div style="margin: auto; width: 400px;">
    <div class="p-2">
        <form method="post" action="/loginAction.do">

            <h1 class="h3 mb-3 fw-normal text-center">Please sign in</h1>

            <div class="form-floating mb-3">
                <input type="text" name="userId" class="form-control" id="userId" placeholder="회원 아이디" required>
                <label for="userId">회원 아이디</label>
            </div>

            <div class="form-floating mb-3">
                <input type="password" name="userPassword" class="form-control" id="userPassword" placeholder="비밀번호" required>
                <label for="userPassword">비밀번호</label>
            </div>

            <button class="w-100 btn btn-lg btn-primary mt-3" type="submit">Sign in</button>

            <p class="mt-4 text-center">
                계정이 없으신가요? <a href="/signup.do">회원가입</a>
            </p>

            <p class="mt-5 mb-3 text-muted text-center">© 2022-2025</p>

        </form>
    </div>
</div>