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
            <img src="image/book.jpg" alt="" class="image">
            <div class="admin">
                <label class="link"><svg xmlns="http://www.w3.org/2000/svg" width="22" height="30" fill="currentColor"
                                         class="bi bi-person-lines-fill" viewBox="0 0 16 16">
                    <path
                        d="M6 8a3 3 0 1 0 0-6 3 3 0 0 0 0 6zm-5 6s-1 0-1-1 1-4 6-4 6 3 6 4-1 1-1 1H1zM11 3.5a.5.5 0 0 1 .5-.5h4a.5.5 0 0 1 0 1h-4a.5.5 0 0 1-.5-.5zm.5 2.5a.5.5 0 0 0 0 1h4a.5.5 0 0 0 0-1h-4zm2 3a.5.5 0 0 0 0 1h2a.5.5 0 0 0 0-1h-2zm0 3a.5.5 0 0 0 0 1h2a.5.5 0 0 0 0-1h-2z" />
                    </svg>
                    Admin</label>
                <a class="link" href="login.html">Log out</a>
            </div>
            <a class="link" href="adminListUser.html">
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
            <input type="text" class="form-control" name="searchValue" value="${requestScope.searchValue}" placeholder="Search Name Book" required=""/>
            <button class="btn btn-primary" type="submit" name="btnAction" value="SearchBook" placeholder="Search Name Book">Search</button>
        </form>
        <%
            List<TblBookDTO> list = (List<TblBookDTO>) request.getAttribute("LIST_BOOK");
            if (list != null) {
                if (!list.isEmpty()) {
                    request.setAttribute("LIST_BOOK", list);
        %> 

        <div class="book-list">
            <table class="table table-bordered table-hover">
                <thead>
                    <tr>
                        <th>NO.</th>
                        <th>Book ID</th>
                        <th>Book Name</th>
                        <th>Quantity</th>
                        <th>Price</th>
                        <th>Status</th>
                        <th>Image Path</th>
                        <th colspan="2">Action</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        int count = 1;
                        for (TblBookDTO listBook : list) {
                    %>
                <form action="DispatchController">
                    <tr>
                        <td><%= count++%></td>
                        <td><%= listBook.getBookID()%></td>
                        <td>
                            <input type="text" name="txtBookName" value="<%=listBook.getBookName()%>" />
                        </td>
                        <td>
                            <input type="text" name="txtQuantity" value="<%=listBook.getQuantity()%>" />
                        </td>
                        <td>
                            <input type="text" name="txtPrice" value="<%=listBook.getPrice()%>" />
                        </td>
                        <td><%=listBook.isStatus()%></td>
                        <td>
                            <%--<a class="link-action link-secondary" href="<%= listBook.getImagePath() %>">Click here</a> --%>
                            <input type="text" name="txtImagePath" value="<%= listBook.getImagePath()%>" />
                        </td>
                        <td>
                            <a class="link-action link-danger" href="DispatchController?btnAction=DeleteBook&txtBookID=<%=listBook.getBookID()%>&searchValue=${requestScope.searchValue}">Delete</a>
                        </td>                                       
                        <td>
                            <input class="btn btn-outline-primary" type="submit" name="btnAction" value="Update Book" />
                            <input type="hidden" name="txtBookID" value="<%=listBook.getBookID()%>" />
                            <input type="hidden" name="searchValue" value="${requestScope.searchValue}"/>
                        </td>
                    </tr>
                </form>
                <%
                    }
                %>
                </tbody>
            </table>
            <%
                    }
                }
            %>
            <form action="addNewBook.html">
                <button class="btn btn-outline-primary">
                    Add New Book
                </button>
            </form>
            <form action="DispatchController">
                <button class="btn btn-primary" type="submit" name="btnAction" value="ShowAllBook">
                    List All Book
                </button>
            </form>
        </div>

    </body>