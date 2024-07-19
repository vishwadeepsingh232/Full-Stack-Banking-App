 <%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
 <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

 <!-- Payment card -->
      <div class="card payment-card">
        <div class="card-body">
           <!-- Payment Form -->
           <form action="/transact/payment" method="POST">
   <div class="form-group mb-2">
       <label for="">Account Holder/Beneficiary </label>
      <input type="text" name="beneficiary" class="form-control" placeholder="Enter Account holder/beneficiary Name">
     </div>
     <div class="form-group mb-2">
     <label for="">Beneficiary Account Number</label>
   <input type="text" name="account_number" class="form-control" placeholder="Enter beneficiary Account Number">
   </div>

                   <div class="form-group">
                     <label for="account_id">Select Account</label>
                    <select id="account_id" name="account_id" class="form-control" style="background-color: lightgrey">
                    <option value="">--Select Account--</option>
                    <c:if test="${userAccounts != null}">
                     <c:forEach items="${userAccounts}" var="selectAccount">
                    <option value="${selectAccount.account_id}">${selectAccount.account_name}</option>
                    </c:forEach>
                    </c:if>
                   </select>
                   </div>

                    <div class="form-group mb-2">
                        <label for="">Reference </label>
                        <input type="text" name="reference" class="form-control" placeholder="Enter Reference">
                    </div>
                    <div class="form-group mb-2">
                        <label for="">Enter Payment Amount</label>
                        <input type="text" name="payment_amount" class="form-control" placeholder="Enter payment amount">
                    </div>
                    <div class="form-group mb-2">
                       <button id="" class="btn btn-md transact-btn" >Pay</button>
                        </div>
           </form>
           <-- End Of Payment Form -->

        </div>
      </div>
<!-- End of Payment card -->