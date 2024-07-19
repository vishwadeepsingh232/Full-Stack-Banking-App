<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css" integrity="sha512-SnH5WK+bZxgPHs44uWIX+LLJAJ9/2PkPKZ5QiAj6Ta86w+fsb2TkcmfRyVX3pBnMFcV7oQPJkl9QevSCWr3W6A==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,100..900;1,100..900&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="../css/main.css">
    <script src="../js/bootstrap.bundle.js"></script>

    <title>Dashboard</title>
</head>
<body>

<!-- Header -->
<jsp:include page="components/incl/header.jsp"/>

  <!--Container  -->
      <div class="container">
    <!-- Transaction History card -->
     <div class="card transaction-history shadow">
        <!-- Card Header -->
         <div class="card-header">
            <i class="fas fa-credit-card me-2" aria-hidden="true"></i>Transaction History
         </div>
         <!-- End of Card Header -->
          <!-- Card Body -->
           <div class="card-body">
            <c:if test="${requestScope.transact_history != null}">
                <table class="table text-center table striped">
                    <tr>
                        <th>Transaction ID</th>
                        <th>Transaction Type</th>
                        <th>Amount</th>
                        <th>Source</th>
                        <th>Status</th>
                        <th>Reason Code</th>
                        <th>Created at</th>
                    </tr>
                    <c:forEach items="${requestScope.transact_history}" var="transactionHistory">
                        <tr>
                            <td>${transactionHistory.transaction_id}</td>
                            <td>${transactionHistory.transaction_type}</td>
                            <td>${transactionHistory.amount}</td>
                            <td>${transactionHistory.source}</td>
                            <td>${transactionHistory.status}</td>
                            <td>${transactionHistory.reason_code}</td>
                            <td>${transactionHistory.created_at}</td>
                        </tr>
                    </c:forEach>
                </table>
            </c:if>
           </div>
           <!-- ENd of card body -->
     </div>
     <!-- End of Transaction History card -->
       </div>
       <!-- End of Container -->




<!-- Footer -->
<jsp:include page="components/incl/footer.jsp"/>