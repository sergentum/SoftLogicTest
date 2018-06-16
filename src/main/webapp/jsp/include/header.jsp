<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: username
  Date: 2018-06-16
  Time: 15:04 PM
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

    <title>Spring Security</title>

    <!-- Bootstrap core CSS -->
    <link href="<c:url value="/resources/css/bootstrap.css" />" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="<c:url value="/resources/css/jumbotron-narrow.css" />" rel="stylesheet">

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<div class="container">
    <sec:authorize access="!isAuthenticated()">

        <p><a class="btn btn-lg btn-success" href="<c:url value="/login" />" role="button">Войти</a></p>

    </sec:authorize>
    <sec:authorize access="isAuthenticated()">
        <table>
            <tr>
                <td>
                    Your login:
                    <a class="btn btn-lg btn-primary" href="<c:url value="../app/" />" role="button">
                        <sec:authentication property="principal.username"/>
                    </a>
                </td>
                <td>
                    <a class="btn btn-lg btn-danger" href="<c:url value="/logout" />" role="button">
                        Выйти
                    </a>
                </td>
            </tr>
        </table>

    </sec:authorize>
</div>
</body>
</html>
