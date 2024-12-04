<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Product Detail</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@10/swiper-bundle.min.css" />
        <link rel="stylesheet" href="./style.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA==" crossorigin="anonymous" referrerpolicy="no-referrer"
              />
        <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
        <style>
            * {
                box-sizing: border-box;
            }

            /* Position the image container (needed to position the left and right arrows) */
            .container {
                position: relative;
            }

            /* Hide the images by default */
            .mySlides {
                display: none;
            }

            /* Add a pointer when hovering over the thumbnail images */
            .cursor {
                cursor: pointer;
            }

            /* Number text (1/3 etc) */
            .numbertext {
                color: #f2f2f2;
                font-size: 12px;
                padding: 8px 12px;
                position: absolute;
                top: 0;
            }

            /* Container for image text */
            .caption-container {
                text-align: center;
                background-color: #222;
                padding: 2px 16px;
                color: white;
            }

            .row:after {
                content: "";
                display: table;
                clear: both;
            }

            /* Six columns side by side */
            .column {
                float: left;
                width: 16.66%;
            }

            /* Add a transparency effect for thumnbail images */
            .demo {
                opacity: 0.6;
            }

            .active,
            .demo:hover {
                opacity: 1;
            }
        </style>
        <script>
            let slideIndex = 1;
            showSlides(slideIndex);

            function plusSlides(n) {
                showSlides(slideIndex += n);
            }

            function currentSlide(n) {
                showSlides(slideIndex = n);
            }

            function showSlides(n) {
                let i;
                let slides = document.getElementsByClassName("mySlides");
                let dots = document.getElementsByClassName("demo");
                let captionText = document.getElementById("caption");
                if (n > slides.length) {
                    slideIndex = 1;
                }
                if (n < 1) {
                    slideIndex = slides.length;
                }
                for (i = 0; i < slides.length; i++) {
                    slides[i].style.display = "none";
                }
                for (i = 0; i < dots.length; i++) {
                    dots[i].className = dots[i].className.replace(" active", "");
                }
                slides[slideIndex - 1].style.display = "block";
                dots[slideIndex - 1].className += " active";
                captionText.innerHTML = dots[slideIndex - 1].alt;
            }
        </script>
    </head>

    <body>
        <%@include file="../Headers.jsp" %>
        <section id="product-detail" class="mt-5">
            <div class="container h-100">
                <div class="row h-100 gx-5">
                    <div class="col-md-5">
                        <div class="main-image rounded-sm overflow-hidden">
                            <div class="row">
                                <!-- Display product images -->
                                <c:forEach items="${listImage}" var="image">
                                    <div class="mySlides">
                                        <img class="img-thumbnail" src="./uploadFiles/${image.name}" style="width:100%">
                                    </div>
                                </c:forEach>

                            </div>
                            <div class="row mt-3">
                                <!-- Display product image thumbnails -->
                                <c:forEach items="${listImage}" var="image">
                                    <div class="column">
                                        <img class="demo cursor" src="./uploadFiles/${image.name}" style="width:100%" onclick="" alt="${product.name}">
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                    </div>

                    <div class="col-md-7">
                        <div class="">
                            <div class="product-title">
                                <h1 class="fw-bold">${product.name}</h1>
                            </div>
                            <div class="d-flex align-items-center mt-5">
                                <div class="px-4 py-1 border-green rounded-sm">
                                    <!-- Display product price after discount -->
                                    <span class="text-green fs-3 fw-bold">${product.priceAfterDiscount}₫</span>
                                </div>
                                <div class="px-3">
                                    <!-- Display original product price -->
                                    <p class="text-decoration-line-through text-black fs-4 m-0">${product.price}₫</p>
                                </div>
                                <c:if test="${product.discount > 0}">
                                    <!-- Display discount percentage if applicable -->
                                    <span class="text-white p-2 mx-2 fs-5 fw-bold bg-danger tag-sale">Giảm ${product.discount}%</span>
                                </c:if>


                            </div>
                            <div class="w-75 mt-5">
                                <h3 class="fw-semibold">Color:</h3>
                                <div class="d-flex flex-wrap gap-4 fs-4">
                                    <!-- Display product colors -->
                                    <c:forEach items="${listPD}" var="detail">
                                        <span class="badge bg-secondary">${detail.color}</span>
                                    </c:forEach>
                                </div>
                            </div>
                            <div class="w-75 mt-5">
                                <h3 class="fw-semibold">Size:</h3>
                                <div class="d-flex flex-wrap gap-4 fs-4">
                                    <!-- Display product sizes -->
                                    <c:forEach items="${listPD}" var="detail">
                                        <span class="badge bg-secondary">${detail.size}</span>
                                    </c:forEach>
                                </div>
                            </div>

                            <form action="Cart" method="post">
                                <input type="hidden" name="Service" value="cart"/>
                                <input type="hidden" value="${product.id}" name="product_id"/>
                                <div class="w-75 mt-5">
                                    <h5 class="fw-semibold">Number:</h5>
                                    <div class="d-flex align-items-center">
                                        <div class="box-input">
                                            <button type="button" class="p-3 border-0 bg-white" id="minusNumberBtn">
                                                <i class="fa-solid fa-minus fs-5"></i>
                                            </button>
                                            <input type="text" value="1" name="quantity" class="input-number" id="numberValue"/>
                                            <button type="button" class="p-3 border-0 bg-white" id="addNumberBtn">
                                                <i class="fa-solid fa-plus fs-5"></i>
                                            </button>
                                        </div>
                                        <div class="ms-4 fs-5">
                                            <!-- Display available quantity -->
                                            <span id="number-available">${listPD[0].quantity} sản phẩm có sẵn</span>
                                        </div>
                                    </div>
                                </div>
                                <div class="mt-5">
                                    <div class="d-flex">
                                        <button type="submit" class="d-block w-fit-content px-4 py-4 border-0 rounded-sm min-w20 text-center me-3 bg-black text-white shadow-lg">
                                            <i class="bx bx-shopping-bag fs-3 me-2"></i>
                                            <span class="fs-3 fw-semibold">Add to bag</span>
                                        </button>
                                        <button class="d-block w-fit-content px-4 py-4 border-0 rounded-sm min-w20 text-center bg-danger text-white shadow-lg">
                                            <span class="fs-3 fw-semibold">Buy now</span>
                                        </button>
                                    </div>
                                </div>
                            </form>

                            <div class="mt-5">
                                <div class="">
                                    <div class="fs-4">
                                        <!-- Display product description -->
                                        <p>${product.description}</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <script src="./js/app.js"></script>

    </body>
</html>
