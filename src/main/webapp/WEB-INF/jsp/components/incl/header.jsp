 <%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
 <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

 <header>
    <div class="main-page-header mb-3 bg-primary">
    <div class="container d-flex align-item-center">
    <div class="company-name">
        Easy Way Bank
    </div>
    <nav class="navigation">
     <li><a href="/app/dashboard">Dashboard</a></li>
     <li><a href="/app/payment_history">Payment History</a></li>
     <li><a href="/app/transact_history">Transaction History</a></li>
    </nav>

    <div class="display-name ms-auto text-white">
     <i class="fa fa-circle text-success me-2"></i> Welcome:  <span>${user.first_name}  ${user.last_name}</span>
    </div>

    <a href="/logout" class="btn btn-sm text-white ms-3">
        <i class="fas fa-sign-out-alt me-1" aria-hidden="true"></i>Sign Out
    </a>
    </div>
    </div>
</header>
