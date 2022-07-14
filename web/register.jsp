<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <title>Register Page</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2"
        crossorigin="anonymous"></script>
        <link rel="stylesheet" href="css/register.css">
    </head>
    <body class="text-center">
        <div class="form">
            <div>
                <a href="DispatchController?btnAction=ShowListBookAsUser">
                    <img src="image/Pngtre.png" alt="" width="140px" height="140px"> <br>
                </a>
            </div>
            <form action="DispatchController" method="POST">
                <div class="error text-danger text-left">
                    <c:set var="error" value="${requestScope.ERROR}"/>
                    <c:if test="${not empty error}">
                        ${error.usernameDuplicate}
                    </c:if>
                </div>
                <div class="form-floating w-100">
                    <input type="text" class="form-control" id="formInput" placeholder="username" name="username" value="${param.username}">
                    <label for="formInput">*Username</label>
                </div>
                <div class="error text-danger text-left">
                    <c:if test="${not empty error}">
                        ${error.usernameEmpty}
                    </c:if>
                </div>
                <div class="form-floating w-100">
                    <input type="password" class="form-control" id="formPassword" placeholder="password" name="password">
                    <label for="formPassword">*Password</label>
                </div>
                <div class="error text-danger text-left">
                    <c:if test="${not empty error}">
                        ${error.passwordEmpty}
                    </c:if>
                </div>
                <div class="form-floating w-100">
                    <input type="password" class="form-control" id="formConfirmPassword" placeholder="confirmPassword" name="confirmPassword">
                    <label for="formConfirmPassword">*Confirm Password</label>
                </div>
                <div class="error text-danger text-left">
                    <c:if test="${not empty error}">
                        ${error.wrongPassword}
                    </c:if>
                </div>
                <div class="form-floating w-100">
                    <input type="text" class="form-control" id="formFullName" placeholder="fullname" name="fullname" value="${param.fullname}">
                    <label for="formFullName">*Full Name</label>
                </div>
                <div class="error text-danger text-left">
                    <c:if test="${not empty error}">
                        ${error.fullName}
                    </c:if>
                </div>
                <div class="acction">
                    <button name="btnAction" value="Register" class="btn btn-outline-primary" type="submit">
                        Register
                    </button>
                    <a href="login.jsp" class="btn btn-outline-danger" type="submit">
                        Log In
                    </a>
                </div>
            </form>
        </div>
    </body>