<%-- 
    Document   : Home
    Created on : 13 Oct, 2023, 11:30:28 AM
    Author     : HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.text.Normalizer" %>
<%@page import="java.util.regex.Pattern" %>
<%@page import="DAL.*" %>
<%@page import="Models.*" %>
<%@page import="java.util.*" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Home Page</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@10/swiper-bundle.min.css" />
        <link rel="stylesheet" href="./style.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA==" crossorigin="anonymous" referrerpolicy="no-referrer"
              />
        <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
    </head>

    <body>
        <%@include file="../Headers.jsp" %>
        <h3>${requestScope.linkProduct!=null?requestScope.linkProduct:''}</h3>
        <section id="banner">
            <div class="swiper mySwiper">
                <div class="swiper-wrapper">
                    <div class="swiper-slide">
                        <div class="slider-img">
                            <img src="./images/banner1.jpg" alt="Images">
                        </div>
                        <div class="slider-info">
                            <h2 class="slider-title">Exclusive collection for everyone</h2>
                            <p class="slider-desc">Lorem ipsum dolor sit amet consectetur adipisicing elit. Aperiam tenetur molestiae magnam.</p>
                            <a href="#" class="slider-button">
                                Order now
                                <i class="fa-solid fa-arrow-right"></i>
                            </a>
                        </div>
                    </div>
                    <div class="swiper-slide">
                        <div class="slider-img">
                            <img src="./images/banner2.jpg" alt="">
                        </div>
                        <div class="slider-info">
                            <h2 class="slider-title">Exclusive collection for everyone</h2>
                            <p class="slider-desc">Lorem ipsum dolor sit amet consectetur adipisicing elit. Aperiam tenetur molestiae magnam.</p>
                            <a href="#" class="slider-button">
                                Order now
                                <i class="fa-solid fa-arrow-right"></i>
                            </a>
                        </div>
                    </div>
                    <div class="swiper-slide">
                        <div class="slider-img">
                            <img src="./images/banner3.jpg" alt="">
                        </div>
                        <div class="slider-info">
                            <h2 class="slider-title">Exclusive collection for everyone</h2>
                            <p class="slider-desc">Lorem ipsum dolor sit amet consectetur adipisicing elit. Aperiam tenetur molestiae magnam.</p>
                            <a href="#" class="slider-button">
                                Order now
                                <i class="fa-solid fa-arrow-right"></i>
                            </a>
                        </div>
                    </div>
                </div>
                <div class="swiper-button-next">

                </div>
                <div class="swiper-button-prev">

                </div>
                <div class="swiper-pagination"></div>
            </div>
        </section>
        <section id="body" class="mt-5">
            <div class="container">
                <h2 class="fs-40 my-5 fw-bold">Discover more. <span class="black-weak">Good things are waiting for you</span></h2>
                <div class="row gx-4">
                    <div class="col-md-3 mt-4">
                        <div class="dis-item">
                            <div class="dis-info">
                                <div class="info-text">
                                    <p>Explore new arrivals</p>
                                    <h2>Shop the latest <br>from top brands</h2>
                                </div>
                                <button>Show me all</button>
                            </div>
                            <img src="#" alt="Images">
                        </div>
                    </div>
                    <div class="col-md-3 mt-4">
                        <div class="dis-item">
                            <div class="dis-info">
                                <div class="info-text">
                                    <p>Explore new arrivals</p>
                                    <h2>Shop the latest <br>from top brands</h2>
                                </div>
                                <button>Show me all</button>
                            </div>
                            <img src="./images/trousersItem.png" alt="">
                        </div>
                    </div>
                    <div class="col-md-3 mt-4">
                        <div class="dis-item">
                            <div class="dis-info">
                                <div class="info-text">
                                    <p>Explore new arrivals</p>
                                    <h2>Shop the latest <br>from top brands</h2>
                                </div>
                                <button>Show me all</button>
                            </div>
                            <img src="./images/shoeItem.png" alt="">
                        </div>
                    </div>
                    <div class="col-md-3 mt-4">
                        <div class="dis-item">
                            <div class="dis-info">
                                <div class="info-text">
                                    <p>Explore new arrivals</p>
                                    <h2>Shop the latest <br>from top brands</h2>
                                </div>
                                <button>Show me all</button>
                            </div>
                            <img src="./images/gymItem.png" alt="">
                        </div>
                    </div>
                </div>
            </div>
            <div class="fashion-container h-100 mt-5">
                <div class="row box-fashion d-flex">
                    <div class="col-md-3 col-lg-2 left-nav bg-white h-100">
                        <div class="list-filter">
                            <div class="filter-header">
                                <h4>Filters</h4>
                                <!--<span id="clearAll">Clear all</span>-->
                            </div>

                            <form action="filterproduct" method="get">
                                <div class="boder p-4">
                                    <div class="d-flex align-items-center justify-content-between">
                                        <h3 class="text-uppercase fs-5 fw-bold">Category: </h3>
                                        <i class="fa-solid fa-magnifying-glass search-icon-filter pointer"></i>
                                    </div>
                                    <c:forEach var="category" items="${requestScope.listCategory}">
                                        <c:set var="isChecked" value="false" /> <!-- Mặc định chưa chọn -->
                                        <c:forEach var="cat_select" items="${requestScope.category_select}">
                                            <c:if test="${cat_select == category.name && !isChecked}">
                                                <c:set var="isChecked" value="true" />
                                            </c:if>
                                        </c:forEach>
                                        <div class="d-flex align-items-center mt-3">
                                            <input class="me-3 pointer category-item" type="checkbox" 
                                                   id="category-${category.id}"
                                                   class="custom-radio" name="category"
                                                   ${isChecked ? "checked" : ""}
                                                   "
                                                   value="${category.name}" />
                                            <label class="fs-5 fw-bold pointer text-capitalize"
                                                   for="category-${category.id}">${category.name}</label>
                                        </div>
                                    </c:forEach>
                                </div>

                                <div class="boder p-4">
                                    <div class="d-flex align-items-center justify-content-between">
                                        <h3 class="text-uppercase fs-5 fw-bold">Size: </h3>
                                        <!--<i class="fa-solid fa-magnifying-glass search-icon-filter pointer"></i>-->
                                    </div>
                                    <div class="d-flex align-items-center mt-3">
                                        <div class="d-flex justify-content-around w-100 mb-3">
                                            <input class="me-1 pointercustom-radio" type="checkbox"
                                                   id="sizeS"
                                                   ${isChecked ? "checked" : ""}
                                                   name="size" value="s" />
                                            <label class="fs-5 pointer" for="sizeS">S</label>
                                        </div>
                                        <div class="d-flex justify-content-around w-100 mb-3">
                                            <input class="me-1 pointercustom-radio" type="checkbox"
                                                   id="sizeM"
                                                   ${isChecked ? "checked" : ""}
                                                   name="size" value="m" />
                                            <label class="fs-5 pointer" for="sizeM">M</label>
                                        </div>    
                                        <div class="d-flex justify-content-around w-100 mb-3">
                                            <input class="me-1 pointercustom-radio" type="checkbox"
                                                   id="sizeL"
                                                   ${isChecked ? "checked" : ""}
                                                   name="size" value="l" />
                                            <label class="fs-5 pointer" for="sizeL">L</label>
                                        </div>
                                        <div class="d-flex justify-content-around w-100 mb-3">
                                            <input class="me-1 pointercustom-radio" type="checkbox"
                                                   id="sizeXL"
                                                   ${isChecked ? "checked" : ""}
                                                   name="size" value="xl" />
                                            <label class="fs-5 pointer" for="sizeXL">XL</label>
                                        </div>
                                        <div class="d-flex justify-content-around w-100 mb-3">
                                            <input class="me-1 pointercustom-radio" type="checkbox"
                                                   id="sizeXXL"
                                                   ${isChecked ? "checked" : ""}
                                                   name="size" value="xxl" />
                                            <label class="fs-5 pointer" for="sizeXXL">XXL</label>
                                        </div>
                                    </div>
                                </div>


                                <div class="boder p-4 text-center">
                                    <div class="d-flex align-items-center justify-content-between">
                                        <h3 class="text-uppercase fs-5 fw-bold">Price</h3>
                                    </div>
                                    <c:set var="min_input" value="${requestScope.min_price}" />
                                    <c:set var="max_input" value="${requestScope.max_price}" />
                                    <div class="d-flex mt-3 align-items-center flex-column ">
                                        <div class="d-flex align-items-center">
                                            <input class="me-3 pointer py-4" type="number" 
                                                   value="${min_input != null ? min_input : ''}"
                                                   placeholder="Min price" min="1" name="minPrice" id="minPrice" />
                                            <span class="text-danger fs-5">vnđ</span>
                                        </div>
                                        <div class="d-flex align-items-center mt-3">
                                            <input class="me-3 pointer py-4" type="number"
                                                   value="${max_input != null ? max_input : ''}"
                                                   placeholder="Max price" min="1" name="maxPrice" id="maxPrice" />
                                            <span class="text-danger fs-5">vnđ</span>
                                        </div>
                                        <div class="text-center mt-3">
                                            <button onclick="filterProduct('price')" type="button" class="border-0 text-white bg-danger py-2 px-4 rounded fs-5 fw-bold">Áp dụng</button>
                                        </div>
                                    </div>
                                </div>
                            </form>

                        </div>
                    </div>
                    <div class="col-md-9 col-lg-10 right-product h-100">
                        <!--Sort product by--> 
                        <div class="sort-search">
                            <div class="sort-box">
                                <c:set value="${requestScope.order}" var="order"/>
                                <select class="form-select" onchange="filterProduct('order')" id="orderOption">
                                    <option value="asc" class="py-5" ${order.equals("asc")?"selected":""}>Price: Low To Hight</option>
                                    <option value="desc" class="py-5" ${order.equals("desc")?"selected":""}>Price: Hight To Low</option>
                                </select>
                                <span class="select-title">Sort by: </span>
                            </div>
                        </div>
                        <!--print list product-->
                        <div class="row g-5" id="list-product">
                            
                            <c:forEach items="${listProduct}" var="product">
                                <div class="col-sm-6 col-md-4 col-lg-3">
                                    <a href="ProductDetail?productIdSelect=${product.id}" 
                                       class="d-block h-100 product-item text-decoration-none position-relative">

                                        <div class="boder-radius-3 position-relative overflow-hidden">
                                            
                                            <c:choose>
                                                <c:when test="${image.getImage(listImage, product.id) != null && !image.getImage(listImage, product.id).isEmpty()}">
                                                    <img src="./uploadFiles/${image.getImage(listImage, product.id).get(0).getName()}" alt="${product.name}"/>
                                                </c:when>
                                                <c:otherwise>
                                                    <img src="" alt="${image.getImage(listImage, product.id).get(0).getName()}"/>
                                                </c:otherwise>
                                            </c:choose>

                                            <div class="d-flex justify-content-center align-items-center px-3 button-products">
                                                <!--button add to cart-->
                                                
                                                    <button class="button-product me-3">
                                                        <i class='bx bx-shopping-bag'></i> 
                                                        &ensp; Add to cart
                                                    </button>
                                                

                                                <!--button Quick view-->
                                                <button class="button-product me-3">
                                                    <i class="fa-solid fa-arrows-up-down-left-right"></i>
                                                    &ensp; Quick view
                                                </button>
                                            </div>

                                            <!--discount-->
                                            <c:if test="${product.discount > 0}">
                                                <div class="product-sale">
                                                    <span class="product-sale-percent">${product.discount}%</span>
                                                    <span class="product-sale-label">Giảm</span>
                                                </div>
                                            </c:if>
                                        </div>

                                        <div class="p-4 d-flex flex-column">
                                            <h3 class="fw-bold text-black product-title">${product.name}</h3>
                                            <div class="flex-fill mt-auto">
                                                <c:if test="${product.gender == 0}">
                                                    <p class="fs-4 product-des">Unisex</p>
                                                </c:if>
                                                <c:if test="${product.gender == 1}">
                                                    <p class="fs-4 product-des">Male</p>
                                                </c:if>
                                                <c:if test="${product.gender == 2}">
                                                    <p class="fs-4 product-des">Female</p>
                                                </c:if>
                                                <div class="">
                                                    <p class="text-decoration-line-through text-black fs-4">${product.price}₫</p>
                                                </div>
                                                <div class="d-flex align-items-center justify-content-between mt-4">
                                                    <span class="product-price">${product.getPriceAfterDiscount()}₫</span>
                                                    <div class="d-flex align-items-center">
                                                        <i class="fa-solid fa-star text-warning me-3"></i>
                                                        <h4 class="m-0 product-preview">4.9(98 reviews)</h4>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </a>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </div>

            </div>
        </section>
        <%--<%@include file="./Footer.jsp" %>--%>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/swiper@10/swiper-bundle.min.js"></script>
        <!-- Initialize Swiper -->
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

            function actionHover() {
                let ProductList = document.querySelectorAll('.product-item')
                ProductList.forEach((product) => {
                    product.addEventListener('mouseover', () => {
                        product.querySelector('.button-products').classList.add('activeHover')
                    })
                    product.addEventListener('mouseout', () => {
                        product.querySelector('.button-products').classList.remove('activeHover')
                    })
                })
            }
            actionHover()

            function filterProduct(param) {
                let url = "";
                const listSize = document.getElementsByName("size");
                const listCategory = document.getElementsByName("category");
                const dicount = document.getElementsByName("discount");

                const orderOption = document.getElementById("orderOption");
                const minPrice = document.getElementById("minPrice");
                const maxPrice = document.getElementById("maxPrice");

                for (let i = 0; i < listSize.length; i++) {
                    if (listSize[i].checked == true) {
                        url += "&size=" + listSize[i].value;
                    }
                }

                for (let i = 0; i < listCategory.length; i++) {
                    if (listCategory[i].checked == true) {
                        url += "&category=" + listCategory[i].value;
                    }
                }
                for (let i = 0; i < dicount.length; i++) {
                    if (dicount[i].checked == true) {
                        url += "&discount=" + dicount[i].value;
                    }
                }
                url = "?Service=filter" + url;

                if (param == 'price') {
                    let separator = url.indexOf("?") !== -1 ? "&" : "?";
                    if (minPrice.value != '' && maxPrice.value != '') {
                        if (minPrice.value * 1 > maxPrice.value * 1) {
                            alert('The price unvalid')
                            return;
                        } else {
                            url += separator + "minPrice=" + minPrice.value + "&maxPrice=" + maxPrice.value;
                        }
                    } else if (minPrice.value == '' && maxPrice.value == '') {
                        //make not load                        
                    } else if (minPrice.value == '') {
                        url += separator + "&maxPrice=" + maxPrice.value;
                    } else if (maxPrice.value == '') {
                        url += separator + "&minPrice=" + minPrice.value;
                    }
                }

                if (param == 'order') {
                    // Kiểm tra xem URL đã chứa tham số "?" chưa
                    let separator = url.indexOf("?") !== -1 ? "&" : "?";
                    // Cập nhật URL với tham số "order"
                    url += separator + "orderBy=" + orderOption.value;
                }

                window.location = url
            }

        </script>
        <!--        <script src="./js/app.js"></script>-->
    </body>
</html>
<!--<script src="./js/app.js"></script>-->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/swiper@10/swiper-bundle.min.js"></script>

</script>
</body>
</html>
