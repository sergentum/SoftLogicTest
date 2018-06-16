<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>

<jsp:include page="../include/header.jsp"/>

<body>

<div class="container">

    <form:form method="POST" action="/app/transaction">
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
                    <input class="pull-right" type="text" id="txtPhone" name="amount" value=""/>
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
