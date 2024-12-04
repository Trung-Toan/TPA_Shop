<%-- 
    Document   : InsertProduct
    Created on : May 21, 2024, 2:44:22 AM
    Author     : trantoan
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Models.*" %>
<%@page import="java.util.*" %>
<!DOCTYPE html>
<html>

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Insert product detail</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@10/swiper-bundle.min.css" />
        <link rel="stylesheet" href="./style.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@10/swiper-bundle.min.css" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA==" crossorigin="anonymous" referrerpolicy="no-referrer">
        <!--<script src="./js/productDetail.js" type="text/javascript"></script>-->
        <style>
            #alert-mess {
                position: fixed;
                top: 40px;
                left: 80px;
            }
        </style>
        <script>
            function addMoreProductDetail() {
                var root = document.querySelector('.root');

                var productDetailDiv = document.createElement('div');
                productDetailDiv.className = "mb-3 row";

                var sizeDiv = document.createElement('div');
                sizeDiv.className = "col-md-4";

                var sizeLabel = document.createElement('label');
                sizeLabel.className = "form-label fs-4";
                sizeLabel.textContent = "Size";

                var sizeInput = document.createElement('input');
                sizeInput.type = "text";
                sizeInput.className = "form-control fs-4 py-3";
                sizeInput.name = "size";
                sizeInput.placeholder = "Size";
                sizeInput.required = true;

                sizeDiv.appendChild(sizeLabel);
                sizeDiv.appendChild(sizeInput);
                productDetailDiv.appendChild(sizeDiv);


                var colorDiv = document.createElement('div');
                colorDiv.className = "col-md-4";

                var colorLabel = document.createElement('label');
                colorLabel.className = "form-label fs-4";
                colorLabel.textContent = "Color";

                var colorInput = document.createElement('input');
                colorInput.type = "text";
                colorInput.className = "form-control fs-4 py-3";
                colorInput.name = "color";
                colorInput.placeholder = "Color";
                colorInput.required = true;

                colorDiv.appendChild(colorLabel);
                colorDiv.appendChild(colorInput);
                productDetailDiv.appendChild(colorDiv);


                var quantityDiv = document.createElement('div');
                quantityDiv.className = "col-md-4";

                var quantityLabel = document.createElement('label');
                quantityLabel.className = "form-label fs-4";
                quantityLabel.textContent = "Quantity";

                var quantityInput = document.createElement('input');
                quantityInput.type = "number";
                quantityInput.className = "form-control fs-4 py-3";
                quantityInput.name = "quantity";
                quantityInput.value = "1";

                quantityDiv.appendChild(quantityLabel);
                quantityDiv.appendChild(quantityInput);
                productDetailDiv.appendChild(quantityDiv);

                root.appendChild(productDetailDiv);
            }
        </script>
    </head>
    <body>
        <section class="bg-light py-5">
            <div class="container bg-white p-5 rounded shadow-sm">
                <div class="row justify-content-center">
                    <div class="col-12">
                        <h1 class="mb-5 text-center">Add Product</h1>
                        <h3 style="color: red">${msg}</h3>
                    </div>
                    <!--product-->
                    <div class="col-md-6">
                        <h2 class="mb-4 text-center">Product</h2>
                        <c:choose>
                            <c:when test="${empty messenger}">
                            </c:when>
                            <c:otherwise>
                                <div class="row d-flex justify-content-end" id="alert-mess">
                                    <div class="col-md-12 alert fade show alert-${style.equals("success") ? "success" : "danger"} alert-dismissible" style="font-size: 14px">
                                        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                                        <strong>${style.equals("success") ? "Success!" : "Error!"}</strong> ${messenger}
                                    </div>
                                </div>
                            </c:otherwise>
                        </c:choose>
                        <div class="mb-4">
                            <!--name product-->
                            <div class="mb-3 row">
                                <label for="product-name" class="col-md-3 col-form-label fs-4 text-end">Tên sản phẩm</label>
                                <div class="col-md-9">
                                    <input type="text" id="product-name" class="form-control fs-4 py-4" name="productName" value="${pro.name}" readonly>
                                </div>
                            </div>
                            <!--price and discount-->
                            <div class="mb-4 row">
                                <label for="product-price" class="col-md-3 col-form-label fs-4 text-end">Giá</label>
                                <div class="col-md-4 position-relative">
                                    <input type="number" min="0" id="product-price" name="price" class="form-control fs-4 py-3" value="${pro.price}" style="height: 38px" readonly>
                                    <span class="input-suffix fs-5">VNĐ</span>
                                </div>
                                <label class="col-md-2 col-form-label fs-4 text-end">Sale</label>
                                <div class="col-md-3 position-relative">
                                    <input type="number" name="discount" class="form-control fs-4 py-3" value="${pro.discount}" style="height: 38px" readonly>
                                    <span class="input-suffix fs-5">%</span>
                                </div>
                            </div>
                            <!--gender-->
                            <div class="mb-4 row">
                                <label class="col-md-3 col-form-label fs-4 text-end">Sản phẩm cho:</label>
                                <div class="col-md-9 d-flex align-items-center">
                                    <div class="form-check me-4">
                                        <input class="form-check-input" type="radio" name="gender" value="1" ${pro.gender == 1 ? "checked" : ""} disabled>
                                        <label class="form-check-label fs-4">Nam</label>
                                    </div>
                                    <div class="form-check me-4">
                                        <input class="form-check-input" type="radio" name="gender" value="2" ${pro.gender == 2 ? "checked" : ""} disabled>
                                        <label class="form-check-label fs-4">Nữ</label>
                                    </div>
                                    <div class="form-check">
                                        <input class="form-check-input" type="radio" name="gender" value="0" ${pro.gender == 0 ? "checked" : ""} disabled>
                                        <label class="form-check-label fs-4">Cả nam và nữ</label>
                                    </div>
                                </div>
                            </div>
                            <!--category and sub category-->
                            <div class="container mb-4 row" style="margin-left: auto;">
                                <div class="row col-md ">
                                    <label class="col-md-3 col-form-label fs-4 text-end">Category</label>
                                    <div class="col-md-9">
                                        <select name="category" class="form-select fs-5 py-3" disabled>
                                            <option value="${category.id}" >${category.name}</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="row col-md ">
                                    <label class="col-md-3 col-form-label fs-4 text-end">Sub category</label>
                                    <div class="col-md-9">
                                        <select name="category" class="form-select fs-5 py-3" disabled>
                                            <option value="${subcCategory.id}" >${subcCategory.name}</option>
                                        </select>
                                    </div>
                                </div>
                            </div>

                            <!--description-->
                            <div class="mb-4 row">
                                <label class="col-md-3 col-form-label fs-4 text-end">Mô tả sản phẩm</label>
                                <div class="col-md-9">
                                    <textarea name="describe" oninput="autoResize(this)" class="form-control fs-4 py-3" style="min-height: 100px;" readonly>${pro.description}</textarea>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!--product detail-->
                    <div class="col-md-6">
                        <h2 class="mb-4 text-center">Product Details</h2>
                        <form action="addproductdetail" method="post" enctype="multipart/form-data">
                            <!--insert img-->
                            <div class="mb-4 row pt-3">
                                <label class="col-md-3 col-form-label fs-4 text-end">
                                    <b>Upload image</b>
                                </label>
                                <div class="col-md-9">
                                    <input type="file" name="file" multiple class="form-control fs-4 " style="height: 28px">
                                    <label style="font-size: 10px" class="col-md-12 col-form-label text-start">Kích thước 1 hình ảnh <= 10mb, tổng số ảnh <= 50mb.</label>
                                </div>

                            </div>

                            <!--product detail-->
                            <div class="mb-4 row">
                                <label class="col-md-3  fs-4 text-end">
                                    <b>Product Details<sup style="color: red; font-size: 14px">*</sup></b>
                                </label>
                                <!--size - color - quantity-->
                                <div class="root col-md-9 p-4 border rounded">
                                    <div class="mb-3 row">
                                        <div class="col-md-4">
                                            <label class="form-label fs-4">Size</label>
                                            <input type="text" class="form-control fs-4 py-3" name="size" placeholder="Size" required>
                                        </div>
                                        <div class="col-md-4">
                                            <label class="form-label fs-4">Color</label>
                                            <input type="text" class="form-control fs-4 py-3" name="color" placeholder="Color" required>
                                        </div>
                                        <div class="col-md-4">
                                            <label class="form-label fs-4">Quantity</label>
                                            <input type="number" class="form-control fs-4 py-3" name="quantity" value="1">
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-2  offset-md-7 ">
                                    <button type="button" id="add-product-btn" class="btn btn-secondary  w-90 fs-9" onclick="addMoreProductDetail()">+</button>
                                </div>
                            </div>
                            <div class="col-md-3  offset-md-6 text-center">
                                <button type="submit" name="submit" value="Insert" class="btn btn-success px-5 py-3 fs-4">Insert</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </section>

        <style>
            .input-suffix {
                background-color: #f0f0f0;
                padding: 10px;
                border: 1px solid #ccc;
                border-left: none;
                position: absolute;
                right: 0;
                top: 0;
                bottom: 0;
                display: flex;
                align-items: center;
                justify-content: center;
                width: 50px;
            }
        </style>

    </body>
</html>
