 <%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
 <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="card transfer-card">
<div class="card-body">

  <!-- Transfer Form -->
  <form action="/transact/transfer" method="POST">
            <!-- Transfer card -->
                   <div class="form-group">
                        <label for="account_id">Select Account</label>
                        <select id="transfer_from" name="transfer_from" class="form-control" style="background-color: lightgrey">
                            <option value="">--Select Account--</option>
                            <c:if test="${userAccounts != null}">
                                <c:forEach items="${userAccounts}" var="selectAccount">
                                    <option value="${selectAccount.account_id}">${selectAccount.account_name}</option>
                                </c:forEach>
                            </c:if>
                        </select>
                    </div>

                    <div class="form-group">
                        <label for="account_id">Select Account</label>
                        <select id="transfer_to" name="transfer_to" class="form-control" style="background-color: lightgrey">
                            <option value="">--Select Account--</option>
                            <c:if test="${userAccounts != null}">
                             <c:forEach items="${userAccounts}" var="selectAccount">
                             <option value="${selectAccount.account_id}">${selectAccount.account_name}</option>
                               </c:forEach>
                            </c:if>
                        </select>
                    </div>

       <div class="form-group mb-2">
           <label for="">Enter Transfer Amount</label>
           <input type="text" name="transfer_amount" class="form-control" placeholder="Enter transfer amount">
       </div>
       <div class="form-group mb-2">
          <button id="" class="btn btn-md transact-btn" >Transfer</button>
           </div>


   <!-- End of Transfer Card -->
  </form>
   <!-- End Of Transfer Form -->

</div>

</div>