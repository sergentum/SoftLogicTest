<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <title>Index</title>
</head>
<body>
Index JSP
Hello <b><c:out value="${pageContext.request.remoteUser}"/></b>
</body>
</html>
