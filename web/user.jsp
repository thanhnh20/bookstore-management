<%-- 
    Document   : user
    Created on : Jun 28, 2022, 1:58:52 PM
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Page</title>
    </head>
    <body>
        <a class="link" href="DispatchController?btnAction=Logout">Log out</a>
    </body>
</html>-->
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
    <title>User Page</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
    <link rel="stylesheet" href="css/userPage.css">
</head>

<body class="text-center">
    <div class="head sticky-top">
        <c:set var="account" value="${sessionScope.USER_ROLE}"/>
        <img src="image/Pngtre.png" alt="" class="image">  
        <!--            <font class="link">My Book Store</font>-->
        <c:if test="${not empty account}">
            <div class="user">
                <a class="link" href="DispatchController?btnAction=ShowProfile"><svg xmlns="http://www.w3.org/2000/svg" width="22" height="30" fill="currentColor"
                                                                                     class="bi bi-person-lines-fill" viewBox="0 0 16 16">
                    <path
                        d="M6 8a3 3 0 1 0 0-6 3 3 0 0 0 0 6zm-5 6s-1 0-1-1 1-4 6-4 6 3 6 4-1 1-1 1H1zM11 3.5a.5.5 0 0 1 .5-.5h4a.5.5 0 0 1 0 1h-4a.5.5 0 0 1-.5-.5zm.5 2.5a.5.5 0 0 0 0 1h4a.5.5 0 0 0 0-1h-4zm2 3a.5.5 0 0 0 0 1h2a.5.5 0 0 0 0-1h-2zm0 3a.5.5 0 0 0 0 1h2a.5.5 0 0 0 0-1h-2z" />
                    </svg>
                    ${account.fullName}
                </a>

                <a class="link" href="DispatchController?btnAction=Logout">Log out</a>
            </div>
        </c:if>
        <c:if test="${empty account}">
            <div class="user">
                <label class="link" href="login.jsp"><svg xmlns="http://www.w3.org/2000/svg" width="22" height="30" fill="currentColor"
                                                          class="bi bi-person-lines-fill" viewBox="0 0 16 16">
                    <path
                        d="M6 8a3 3 0 1 0 0-6 3 3 0 0 0 0 6zm-5 6s-1 0-1-1 1-4 6-4 6 3 6 4-1 1-1 1H1zM11 3.5a.5.5 0 0 1 .5-.5h4a.5.5 0 0 1 0 1h-4a.5.5 0 0 1-.5-.5zm.5 2.5a.5.5 0 0 0 0 1h4a.5.5 0 0 0 0-1h-4zm2 3a.5.5 0 0 0 0 1h2a.5.5 0 0 0 0-1h-2zm0 3a.5.5 0 0 0 0 1h2a.5.5 0 0 0 0-1h-2z" />
                    </svg>
                    User
                </label>
                <a class="link" href="login.jsp">Log in</a>
            </div>
        </c:if>

        <a class="link" href="DispatchController?btnAction=ShowCartAsUser">            
            <svg xmlns="http://www.w3.org/2000/svg" width="22" height="30" fill="currentColor" class="bi bi-cart3"
                 viewBox="0 0 16 16">
            <path
                d="M0 1.5A.5.5 0 0 1 .5 1H2a.5.5 0 0 1 .485.379L2.89 3H14.5a.5.5 0 0 1 .49.598l-1 5a.5.5 0 0 1-.465.401l-9.397.472L4.415 11H13a.5.5 0 0 1 0 1H4a.5.5 0 0 1-.491-.408L2.01 3.607 1.61 2H.5a.5.5 0 0 1-.5-.5zM3.102 4l.84 4.479 9.144-.459L13.89 4H3.102zM5 12a2 2 0 1 0 0 4 2 2 0 0 0 0-4zm7 0a2 2 0 1 0 0 4 2 2 0 0 0 0-4zm-7 1a1 1 0 1 1 0 2 1 1 0 0 1 0-2zm7 0a1 1 0 1 1 0 2 1 1 0 0 1 0-2z" />
            </svg>
            My Cart
        </a>

    </div>
    <c:set var="rangeSelected" value="${requestScope.rangeSelected}"/>
    <form class="search" action="DispatchController">
        <input type="text" name="txtSearchValue" value="${param.txtSearchValue}" class="form-control childSearch" type="search" placeholder="Book Name">
        <select name="range" class="form-select childSearch" aria-label="Default select example">
            <option value="0" <c:if test="${'0' eq rangeSelected}">
                    selected
                </c:if>>
                Price Range
            </option>
            <option value="1"<c:if test="${'1' eq rangeSelected}">
                    selected
                </c:if>>
                Min - 30$
            </option>
            <option value="2"<c:if test="${'2' eq rangeSelected}">
                    selected
                </c:if>>
                30$ - 60$
            </option>
            <option value="3"<c:if test="${'3' eq rangeSelected}">
                    selected
                </c:if>>
                60$ Above
            </option>
        </select>
        <button name="btnAction" value="SearchAsUser" class="btn btn-primary childSearch">Search</button>
    </form>
    <div class="numberResult">
        <c:set var="numberResult" value="${requestScope.NUMBER_RESULT}"/>
        <h5>
            Result: ${numberResult}
        </h5>
    </div>
    <div>
        <c:set var="msg" value="${requestScope.MSG}"/>
        <c:if test="${not empty msg}">
            <h5 class="text-success">
                ${msg}
                <a href="DispatchController?btnAction=ShowCartAsUser">Click to view your cart</a>
            </h5>
        </c:if>
    </div>
    <c:if test="${numberResult eq '0'}">
        <h5>
            This search does not has result            
        </h5>
        <a href="DispatchController?btnAction=ShowListBookAsUser" class="btn btn-primary">Show all book</a>
    </c:if>
    <c:set var="numberBook" value="${requestScope.NUMBER_BOOK}"/>

    <div class="row row-cols-md-4">
        <c:forEach var="bookDTO" items="${requestScope.LIST_BOOK}">
            <form action="DispatchController">
                <div class="card shadow-sm" style="width: 15rem;">
                    <img style="height: 240px;" src="image/${bookDTO.imagePath}" class="card-img-top" alt="...">
                    <div style="height: 60px;" class="card-body">
                        <h6 class="card-title">${bookDTO.bookName}</h6>
                    </div>

                    <ul class="list-group list-group-flush">
                        <li class="list-group-item">
                            Quantity: <font style="font-weight: 500;">${bookDTO.quantity}</font>
                        </li>
                        <li class="list-group-item">
                            Price: <font style="font-weight: 750;">${bookDTO.price}$</font>
                        </li>
                        <c:if test="${bookDTO.status eq 'true'}">
                            <li class="list-group-item" style="color: green;font-weight: 500;">Ready</li>
                            </c:if>
                            <c:if test="${bookDTO.status eq 'false'}">
                            <li class="list-group-item" style="color: red;font-weight: 500;">Not Ready</li>
                            </c:if>
                    </ul>
                    <input type="hidden" name="bookID" value="${bookDTO.bookID}" />
                    <input type="hidden" name="txtSearchValue" value="${param.txtSearchValue}" />
                    <input type="hidden" name="range" value="${param.range}" />
                    <div class="card-body">
                        <button <c:if test="${bookDTO.status eq 'false' or bookDTO.quantity lt '1'}">
                                disabled
                            </c:if>
                            class="btn btn-outline-primary" name="btnAction" value="AddToCartAsUser">Add to card</button>
                    </div>
                </div>
            </form>   
        </c:forEach>
    </div>
    <div style="margin-bottom: 50px">
        <c:if test="${numberResult lt numberBook and numberResult ne '0'}">
            <a href="DispatchController?btnAction=ShowListBookAsUser" class="btn btn-primary">Show all book</a>
        </c:if>
    </div>
</body>