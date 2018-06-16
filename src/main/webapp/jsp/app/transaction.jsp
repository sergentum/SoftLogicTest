<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>

<jsp:include page="../include/header.jsp"/>

<body>

<div class="container">

    <form:form method="POST" action="/app/transaction">
        <h2 class="form-signin-heading">Create new transaction</h2>

        <table class="table">
            <tr>
                <th>Payee</th>
                <th>Invoice</th>
                <th>Amount</th>
            </tr>
            <tr>
                <td>
                        <%--<input type="text" name="payeeName" value=""/>--%>

                    <select name="payeeName">
                        <c:forEach var="payee" items="${payeeList}">
                            <option value="${payee.name}">
                                <c:out value="${payee.name}"/>
                            </option>
                        </c:forEach>
                    </select>
                </td>
                <td>
                    <input type="text" id="txtPassword" name="invoice" value=""/>
                </td>
                <td>
                    <input type="text" id="txtPhone" name="amount" value=""/>
                </td>
            </tr>

            <tr>
                <td>
                    <button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
                </td>
                <td>

                </td>
                <td>
                    <a class="btn btn-lg btn-danger pull-right" href="../app/" role="button">
                    Return
                    </a>
                </td>
            </tr>


        </table>
    </form:form>

</div>
<jsp:include page="../include/footer.jsp"/>
</body>
</html>
