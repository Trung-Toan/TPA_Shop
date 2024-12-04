<!DOCTYPE html>
<html lang="en">
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@page contentType="text/html" pageEncoding="UTF-8"%>
    <%@page import="java.text.Normalizer" %>
    <%@page import="java.util.regex.Pattern" %>
    <%@page import="DAL.*" %>
    <%@page import="DAL.Process.*" %>
    <%@page import="Models.*" %>
    <%@page import="java.util.*" %>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Shopping Cart</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@10/swiper-bundle.min.css" />
        <link rel="stylesheet" href="./style.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA==" crossorigin="anonymous" referrerpolicy="no-referrer"
              />
        <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
    </head>

    <body>
        <%@include file="../Headers.jsp" %>
        <section>
            <div class="container">
                <div class="py-5">
                    <h1 class="fw-semibold">Shopping Cart</h1>
                    <nav aria-label="breadcrumb mt-4">
                        <ol class="breadcrumb fs-4">
                            <li class="breadcrumb-item"><a class="text-dark" href="home">Home</a></li>
                        </ol>
                    </nav>
                </div>
                <%
                        User acc = null;
                        if(session.getAttribute("account") != null) {
                          acc = (User)session.getAttribute("account");
                        }
                %>
                <div class="col-md-12 d-flex align-items-center ms-3">
                    <div class="col-md-8 row py-3">    
                        <div class="col-md-12 border-end pe-5">
                            <div class="row border-bottom mb-3">
                                <div class="col-7"><strong>Product</strong></div>
                                <div class="col-4"><strong>Quantity</strong></div>
                                <div class="col-1"><strong>Price</strong></div>
                            </div>
                            <c:forEach items="${requestScope.products}" var="product">  

                                <div class="row">
                                    <div class="col-2">
                                        <a href="home?Service=product_id&Pid=${product.id}" class="d-block h-100">
                                            <c:choose>
                                                <c:when test="${image.getImage(listImage, product.id) != null && !image.getImage(listImage, product.id).isEmpty()}">
                                                    <img src="./uploadFiles/${image.getImage(listImage, product.id).get(0).getName()}" alt="${product.name}"/>
                                                </c:when>
                                                <c:otherwise>
                                                    <img src="" alt="${image.getImage(listImage, product.id).get(0).getName()}"/>
                                                </c:otherwise>
                                            </c:choose>
                                        </a>
                                    </div>

                                    <div class="col-9 d-flex flex-fill flex-column justify-content-between ">
                                        <div class="h-50 d-flex align-items-center justify-content-between flex-fill">
                                            <div class="">
                                                <h3 class="fw-bold">${product.name}</h3>
                                                <div class="d-flex align-items-center position-relative hover-change">
                                                    <div class="">
                                                        <i class="fa-solid fa-fill-drip me-3"></i>
                                                        <span class="text-black">${product.PDetails.color}</span>
                                                    </div>
                                                    <div class="border-line border-l mx-3"></div>
                                                    <div class="">
                                                        <i class="fa-solid fa-store me-3"></i>
                                                        <span class="text-black">${product.PDetails.size}</span>
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="">
                                                <span class="fs-4"></span>
                                            </div>

                                            <div class="d-flex align-items-center">
                                                <div class="box-input" >

                                                    <input type="text" value="${product.quantity}" class="input-number" id="numberValue" />

                                                </div>
                                            </div>

                                            <div class="fs-4">
                                                <span class="text-danger">${product.getTotalPriceOnItems()}</span>
                                            </div>

                                        </div>
                                        <div class="h-50 d-flex align-items-center justify-content-between">


                                        </div>
                                    </div>
                                </div>
                                <hr class="product-divider">
                            </c:forEach>
                        </div>
                    </div>







                    <div class="col-4 col-md-4 text-order fs-4 fw-500 p-5">
                        <div class="w-80 m-auto">
                            <h2 class="fw-semibold text-dark">Order Summary</h2>
                            <div class="d-flex align-items-center justify-content-between border-bottom py-3">
                                <span>Subtotal</span>
                                <span class="fw-600 text-dark">${requestScope.total != null ? requestScope.total : 0} VNĐ</span>

                            </div>
                            <div class="d-flex align-items-center justify-content-between border-bottom py-3">
                                <span>Shipping Method</span>
                                <span class="fw-600 text-dark">0 VNĐ</span>
                            </div>
                            <div class="d-flex align-items-center justify-content-between border-bottom py-3">
                                <span class="fw-bold fs-3 text-dark">Order total</span>
                                <span class="fw-600 text-dark">${requestScope.total != null ? requestScope.total : 0} VNĐ</span>

                            </div>
                            <div class="mt-3 d-flex align-items-center justify-content-between">
                                <div class="">
                                    <span class="text-black">Address: </span>
                                    <% if(acc != null) {%>
                                    <p class="mt-4-3"><i class="fa-solid fa-location-dot text-danger fs-4 me-3"></i><%=acc.getAddress()%>  </p>

                                    <%}%>
                                </div> 
                                <div class="">
                                    <a href="" class="fs-4 text-info text-decoration-none"></a>
                                </div>

                            </div>
                            <a href="Cart?Service=checkout" class="mt-5 d-block
                               py-3 text-decoration-none fs-2 text-center
                               w-100 text-white bg-dark rounded-xl box-shadow1">
                                Checkout
                            </a>
                        </div>
                    </div>
                </div>    

            </div>


        </section>
        <h4 class="text-danger">${requestScope.message}</h4>
        <script src="./js/app.js"></script>
    </body>

</html>
