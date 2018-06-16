<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>

<jsp:include page="../include/header.jsp"/>

<body>


<div class="container">

    <table class="table">
        <tr>
            <td>
                <a class="btn btn-lg btn-primary" href="<c:url value="../app/transaction" />" role="button">
                    New transaction
                </a>
            </td>
            <td>
                <div class="pull-right">
                    <h1>Balance: <b><c:out value="${user.balance}"/></b></h1>
                </div>
            </td>
        </tr>
    </table>




    <table class="table">
        <tr>
            <th>DateTime</th>
            <th>Payee</th>
            <th>Amount</th>
        </tr>
        <c:forEach items="${transactionList}" var="item">
            <tr>
                    <%--<td>${item.timestamp}</td>--%>
                <td><fmt:formatDate value="${item.timestamp}" pattern="yyyy-MM-dd hh:mm "/></td>
                <td>${item.payee.name}</td>
                <td>${item.amount}</td>
            </tr>
        </c:forEach>
    </table>

</div>

<jsp:include page="../include/footer.jsp"/>
</body>
</html>
