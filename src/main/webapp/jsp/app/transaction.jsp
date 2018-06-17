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
                <p>Payee:
                    <form:select type="payee" path="payee" class="form-control">
                        <form:options items="${payeeList}" itemValue="id" itemLabel="name"/>
                    </form:select>
                    <form:errors path="payee"/>
                </p>
            </div>
        </spring:bind>

        <spring:bind path="invoice">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <p>Invoice:
                    <form:input type="text" path="invoice" class="form-control"/>
                    <form:errors path="invoice"/>
                </p>
            </div>
        </spring:bind>

        <spring:bind path="amount">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <p>Amount:
                    <form:input type="number" path="amount" class="form-control" min="1" max="100"/>
                    <form:errors path="amount"/>
                </p>
            </div>
        </spring:bind>

        <button class="form-group btn btn-lg btn-primary" type="submit">Submit</button>

        <button class="form-group btn btn-lg btn-danger pull-right" href="../app/">Cancel</button>
    </form:form>

</div>


<jsp:include page="../include/footer.jsp"/>
</body>
</html>
