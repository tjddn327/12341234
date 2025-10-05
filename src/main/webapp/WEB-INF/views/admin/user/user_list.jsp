<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>

<div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
    <h1 class="h2">회원 목록</h1>
</div>
<div class="table-responsive">
    <table class="table table-striped table-sm">
        <thead>
        <tr>
            <th>아이디</th>
            <th>이름</th>
            <th>권한</th>
            <th>가입일</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="user" items="${userList}">
            <tr>
                <td>${user.userId}</td>
                <td>${user.userName}</td>
                <td>
                    <c:choose>
                        <c:when test="${user.userAuth.name() == 'ROLE_ADMIN'}">
                            <span class="badge bg-danger">관리자</span>
                        </c:when>
                        <c:otherwise>
                            <span class="badge bg-secondary">일반회원</span>
                        </c:otherwise>
                    </c:choose>
                </td>
                <td>
                        ${fn:substring(fn:replace(user.createdAt.toString(), 'T', ' '), 0, 19)}
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>