<%-- 
    Document   : Headers
    Created on : May 21, 2024, 1:52:36 AM
    Author     : trantoan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <%@page import="DAL.*" %>
    <%@page import="DAL.Process.*" %>
    <%@page import="Models.*" %>
    <%@page import="java.util.*" %>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Document</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@10/swiper-bundle.min.css" />
        <!--<link rel="stylesheet" href="../style.css">-->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA==" crossorigin="anonymous" referrerpolicy="no-referrer"
              />

        <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
        <link href="./css/header.css" rel="stylesheet" type="text/css"/>
    </head>

    <body>
        <%
            User account = null;
            if(session.getAttribute("account") != null) {
                account = (User)session.getAttribute("account");
            }
        %>
        <section id="header">
            <div class="container">
                <div class="row align-items-center">
                    <div class="col-md-1">
                        <div class="header-logo">
                            <a href="home"><img src="" alt="Header"></a>
                        </div>
                    </div>
                    <div class="col-md-5">
                        <nav class="navbar navbar-expand-lg bg-body-tertiary bg-danger">
                            <div class="container-fluid">
                                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
                                    <span class="navbar-toggler-icon"></span>
                                </button>
                                <div class="collapse navbar-collapse" id="navbarNavDropdown">
                                    <ul class="navbar-nav">
                                        <li class="nav-item">
                                            <a class="nav-link" aria-current="page" href="#">Men</a>
                                        </li>
                                        <li class="nav-item">
                                            <a class="nav-link" href="#">Women</a>
                                        </li>
                                        <li class="nav-item">
                                            <a class="nav-link" href="#">Kids</a>
                                        </li>
                                        <li class="nav-item has-child">
                                            <a class="nav-link" href="#">
                                                Home
                                            </a>
                                            <ul class="submenu">
                                                <li class="nav-item"><a class="nav-link" href="">Kids</a></li>
                                                <li class="nav-item"><a class="nav-link" href="">Kids</a></li>
                                                <li class="nav-item has-child"><a class="nav-link" href="">Kids</a>
                                                    <ul class="submenu">
                                                        <li class="nav-item"><a class="nav-link" href="">Kids</a></li>
                                                        <li class="nav-item"><a class="nav-link" href="">Kids</a></li>
                                                        <li class="nav-item"><a class="nav-link" href="">Kids</a></li>
                                                        <li class="nav-item"><a class="nav-link" href="">Kids</a></li>
                                                    </ul>
                                                </li>
                                                <li class="nav-item"><a class="nav-link" href="">Kids</a></li>
                                            </ul>
                                        </li>
                                        <li class="nav-item">
                                            <a class="nav-link" href="#">Beauty</a>
                                        </li>
                                        <li class="nav-item">
                                            <a class="nav-link" href="#">Studio</a>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </nav>
                    </div>
                    <div class="col-md-4">
                        <div class="input-box d-flex align-items-center">
                            <div class="search-icon" onclick="handleSearch()">
                                <div class="border-0 d-flex">
                                    <i class='bx bx-search'></i>
                                </div>
                            </div>
                            <div class="search-input" >
                                <input type="text" id="header-search"
                                       placeholder="Search for products, brands and more" 
                                       aria-label="Example text with button addon"
                                       />
                            </div>
                        </div>
                    </div>
                    <div class="col-md-2">
                        <div class="row">
                            <div class="col">
                                <div class="user-status">
                                    <div class="user-logo">
                                        <i class='bx bx-user'></i>
                                    </div>
                                    <span class="user-title">Profile</span>
                                    <div class="user-box_access">
                                        <div class="user-box-header">
                                            <!-- access -->
                                            <%if(account != null) {%>
                                            <div class="user-access">
                                                <%if(account.getImage() != null) {%>
                                                <a href="${pageContext.request.contextPath}/profile" class="d-block">
                                                    <img src="./images/<%=account.getImage()%>" alt="">
                                                </a>
                                                <%} else {%>
                                                <a href="${pageContext.request.contextPath}/profile" class="d-block">
                                                    <img src="./images/accountImg.png" alt="">
                                                </a>
                                                <%}%>

                                                <div class="user-access-info">
                                                    <span><%=account.getFullname()%></span>
                                                    <p><%=account.getPhoneNumber()%></p>
                                                </div>
                                                <a href="./logout" class="logout-link">
                                                    <div class="logout-button">
                                                        Logout
                                                    </div>
                                                </a>
                                            </div>
                                            <%} else {%>
                                            <!-- not access -->
                                            <div class="user-not-access">
                                                <span class="not-access-title">Wellcome</span>
                                                <p>To access account and manage orders</p>
                                                <span class="login-link">
                                                    <div class="login-button">
                                                        <a href="./Login" >Login</a>/<a href="./createaccount" >Signup</a>
                                                    </div>
                                                </span>
                                            </div>

                                            <%}%>
                                        </div>
                                        <hr>
                                        <ul class="user-access-link">
                                            <li><a href="Cart">Orders</a></li>
                                            <li><a href="#">Wishlist</a></li>
                                            <li><a href="#">Gift Cards</a></li>
                                            <li><a href="#">Contact Us</a></li>
                                            <li class="access-new">
                                                <a href="#">Myntra Insider</a>
                                                <div class="special">New</div>
                                            </li>
                                        </ul>
                                        <hr>
                                        <ul class="page-access-link">
                                            <li><a href="#">Myntra Credit</a></li>
                                            <li><a href="#">Coupons</a></li>
                                            <li><a href="#">Saved Cards</a></li>
                                            <li><a href="#">Saved VPA</a></li>
                                            <li><a href="#">Saved Addresses</a></li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                            <div class="col">
                                <div class="message-status">
                                    <div class="bell-logo">
                                        <i class='bx bx-bell'></i>
                                    </div>
                                    <span class="love-title">Notification
                                    </span>
                                </div>
                            </div>
                            <div class="col">
                                <a href="Cart" class="cart-status text-black text-decoration-none">
                                    <div class="cart-logo">
                                        <i class='bx bx-shopping-bag'></i>
                                    </div>
                                    <span class="cart-title">Bag</span>
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <script>
            var swiper = new Swiper(".mySwiper", {
                spaceBetween: 30,
                centeredSlides: true,
                loop: true,
                speed: 1500,

                autoplay: {
                    delay: 5000,
                    disableOnInteraction: false,
                },
                pagination: {
                    el: ".swiper-pagination",
                    clickable: true,
                },
                navigation: {
                    nextEl: ".swiper-button-next",
                    prevEl: ".swiper-button-prev",
                },
            });
        </script>
        <script>

        </script>

        <!--<script src="./js/app.js"></script>-->
    </body>
</html>
