<!DOCTYPE html>
<html lang="en">
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <head>
        <title>Cart Page</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
        <link rel="stylesheet" href="css/cartList.css">
    </head>

    <body class="text-center">
        <c:set var="account" value="${sessionScope.USER_ROLE}"/>
        <div class="head sticky-top">          
            <img src="image/Pngtre.png" alt="" class="image">
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
            <a class="link" href="DispatchController?btnAction=ShowListBookAsUser">
                <svg xmlns="http://www.w3.org/2000/svg" width="22" height="30" fill="currentColor" class="bi bi-cart3"
                     viewBox="0 0 16 16">
                <path
                    d="M0 1.5A.5.5 0 0 1 .5 1H2a.5.5 0 0 1 .485.379L2.89 3H14.5a.5.5 0 0 1 .49.598l-1 5a.5.5 0 0 1-.465.401l-9.397.472L4.415 11H13a.5.5 0 0 1 0 1H4a.5.5 0 0 1-.491-.408L2.01 3.607 1.61 2H.5a.5.5 0 0 1-.5-.5zM3.102 4l.84 4.479 9.144-.459L13.89 4H3.102zM5 12a2 2 0 1 0 0 4 2 2 0 0 0 0-4zm7 0a2 2 0 1 0 0 4 2 2 0 0 0 0-4zm-7 1a1 1 0 1 1 0 2 1 1 0 0 1 0-2zm7 0a1 1 0 1 1 0 2 1 1 0 0 1 0-2z" />
                </svg>
                Buy book
            </a>
        </div>
        <div style="margin-top: 30px">
            <c:set  var="msg" value="${requestScope.MSG}"/>
            <c:if test="${not empty msg}">   
                <h6 class="text-danger">
                    ${msg}  
                </h6>                     
            </c:if>
        </div>
        <c:set var="listBookInCart" value="${requestScope.LIST_BOOK_IN_CART}"/>
        <div style="margin: 30px">
            <c:if test="${empty listBookInCart}">
                <h5>
                    Your cart is empty        
                    <a class="" href="DispatchController?btnAction=ShowListBookAsUser">Click to buy Book</a>
                </h5>
            </c:if>
        </div>

        <c:if test="${not empty listBookInCart}">
            <div style="margin-top: 20px">
                <c:set var="error" value="${requestScope.ERROR}"/>
                <c:if test="${not empty error}">    
                    <h6 class="text-danger">
                        ${error.outOfQuantityError}
                        ${error.quantityOutOfStockError}
                        ${error.invalidQuantity}
                    </h6>
                </c:if>               
            </div> 
            <div class="book-list">
                <form action="DispatchController">
                    <table class="table table-bordered table-hover">
                        <thead>
                            <tr>
                                <th>NO.</th>
                                <th>Book Name</th>
                                <th>Quantity</th>
                                <th>Price</th>
                                <th>Status</th>
                                <th>Select</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="book" items="${listBookInCart}" varStatus="count">                     
                                <tr>
                                    <td>${count.count}</td>
                                    <td>${book.bookName}</td>
                                    <td>
                                        <input style="width: 100px; margin: auto" class="form-control text-center" type="number" name="${book.bookID}"                          
                                               value="${book.quantity}" />
                                    </td>

                                    <td>${book.price}$</td>
                                    <td>
                                        <c:if test="${book.status eq 'true'}">
                                            <font color="Green">Stocking</font>
                                        </c:if>
                                        <c:if test="${book.status eq 'false'}">
                                            <font color="red">Out of Stock</font>
                                        </c:if>
                                    </td>
                                    <td>
                                        <input name="checkedItems" value="${book.bookID}" type="checkbox" class="form-check-input">
                                    </td>
                                </tr>   
                            </c:forEach> 
                        </tbody>
                    </table>
                    <button
                        name="btnAction" value="CheckoutAsUser" class="btn btn-outline-danger">
                        Check out
                    </button>
                    <button name="btnAction" value="RemoveCartAsUser" class="btn btn-outline-danger">
                        Remove from cart
                    </button>
                </form>
            </div>
        </c:if>
    </body>