<%@ page contentType="text/html;charset=UTF-8" language="java" isErrorPage="true" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <title>오류 발생</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container text-center mt-5">
    <h1 class="display-1">Error</h1>
    <p class="lead">서비스 이용에 불편을 드려 죄송합니다.</p>
    <p>요청을 처리하는 중에 예상치 못한 오류가 발생했습니다.</p>
    <% if (exception != null) { %>
    <div class="alert alert-danger mt-4">
        <strong>오류 메시지:</strong> <%= exception.getMessage() %>
    </div>
    <% } %>
    <a href="/index.do" class="btn btn-primary mt-3">홈으로 돌아가기</a>
</div>
</body>
</html>