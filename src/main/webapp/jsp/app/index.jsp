<%@ page import="java.util.Date" %>
<%@ page import="java.util.TimeZone" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>

<jsp:include page="../include/header.jsp"/>

<body>


<div class="container">

    <table class="table">
        <tr>
            <td>
                <a class="btn btn-lg btn-primary" href="<c:url value="../app/transaction" />" role="button">
                    <fmt:message key="app.newTransaction"/>
                </a>
            </td>
            <td>
                <div class="pull-right">
                    <h1><fmt:message key="user.balance"/>: <b><c:out value="${user.balance}"/></b></h1>
                </div>
            </td>
        </tr>
    </table>

    <table class="table">
        <tr>
            <td>
                <input class="form-control" type="date" id="dateTime" name="dateTime"/>
            </td>
            <td>

            </td>
            <td>

            </td>
        <tr>
            <th><fmt:message key="app.datetime"/></th>
            <th><fmt:message key="app.payee"/></th>
            <th><fmt:message key="app.amount"/></th>
        </tr>
        <c:forEach items="${transactionList}" var="item">
            <tr>
                <td>
                    <fmt:formatDate value="${item.timestamp}" type="both" pattern="yyyy-MM-dd HH:mm "/>
                </td>
                <td>${item.payee.name}</td>
                <td>${item.amount}</td>
            </tr>
        </c:forEach>
    </table>

</div>

<jsp:include page="../include/footer.jsp"/>
</body>
</html>
