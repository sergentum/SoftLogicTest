<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>

<jsp:include page="include/header.jsp"/>

<body>

<div class="container" style="width: 400px;">

    <form:form method="POST" modelAttribute="user" class="form-signin">
        <h2 class="form-signin-heading"><spring:message code="user.newAccount"/></h2>

        <spring:bind path="username">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <spring:message code="user.login" var="userLogin"/>
                <form:input type="text" path="username" class="form-control" placeholder="${userLogin}"
                            autofocus="true"/>
                <form:errors path="username"/>
            </div>
        </spring:bind>

        <spring:bind path="email">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <spring:message code="user.mail" var="userMail"/>
                <form:input type="text" path="email" class="form-control" placeholder="${userMail}"/>
                <form:errors path="email"/>
            </div>
        </spring:bind>

        <spring:bind path="password">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <spring:message code="user.password" var="userPassword"/>
                <form:input type="password" path="password" class="form-control" placeholder="${userPassword}"/>
                <form:errors path="password"/>
            </div>
        </spring:bind>

        <spring:bind path="passwordConfirm">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <spring:message code="user.confirmPassword" var="confirmPassword"/>
                <form:input type="password" path="passwordConfirm" class="form-control"
                            placeholder="${confirmPassword}"/>
                <form:errors path="passwordConfirm"/>
            </div>
        </spring:bind>

        <c:out value="${message}"/>

        <button class="btn btn-lg btn-primary btn-block" type="submit"><fmt:message key="app.submit"/></button>
    </form:form>

</div>
<jsp:include page="include/footer.jsp"/>
</body>
</html>
