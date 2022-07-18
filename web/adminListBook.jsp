<%@page import="com.prj.tblbook.TblBookDTO"%>
<%@page import="java.util.List"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <title>Admin Page</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2"
        crossorigin="anonymous"></script>
        <link rel="stylesheet" href="css/adminListBook.css">
    </head>

    <body class="text-center">
        <div class="head sticky-top">
            <img src="image/Pngtre.png" alt="" class="image">
            <div class="admin">
                <label class="link"><svg xmlns="http://www.w3.org/2000/svg" width="22" height="30" fill="currentColor"
                                         class="bi bi-person-lines-fill" viewBox="0 0 16 16">
                    <path
                        d="M6 8a3 3 0 1 0 0-6 3 3 0 0 0 0 6zm-5 6s-1 0-1-1 1-4 6-4 6 3 6 4-1 1-1 1H1zM11 3.5a.5.5 0 0 1 .5-.5h4a.5.5 0 0 1 0 1h-4a.5.5 0 0 1-.5-.5zm.5 2.5a.5.5 0 0 0 0 1h4a.5.5 0 0 0 0-1h-4zm2 3a.5.5 0 0 0 0 1h2a.5.5 0 0 0 0-1h-2zm0 3a.5.5 0 0 0 0 1h2a.5.5 0 0 0 0-1h-2z" />
                    </svg>
                    Admin</label>
                <a class="link" href="DispatchController?btnAction=Logout">Log out</a>
            </div>
            <a class="link" href="DispatchController?btnAction=ShowListUser">
                <svg xmlns="http://www.w3.org/2000/svg" width="22" height="22" fill="currentColor" class="bi bi-list-ul"
                     viewBox="0 0 16 16">
                <path fill-rule="evenodd"
                      d="M5 11.5a.5.5 0 0 1 .5-.5h9a.5.5 0 0 1 0 1h-9a.5.5 0 0 1-.5-.5zm0-4a.5.5 0 0 1 .5-.5h9a.5.5 0 0 1 0 1h-9a.5.5 0 0 1-.5-.5zm0-4a.5.5 0 0 1 .5-.5h9a.5.5 0 0 1 0 1h-9a.5.5 0 0 1-.5-.5zm-3 1a1 1 0 1 0 0-2 1 1 0 0 0 0 2zm0 4a1 1 0 1 0 0-2 1 1 0 0 0 0 2zm0 4a1 1 0 1 0 0-2 1 1 0 0 0 0 2z" />
                </svg>
                List User
            </a>
        </div>

        <%-- --%>
        <form class="search" action="DispatchController">
            <input type="text" class="form-control" name="searchValue" value="${param.searchValue}" placeholder="Search Name Book"/>
            <button class="btn btn-primary" type="submit" name="btnAction" value="SearchBook" placeholder="Search Name Book">Search</button>
        </form>
        <c:set var="list" value="${requestScope.LIST_BOOK}"/>
        <c:if test="${empty list}">
            <h5 style="margin: 20px">
                This search does not has result            
            </h5>
            <a href="DispatchController?btnAction=ShowAllBook" class="btn btn-primary" type="submit" name="btnAction" value="ShowAllBook">
                List All Book
            </a>
        </c:if>
        <c:set var="msg" value="${requestScope.MSG}"/>
        <c:set var="msgError" value="${requestScope.MSG_ERROR}"/>
        <c:if test="${not empty msg}">
            <h5 style="margin: 20px" class="text-success">
                ${msg}         
            </h5>
        </c:if>
        <c:if test="${not empty msgError}">
            <h5 style="margin: 20px" class="text-danger">
                ${msgError}         
            </h5>
        </c:if>
        <c:if test="${not empty list}">
            <div class="book-list">
                <table class="table table-bordered table-hover">
                    <thead>
                        <tr>
                            <th>NO.</th>
                            <th>BookID</th>
                            <th>Book Name</th>
                            <th>Quantity</th>
                            <th>Price</th>
                            <th>Status</th>
                            <th>Image Path</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%--
                            int count = 1;
                            for (TblBookDTO listBook : list) {
                        --%>
                        <c:forEach var="bookDTO" items="${list}" varStatus="count">
                        <form action="DispatchController">
                            <tr>
                                <td>
                                    <%--<%= count++%>--%>
                                    ${count.count}
                                </td>
                                <td>
                                    <%--= listBook.getBookID()--%>
                                    ${bookDTO.bookID}
                                </td>
                                <td>
                                    <input class="form-control text-center" required type="text" name="txtBookName" value="${bookDTO.bookName}"<%--value="<%=listBook.getBookName()%>"--%>/>
                                </td>
                                <td>
                                    <input class="form-control text-center" required style="width: 80px; margin: auto"  type="text" pattern="[0-9]+" name="txtQuantity" value="${bookDTO.quantity}" <%--value="<%=listBook.getQuantity()%>"--%> />
                                </td>
                                <td>
                                    <input class="form-control text-center" required style="width: 80px; margin: auto"  type="number" pattern="[0-9]+" step="any" name="txtPrice" value="${bookDTO.price}" <%--value="<%=listBook.getPrice()%>"--%> />
                                </td>
                                <td>
                                    <%--=listBook.isStatus()
                                    <c:if test="${bookDTO.status eq 'true'}">
                                        <font color="Green">Ready</font>
                                    </c:if>
                                    <c:if test="${bookDTO.status eq 'false'}">
                                        <font color="red">Not Ready</font>
                                    </c:if>
                                    --%>
                                    <select name="status" class="form-control text-center btn <c:if test="${bookDTO.status eq 'false'}">
                                            btn-danger
                                        </c:if>
                                        <c:if test="${bookDTO.status eq 'true'}">
                                            btn-success
                                        </c:if>" aria-label="Default select example">
                                        <option class="btn btn-success" <c:if test="${bookDTO.status eq 'true'}">
                                                selected
                                            </c:if>
                                            value="true">
                                            Ready
                                        </option>
                                        <option class="btn btn-danger" <c:if test="${bookDTO.status eq 'false'}">
                                                selected
                                            </c:if>
                                            value="false">
                                            Not ready
                                        </option>
                                    </select>
                                </td>
                                <td>
                                    <%--<a class="link-action link-secondary" href="<%= listBook.getImagePath() %>">Click here</a> --%>
                                    <input class="form-control text-center" type="text" name="txtImagePath" value="${bookDTO.imagePath}" <%--value="<%= listBook.getImagePath()%>"--%> />
                                </td>
                                <td>
                                    <input type="hidden" name="searchValue" value="${requestScope.searchValue}"/>
                                    <input type="hidden" name="txtBookID" value="${bookDTO.bookID}" <%--value="<%=listBook.getBookID()%>"--%> />
                                    <input class="btn btn-outline-primary" type="submit" name="btnAction" value="UpdateBook" />      

                                </td>
                                <!--                                <td>                                   
                                                                    <a class="btn btn-success" style="color: #FF2A00" href="DispatchController?btnAction=DeleteBook&txtBookID=${bookDTO.bookID}&searchValue=${requestScope.searchValue}">Delete</a>
                                                                </td>-->
                                <!--                                <td>
                                <%--<a class="btn btn-danger" href="DispatchController?btnAction=DeleteBook&txtBookID=<%=listBook.getBookID()%>&searchValue=${requestScope.searchValue}">Delete</a> 
                                <c:url var="url" value="DispatchController">
                                    <c:param name="btnAction" value="DeleteBook"/>
                                    <c:param name="txtBookID" value="${bookDTO.bookID}"/>
                                    <c:param name="searchValue" value="${param.searchValue}"/>
                                </c:url>
                                <a class="btn btn-danger" href="${url}">Delete</a>
                                --%>
                            </td>                                       -->

                            </tr>
                        </form>
                    </c:forEach>
                    <%--
                        }
                    --%>
                    </tbody>
                </table>
                <%--
                        }
                    }
                --%>

            </div>
            <div style="margin: 20px 0 50px">
                <a class="btn btn-outline-primary" href="addNewBook.html">Add New Book</a>
                <!--            <form action="addNewBook.jsp">
                                <button class="btn btn-outline-primary">
                                    Add New Book
                                </button>
                            </form>-->
                <!--            <form action="DispatchController">
                                <button class="btn btn-primary" type="submit" name="btnAction" value="ShowAllBook">
                                    List All Book
                                </button>
                            </form>-->
            </div>
        </c:if>
        <%--
            List<TblBookDTO> list = (List<TblBookDTO>) request.getAttribute("LIST_BOOK");
            if (list != null) {
                if (!list.isEmpty()) {
                    request.setAttribute("LIST_BOOK", list);
        --%> 


    </body>