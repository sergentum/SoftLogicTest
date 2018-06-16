<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<!DOCTYPE html>
<html lang="en">

<jsp:include page="include/header.jsp"/>

<body>

<div class="container">

    <div class="jumbotron" style="margin-top: 20px;">

        <h1>AppName</h1>
        <p class="lead">AppName - суперсервис позволяющий оплачивать любые услуги!</p>
        <sec:authorize access="!isAuthenticated()">
            <p><a class="btn btn-lg btn-success" href="<c:url value="/login" />" role="button">Authorize</a></p>
        </sec:authorize>
        <sec:authorize access="isAuthenticated()">
            <p>Ваш логин: <sec:authentication property="principal.username"/></p>
            <p><a class="btn btn-lg btn-danger" href="<c:url value="/logout" />" role="button">Log out</a></p>

        </sec:authorize>
    </div>

</div>

<jsp:include page="include/footer.jsp"/>
</body>
</html>
