 <%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
 <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


      <!-- Withdraw card -->
     <div class="card withdraw-card">
        <div class="card-body">
            <form action="/transact/withdraw" method="POST" ">
                <div class="form-group mb-2">
                    <label for="">Enter Withdraw Amount</label>
                    <input type="text" name="withdraw_amount" class="form-control" placeholder="Enter withdraw amount">
                </div>

             <div class="form-group">
            <label for="account_id">Select Account</label>
            <select  name="account_id" class="form-control" style="background-color: lightgrey">
             <option value="">--Select Account--</option>
              <c:if test="${userAccounts != null}">
              <c:forEach items="${userAccounts}" var="selectAccount">
               <option value="${selectAccount.account_id}">${selectAccount.account_name}</option>
                </c:forEach>
                </c:if>
                </select>
               </div>

        <div class="form-group my-2">
           <button id="" class="btn btn-md transact-btn" >Withdraw</button>
            </div>

            </form>
        </div>
      </div>
      <!-- End of withdraw card -->

