<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
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

    <title><fmt:message key="app.title"/></title>

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
        <table class="table">
            <tr>
                <td>
                    <a class="btn btn-sm btn-primary" href="<c:url value="/" />" role="button">
                        <fmt:message key="app.title"/>
                    </a>
                </td>
                <td>
                    <a class="btn btn-sm btn-success pull-right" href="<c:url value="/registration" />" role="button">
                        <fmt:message key="app.register"/>
                    </a>

                    <a class="btn btn-sm btn-success pull-right" href="<c:url value="/login" />" role="button">
                        <fmt:message key="app.login"/>
                    </a>
                </td>
            </tr>
        </table>


    </sec:authorize>

    <sec:authorize access="isAuthenticated()">
        <table class="table">
            <tr>
                <td>
                    <a class="btn btn-sm btn-success" href="<c:url value="/" />" role="button">
                        <fmt:message key="app.title"/>
                    </a>
                </td>
                <td>
                    <fmt:message key="app.profile"/>
                    <a class="btn btn-sm btn-primary" href="<c:url value="../app/" />" role="button">
                        <sec:authentication property="principal.username"/>
                    </a>
                </td>
                <td>
                    <a class="btn btn-sm btn-danger pull-right" href="<c:url value="/logout" />" role="button">
                        <fmt:message key="app.logout"/>
                    </a>
                </td>
            </tr>
        </table>

    </sec:authorize>
</div>
</body>
</html>
