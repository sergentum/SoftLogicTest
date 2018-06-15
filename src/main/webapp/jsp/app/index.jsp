<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: username
  Date: 2018-06-04
  Time: 22:45 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>App Home</title>

    <!-- Bootstrap core CSS -->
    <link href="<c:url value="/resources/css/bootstrap.css" />" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="<c:url value="/resources/css/signin.css" />" rel="stylesheet">

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->
</head>
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
