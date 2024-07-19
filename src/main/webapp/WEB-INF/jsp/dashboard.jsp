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

<jsp:include page="components/incl/header.jsp"/>

<!-- Transact Offcanvas: pulls from the left -->
<jsp:include page="components/transact_offcanvas.jsp"/>

<!-- Add Account Offcanvas: pulls from the right -->
<jsp:include page="components/add_accounts_offcanvas.jsp"/>

<!-- Container -->
<div class="container">
    <c:if test="${success != null}">
        <div class="alert alert-info text-center border border-info">
            <b>${success}</b>
        </div>
    </c:if>

    <c:if test="${error != null}">
        <div class="alert alert-danger text-center border border-danger">
            <b>${error}</b>
        </div>
    </c:if>
</div>
<!-- End Of Container -->

<c:choose>
    <c:when test="${fn:length(userAccounts) > 0}">
        <!-- Display Accounts -->
        <jsp:include page="components/accounts_display.jsp"/>
    </c:when>
    <c:otherwise>
        <!-- Don't Display Account -->
        <jsp:include page="components/no_accounts_display.jsp"/>
    </c:otherwise>
</c:choose>

 <jsp:include page="components/incl/footer.jsp"/>

</body>
</html>
