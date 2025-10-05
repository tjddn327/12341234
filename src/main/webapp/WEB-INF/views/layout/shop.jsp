<%@ page contentType="text/html;charset=UTF-8" language="java" session="true" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!doctype html>
<html lang="ko">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
    <title>NHN 아카데미 Shopping Mall</title>

</head>
<body>

<div class="mainContainer">
    <header class="p-3 bg-dark text-white">
        <div class="container">
            <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">

                <a href="/" class="d-flex align-items-center mb-2 mb-lg-0 text-white text-decoration-none">
                    <svg class="bi me-2" width="40" height="32" role="img" aria-label="Bootstrap"><use xlink:href="#bootstrap"></use></svg>
                </a>

                <ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
                    <li><a href="/index.do" class="nav-link px-2 text-secondary">Home</a></li>
                </ul>

                <form class="col-12 col-lg-auto mb-3 mb-lg-0 me-lg-3">
                    <input type="search" class="form-control form-control-dark" placeholder="Search..." aria-label="Search">
                </form>

                <div class="text-end">
                    <c:choose>
                        <c:when test="${not empty sessionScope.user}">
                            <%-- 로그인된 사용자 --%>
                            <span class="text-white me-3">${sessionScope.user.userName}님, 환영합니다.</span>

                            <%-- 권한에 따라 다른 메뉴를 보여줍니다. --%>
                            <c:choose>
                                <c:when test="${sessionScope.user.userAuth.name() == 'ROLE_ADMIN'}">
                                    <%-- 관리자일 경우 --%>
                                    <a href="/admin/index.do" class="btn btn-danger me-2">관리자 페이지</a>
                                </c:when>
                                <c:otherwise>
                                    <%-- 일반 사용자일 경우 --%>
                                    <a href="/mypage/index.do" class="btn btn-outline-light me-2">마이페이지</a>
                                    <a href="/cart/view.do" class="btn btn-outline-warning me-2">장바구니</a>
                                </c:otherwise>
                            </c:choose>

                            <a class="btn btn-light" href="/logout.do">로그아웃</a>
                        </c:when>
                        <c:otherwise>
                            <%-- 로그인되지 않은 경우 --%>
                            <a class="btn btn-outline-light me-2" href="/login.do" >로그인</a>
                            <a class="btn btn-warning" href="/signup.do" >회원가입</a>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>
    </header>

    <main>
        <div class="album py-5 bg-light">
            <div class="container">
                <jsp:include page="${layout_content_holder}" />
            </div>
        </div>
    </main>

    <footer class="text-muted py-5">
        <div class="container">
            <p class="float-end mb-1">
                <a href="#">Back to top</a>
            </p>
            <p class="mb-1">shoppingmall example is © nhnacademy.com</p>
        </div>
    </footer>

</div>

</body>
</html>