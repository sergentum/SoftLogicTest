<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<jsp:include page="include/header.jsp"/>

<body>

<div class="container" style="width: 300px;">
    <c:url value="/spring_security_check" var="loginUrl"/>
    <form action="${loginUrl}" method="post">
        <h2 class="form-signin-heading">Please sign in</h2>
        <input type="text" class="form-control" name="j_login" placeholder="Email address" required autofocus
               value="01234567890">
        <input type="password" class="form-control" name="j_password" placeholder="Password" required value="123123">
        <button class="btn btn-lg btn-primary btn-block" type="submit">Log me in</button>
    </form>

    <c:if test="${param.auth eq 'failure'}">
        <div class="error">
            <spring:message code="error.LoginError"/>
        </div>
    </c:if>
</div>
<jsp:include page="include/footer.jsp"/>
</body>
</html>
