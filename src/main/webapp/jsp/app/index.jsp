<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>

<jsp:include page="../header.jsp" />

<body>


<div class="container">

    User <b><c:out value="${user.username}"/></b>
    Balance <b><c:out value="${user.balance}"/></b>

    <table>
        <tr>
            <th>DateTime</th>
            <th>Payee</th>
            <th>Amount</th>
        </tr>
        <c:forEach items="${transactionList}" var="item">
            <tr>
                <%--<td>${item.timestamp}</td>--%>
                <td><fmt:formatDate value="${item.timestamp}" pattern="yyyy-MM-dd hh:mm " /></td>
                <td>${item.payee.name}</td>
                <td>${item.amount}</td>
            </tr>
        </c:forEach>
    </table>


    <%--<c:if test="${param.auth eq 'failure'}">--%>
        <%--<div class="error">--%>
            <%--<spring:message code="error.LoginError"/>--%>
        <%--</div>--%>
    <%--</c:if>--%>
</div>


</body>
</html>
