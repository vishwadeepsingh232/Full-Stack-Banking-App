<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css" integrity="sha512-SnH5WK+bZxgPHs44uWIX+LLJAJ9/2PkPKZ5QiAj6Ta86w+fsb2TkcmfRyVX3pBnMFcV7oQPJkl9QevSCWr3W6A==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,100..900;1,100..900&display=swap" rel="stylesheet">
    <title>Error</title>
    <style>
        *{
            box-sizing: border-box;
            font-family: comfortaa;
        }
        body{
            height:96vh;
            background-image:url("./images/home.jpg");
            background-size: cover;
            background-position: center center;
            background-repeat: no-repeat;
        }
     .card .card-text{
        font-size: 18px;
     }
     .card{
        box-shadow: 0px 3px 6px rgb(0,14,53);
     }
    </style>
</head>
<body class="d-flex align-items-center justify-content-center">
    <div class="card col-4 alert alert-danger border border-danger text-danger">
        <h3 class="card-title">
            <i class="fa fa-window-close me-2"></i>Errors:
        </h3>
        <hr>
        <div class="card-body">
            <p class="card-text">
                <c:if test="${requestScope.error != null}">
                       <div class="alert alert-danger text-center border border-danger">
                       <b>${requestScope.error}</b>
                       </div>
                       </c:if>
            </p>
            <hr>
            <a href="/login" class="btn btn-sm btn-danger">
                <i class="fa fa-arrow-alt-circle-left me-1"></i>Back
            </a>
        </div>
    </div>
</body>
</html>