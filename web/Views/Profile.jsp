<%-- 
    Document   : newjsp
    Created on : Jun 4, 2024, 10:32:05 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Profile</title>
        <link rel="stylesheet" href="./style.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@10/swiper-bundle.min.css" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA==" crossorigin="anonymous" referrerpolicy="no-referrer">
    </head>
    <body>
        <%@include file="./Headers.jsp" %>
        <section>
            <div class="w-60 m-auto">
                <h1 class="my-4">Account</h1>
                <div class="mt-5">
                    <!--list nav-->
                    <ul class="d-flex py-4 border-top border-bottom text-center px-3">
                        <c:forEach var="service" items="${requestScope.listService}">
                            <li class="me-5">
                                <a href="profile?Service=${service}"
                                   <c:if test="${service==requestScope.current}">
                                    style="text-decoration: underline;
                                    text-decoration-color: var(--pink-color);
                                    "
                                    </c:if>
                                    <c:if test="${service!=requestScope.current}">
                                    </c:if>
                                    class="fs-3 text-dark ">${service}</a>
                            </li>
                        </c:forEach>
                    </ul>
                </div>
                <div class="">
                    <h1 style="color: red">${msg == null ? "" : msg}</h1>

                    <!--mess of action-->
                    <c:if test="${not empty sessionScope.status}">
                        <div class="fs-4 alert ${sessionScope.status eq 'success' ? 'alert-success' : 'alert-danger'}" role="alert">
                            ${sessionScope.message}
                        </div>
                    </c:if>
                    <!--form if is account info-->
                    <c:if test="${requestScope.current.equals('Account info')}">
                        <form action="profile" method="post">
                            <div class="h-100vh mt-5">
                                <h1 class="fw-bold">Account information</h1>
                                <div class="row py-5">
                                    <div class="col-md-3 h-100">
                                        <div class="account-img position-relative">
                                            <img src="#" alt="" id="boxImage">
                                            <div class="change-userImg ">
                                                <i class='bx bx-image-add fs-3'></i>
                                                <span>Change image</span>
                                            </div>
                                            <input type="file" 
                                                   onchange="inputImage(this)"
                                                   accept="image/gif, image/jpeg, image/png"
                                                   class="input-userImg"
                                                   name="image"
                                                   >
                                            <input value="${account.image == null ? "" : account.image}" name="beforeImage" type="hidden"/>
                                        </div>
                                    </div>
                                    <div class="col-md-9">
                                        <div class="user-info">
                                            <div class="mt-5">
                                                <h3 class="fw-medium">Full name</h3>
                                                <div class="input-group flex-nowrap">
                                                    <input type="text" name="fullName"
                                                           value="${account.fullname == null ? "" : account.fullname}" class="form-control px-4 py-3 fs-3 rounded-xl" placeholder="Full name">
                                                </div>
                                            </div>
                                            <div class="mt-5">
                                                <h3>Email</h3>
                                                <div class="input-group mb-3 rounded-xl">
                                                    <span class="input-group-text p-4" id="basic-addon1">
                                                        <i class="fa-regular fa-envelope fs-3"></i>
                                                    </span>
                                                    <input type="email" name="email"
                                                           value="${account.email == null ? "" : account.email}" class="form-control px-4 py-3 fs-3" placeholder="Email" aria-label="Email" aria-describedby="basic-addon1">
                                                </div>
                                            </div>
                                            <div class="mt-5">
                                                <h3>Phone</h3>
                                                <div class="input-group mb-3 rounded-xl">
                                                    <span class="input-group-text p-4" id="basic-addon1">
                                                        <i class="fa-solid fa-phone fs-3"></i>
                                                    </span>
                                                    <input type="text" name="phoneNumber"
                                                           value="${account.phoneNumber == null ? "" : account.phoneNumber}" class="form-control px-4 py-3 fs-3" placeholder="Phone number" aria-label="Phone number" aria-describedby="basic-addon1">
                                                </div>
                                            </div>
                                            <div class="mt-5">
                                                <h3>Address</h3>
                                                <div class="input-group mb-3 rounded-xl">
                                                    <span class="input-group-text p-4" id="basic-addon1">
                                                        <i class="fa-solid fa-location-dot fs-3"></i>
                                                    </span>
                                                    <input type="text" name="address"
                                                           value="${account.address == null ? "" : account.address}" class="form-control px-4 py-3 fs-3" placeholder="Address" aria-label="Address" aria-describedby="basic-addon1">
                                                </div>
                                            </div>
                                            <div class="mt-5">
                                                <h3>Gender</h3>
                                                <div class="input-group mb-3 rounded-xl">
                                                    <span class="input-group-text p-4" id="basic-addon1">
                                                        <i class="fa-solid fa-venus-mars fs-3"></i>
                                                    </span>
                                                    <select name="gender" class="form-control px-4 py-3 fs-3">
                                                        <option value="true" ${account.gender ? "selected" : ""}>Male</option>
                                                        <option value="false"${!account.gender ? "selected" : ""}>Female</option>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="mt-5">
                                                <h3>Date of Birth</h3>
                                                <div class="input-group mb-3 rounded-xl">
                                                    <span class="input-group-text p-4" id="basic-addon1">
                                                        <i class="fa-solid fa-calendar fs-3"></i>
                                                        <input type="text" name="dob"
                                                               value="${account.dob == null ? "" : account.dob}" class="form-control px-4 py-3 fs-3" placeholder="Dob" aria-label="Dob" aria-describedby="basic-addon1">
                                                    </span>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="mt-5">
                                            <button type="submit" class="border-0 px-5 py-4 fs-4 bg-dark text-white rounded-xl fw-bold">Update information</button>
                                        </div>
                                    </div>        
                                </div>
                            </div>
                        </form>
                    </c:if>

                    <!-- form for Change password -->
                    <c:if test="${requestScope.current.equals('Change password')  || requestScope.current.equals('updatePassword')}">

                        <form action="changepass" method="post"> <!-- Assuming "changepass" is your servlet URL for changing password -->
                            <input name="Service" value ="updatePassword" hidden />
                            <div class="h-100vh mt-5">
                                <h1 class="fw-bold">Update your password</h1>
                                <div class="change-password">
                                    <div class="mt-5">
                                        <h3 class="fw-medium">Current password</h3>
                                        <div class="input-group flex-nowrap">
                                            <input type="password" name="currentPassword"  
                                                   class="form-control px-4 py-3 fs-3 rounded-xl" placeholder="password">
                                        </div>
                                    </div>
                                    <div class="mt-5">
                                        <h3 class="fw-medium">New password</h3>
                                        <div class="input-group flex-nowrap">
                                            <input type="password" name="newPassword"
                                                   class="form-control px-4 py-3 fs-3 rounded-xl" placeholder="password">
                                        </div>
                                    </div>
                                    <div class="mt-5">
                                        <h3 class="fw-medium">Confirm password</h3>
                                        <div class="input-group flex-nowrap">
                                            <input type="password" name="confirmPassword"
                                                   class="form-control px-4 py-3 fs-3 rounded-xl" placeholder="password">
                                        </div>
                                    </div>
                                    <div class="mt-5">
                                        <button type="submit" name="submit" value="changePass" class="border-0 px-5 py-4 fs-4 bg-dark text-white rounded-xl fw-bold">Update password</button>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </c:if>
                    <!-- other sections -->
                </div>
        </section>
        <%--<%@include file="./Footer.jsp" %>--%>
        <script src="./js/app.js"></script>
        <script>
                                                       function inputImage(input) {
                                                           var filePath = input.value;
                                                           var fileName = filePath.split('\\').pop();
                                                           var imagePreview = document.getElementById('boxImage');
                                                           imagePreview.src = "./images/" + fileName;
                                                           console.log(fileName);
                                                       }
        </script>
    </body>
</html>
