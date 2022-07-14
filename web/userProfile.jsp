<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">

    <head>
        <title>Profile Page</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
        <link rel="stylesheet" href="css/userProfile.css">
    </head>

    <body>
        <c:set var="user" value="${sessionScope.USER_ROLE}" />
        <div class="head sticky-top">
            <img src="image/Pngtre.png" alt="" class="image">
            <div class="user">
                <a class="link" href="DispatchController?btnAction=ShowProfile"><svg xmlns="http://www.w3.org/2000/svg" width="22" height="30" fill="currentColor"
                                                                                     class="bi bi-person-lines-fill" viewBox="0 0 16 16">
                    <path
                        d="M6 8a3 3 0 1 0 0-6 3 3 0 0 0 0 6zm-5 6s-1 0-1-1 1-4 6-4 6 3 6 4-1 1-1 1H1zM11 3.5a.5.5 0 0 1 .5-.5h4a.5.5 0 0 1 0 1h-4a.5.5 0 0 1-.5-.5zm.5 2.5a.5.5 0 0 0 0 1h4a.5.5 0 0 0 0-1h-4zm2 3a.5.5 0 0 0 0 1h2a.5.5 0 0 0 0-1h-2zm0 3a.5.5 0 0 0 0 1h2a.5.5 0 0 0 0-1h-2z" />
                    </svg>
                    ${user.fullName}</a>
                <a class="link" href="DispatchController?btnAction=Logout">Log out</a>
            </div>
            <a class="link" href="DispatchController?btnAction=ShowListBookAsUser">
                <svg xmlns="http://www.w3.org/2000/svg" width="22" height="30" fill="currentColor" class="bi bi-cart3"
                     viewBox="0 0 16 16">
                <path
                    d="M0 1.5A.5.5 0 0 1 .5 1H2a.5.5 0 0 1 .485.379L2.89 3H14.5a.5.5 0 0 1 .49.598l-1 5a.5.5 0 0 1-.465.401l-9.397.472L4.415 11H13a.5.5 0 0 1 0 1H4a.5.5 0 0 1-.491-.408L2.01 3.607 1.61 2H.5a.5.5 0 0 1-.5-.5zM3.102 4l.84 4.479 9.144-.459L13.89 4H3.102zM5 12a2 2 0 1 0 0 4 2 2 0 0 0 0-4zm7 0a2 2 0 1 0 0 4 2 2 0 0 0 0-4zm-7 1a1 1 0 1 1 0 2 1 1 0 0 1 0-2zm7 0a1 1 0 1 1 0 2 1 1 0 0 1 0-2z" />
                </svg>
                Buy book
            </a>
        </div>
        <div class="text-center" style="margin-top: 40px">
            <h4>
                Personal Profile
            </h4>
        </div>
        <c:set var="msg" value="${requestScope.MSG}"/>
        <div class="text-center" style="margin-top: 40px">
            <c:if test="${not empty msg}">
                <h6 class="text-success">
                    ${msg}
                </h6>
            </c:if>
        </div>    
        <div class="text-center">
            <a href="DispatchController?btnAction=ShowOrderHistory" class="btn btn-info w-25 ">
                Order History
            </a>
        </div>
        <c:set var="userProfile" value="${requestScope.USER_PROFILE}"/>
        <c:set var="error" value="${requestScope.ERROR}"/>

        <form class="form" action="DispatchController">
            <div class="form-div">
                <div class="row">
                    <label class="col-sm-4 col-form-label ">Username</label>
                    <div class="col-sm-8">
                        <input name="username" type="text" disabled class="form-control profile-input" value="${userProfile.account.username}">
                    </div>
                </div>
                <div class="row">
                    <label class="col-sm-4 col-form-label ">Full Name</label>
                    <div class="col-sm-8">
                        <input disabled type="text" class="form-control profile-input" value="${userProfile.account.fullName}">
                    </div>
                </div>

                <div class="row">
                    <label for="updateGender" class=" col-sm-4 col-form-label">Gender</label>
                    <select name="gender" class="gender form-control col-sm-8 profile-input" aria-label="Default select example">
                        <option <c:if test="${userProfile.gender eq '1'}">
                                selected
                            </c:if>
                            value="true">
                            Male
                        </option>
                        <option <c:if test="${userProfile.gender eq '0'}">
                                selected
                            </c:if>
                            value="false">
                            Female
                        </option>

                    </select>
                </div>
                <div class="row">
                    <label for="updateAddress" class="col-sm-4 col-form-label ">Address</label>
                    <div class="col-sm-8">
                        <input name="address" type="text" class="form-control profile-input" id="updateAddress" value="${userProfile.address}">
                        <div class="error text-danger text-left">
                            ${error.errorAddressLength}
                        </div>
                    </div>

                </div>
                <div class="row">
                    <label class="col-sm-4 col-form-label" for="birthday">Date of Birth</label>
                    <div class="col-sm-8">
                        <input name="birthdate" type="date" class="form-control profile-input" name="dateUpdate" id="birthday" value="${userProfile.birthday}">
                        <div class="error text-danger text-left">
                            ${error.errorDateInvalid}
                            ${error.errorDateEmpty}
                        </div>
                    </div>
                </div>
                <div class="row">
                    <label for="updatePhone" class="col-sm-4 col-form-label">Phone Number</label>
                    <div class="col-sm-8">
                        <input name="phone" type="number" class="form-control profile-input" id="updatePhone" value="${userProfile.phone}">
                        <div class="error text-danger text-left">
                            ${error.errorPhoneNumberLength}
                            ${error.errorPhoneNumberFormat}
                        </div>
                    </div>
                </div>
                <div class="row">
                    <button name="btnAction" value="UpdateProfile" class="btn btn-outline-primary w-25 ">
                        Update
                    </button>
                </div>
            </div>
        </form>
    </body>