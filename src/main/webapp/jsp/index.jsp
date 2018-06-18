<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<!DOCTYPE html>
<html lang="en">

<jsp:include page="include/header.jsp"/>

<body>

<div class="container">

    <div class="jumbotron" style="margin-top: 20px;">

        <p class="lead"><fmt:message key="app.title"/> - <fmt:message key="app.description"/></p>

    </div>

</div>

<jsp:include page="include/footer.jsp"/>
</body>
</html>
