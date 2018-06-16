<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>

<jsp:include page="../include/header.jsp"/>

<body>

<div class="container" style="width: 400px;">

    <form:form method="POST" modelAttribute="transaction" class="form-signin">
        <h2 class="form-signin-heading">Create new transaction</h2>

        <spring:bind path="payee">
            <div class="form-group ${status.error ? 'has-error' : ''}">

                <form:select type="payee" path="payee" class="form-control">
                    <form:options items="${payeeList}" itemValue="id" itemLabel="name"/>
                </form:select>
                <form:errors path="payee"/>
            </div>
        </spring:bind>

        <spring:bind path="invoice">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input type="text" path="invoice" class="form-control" placeholder="invoice"/>
                <form:errors path="invoice"/>
            </div>
        </spring:bind>

        <%--<spring:bind path="email">--%>
            <%--<div class="form-group ${status.error ? 'has-error' : ''}">--%>
                <%--<form:input type="text" path="email" class="form-control" placeholder="Email"/>--%>
                <%--<form:errors path="email"/>--%>
            <%--</div>--%>
        <%--</spring:bind>--%>

        <%--<spring:bind path="password">--%>
            <%--<div class="form-group ${status.error ? 'has-error' : ''}">--%>
                <%--<form:input type="password" path="password" class="form-control" placeholder="Password"/>--%>
                <%--<form:errors path="password"/>--%>
            <%--</div>--%>
        <%--</spring:bind>--%>

        <%--<spring:bind path="passwordConfirm">--%>
            <%--<div class="form-group ${status.error ? 'has-error' : ''}">--%>
                <%--<form:input type="password" path="passwordConfirm" class="form-control"--%>
                            <%--placeholder="Confirm your password"/>--%>
                <%--<form:errors path="passwordConfirm"/>--%>
            <%--</div>--%>
        <%--</spring:bind>--%>

        <%--<c:out value="${message}"/>--%>

        <button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
    </form:form>

</div>


<div class="container">

    <form:form method="POST" action="/app/transaction" >
        <h2 class="form-signin-heading">Create new transaction</h2>

        <table class="table" style="width: available;">
            <tr>
                <th>Payee</th>
                <td>
                    <select class="pull-right" name="payeeName">
                        <c:forEach var="payee" items="${payeeList}">
                            <option value="${payee.name}">
                                <c:out value="${payee.name}"/>
                            </option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <th>Invoice</th>
                <td>
                    <input class="pull-right" type="text" name="invoice" value=""/>
                </td>

            </tr>
            <tr>
                <th>Amount</th>
                <td>
                    <input class="pull-right" type="text" name="amount" value=""/>
                </td>
            </tr>

            <tr>
                <td>
                    <button class="btn btn-lg btn-primary btn-block" type="submit">
                        Submit
                    </button>
                </td>
                <td>
                    <a class="btn btn-lg btn-danger pull-right" href="../app/" role="button">
                        Cancel
                    </a>
                </td>
            </tr>


        </table>
    </form:form>

</div>
<jsp:include page="../include/footer.jsp"/>
</body>
</html>
