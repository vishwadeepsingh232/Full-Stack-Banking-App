<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!-- Deposit card -->
<div class="card deposite-card">
    <div class="card-body">
        <form action="/transact/deposit" method="POST" ">
            <div class="form-group mb-2">
                <label for="deposite_amount">Enter Deposit Amount</label>
                <input type="text"  name="deposite_amount" class="form-control" placeholder="Enter deposit amount">
            </div>

            <div class="form-group">
                <label for="account_id">Select Account</label>
                <select name="account_id" class="form-control" style="background-color: lightgrey">
                    <option value="">--Select Account--</option>
                    <c:if test="${userAccounts != null}">
                        <c:forEach items="${userAccounts}" var="selectAccount">
                            <option value="${selectAccount.account_id}">${selectAccount.account_name}</option>
                        </c:forEach>
                    </c:if>
                </select>
            </div>

            <div class="form-group my-2">
                <button type="submit" class="btn btn-md transact-btn">Deposit</button>
            </div>
        </form>
    </div>
</div>
<!-- End of deposit card -->
