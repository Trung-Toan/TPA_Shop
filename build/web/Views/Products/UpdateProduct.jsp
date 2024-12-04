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
        <title>Insert product</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@10/swiper-bundle.min.css" />
        <link rel="stylesheet" href="./style.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA==" crossorigin="anonymous" referrerpolicy="no-referrer"
              />
        <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>


        <style>
            .insert-product-input {
                display: flex;
                align-items: center;
                position: relative;
            }
            .insert-product-input input {
                flex: 1;
                padding-right: 50px; /* Để chừa chỗ cho ký hiệu */
            }
            .input-suffix {
                background-color: #f0f0f0;
                padding: 10px;
                border: 1px solid #ccc;
                border-left: none; /* Bỏ viền trái để kết hợp với viền ô nhập */
                position: absolute;
                right: 0;
                top: 0;
                bottom: 0;
                display: flex;
                align-items: center;
                justify-content: center;
                width: 50px; /* Độ rộng của ký hiệu */
            }
            .img-update .img{
                border: 0px solid red;
                border-radius: 15px;
                background-color: rgba(255, 255, 255, 0.1);
                font-size: 12px;
                color: red;
            }
            .img:hover {
                border: 1px solid rgba(255, 106, 106, 0.6);
                background-color: rgba(255, 106, 106, 0.6);
            }
            #deletePD:hover {
                background-color: #FF6A6A;
                color: #fff
            }
        </style>

        <script>
            function addMoreProductDetail() {
                var root = document.querySelector('.root');

                var productDetailDiv = document.createElement('div');
                productDetailDiv.className = "row mt-3 justify-content-between";

                var sizeDiv = document.createElement('div');
                sizeDiv.className = "row col-md d-flex justify-content-between";

                var sizeLabel = document.createElement('label');
                sizeLabel.className = "col-md-3 text-end fs-4 mt-4";
                sizeLabel.textContent = "Size";

                var sizeInputDiv = document.createElement('div');
                sizeInputDiv.className = "col-md-8 insert-product-input";

                var sizeInput = document.createElement('input');
                sizeInput.type = "text";
                sizeInput.className = "rounded-sm border w-100 fs-3 py-4 px-3";
                sizeInput.name = "sizeAdd";
                sizeInput.placeholder = "Size";
                sizeInput.required = true;

                sizeInputDiv.appendChild(sizeInput);
                sizeDiv.appendChild(sizeLabel);
                sizeDiv.appendChild(sizeInputDiv);
                productDetailDiv.appendChild(sizeDiv);

                var colorDiv = document.createElement('div');
                colorDiv.className = "row col-md d-flex justify-content-between";

                var colorLabel = document.createElement('label');
                colorLabel.className = "col-md-3 text-end fs-4 mt-4";
                colorLabel.textContent = "Color";

                var colorInputDiv = document.createElement('div');
                colorInputDiv.className = "col-md-8 insert-product-input";

                var colorInput = document.createElement('input');
                colorInput.type = "text";
                colorInput.className = "rounded-sm border w-100 fs-4 py-4 px-3";
                colorInput.name = "colorAdd";
                colorInput.placeholder = "Color";
                colorInput.required = true;

                colorInputDiv.appendChild(colorInput);
                colorDiv.appendChild(colorLabel);
                colorDiv.appendChild(colorInputDiv);
                productDetailDiv.appendChild(colorDiv);


                var quantityDiv = document.createElement('div');
                quantityDiv.className = "row col-md d-flex justify-content-between";

                var quantityLabel = document.createElement('label');
                quantityLabel.className = "col-md-3 text-end fs-5 mt-4";
                quantityLabel.textContent = "Quantity";

                var quantityInputDiv = document.createElement('div');
                quantityInputDiv.className = "col-md-8 insert-product-input";

                var quantityInput = document.createElement('input');
                quantityInput.type = "number";
                quantityInput.className = "rounded-sm border w-100 fs-5 py-4 px-3";
                quantityInput.name = "quantityAdd";
                quantityInput.value = "1";

                quantityInputDiv.appendChild(quantityInput);
                quantityDiv.appendChild(quantityLabel);
                quantityDiv.appendChild(quantityInputDiv);
                productDetailDiv.appendChild(quantityDiv);

                var actionDiv = document.createElement('div');
                actionDiv.className = "col-md-1";

                productDetailDiv.appendChild(actionDiv);

                root.appendChild(productDetailDiv);
            }

            function myFun() {
                document.getElementById("frm").submit();
            }
        </script>

    </head>
    <body>
        <section class="bg-weak">
            <div class="container bg-white">
                <div class="row px-5 justify-content-center">
                    <div class="col-md-7 mt-5">
                        <div >
                            <h1 class="text-center">Update Product</h1>
                            <div class="my-5">
                                <!--id product-->
                                <div class="row d-flex  align-items-center">
                                    <div class="col-md-2 text-end">
                                        <label class="fs-4" for="product-name">
                                            <b>ID product</b>
                                        </label>
                                    </div>
                                    <div class="col-md-10 insert-product-input">
                                        <input 
                                            type="text" id="product-name" 
                                            class="rounded-sm border w-100 fs-3 py-4 px-3" 
                                            style="background-color: #CFCFCF"
                                            name="idProduct" 
                                            value="${product.id}" 
                                            readonly 
                                            />  
                                    </div>
                                </div>

                                <!--category and sub category-->                                    
                                <div class="row mt-3">
                                    <div class="col-md-2 text-end">
                                        <label class="fs-4">
                                            <b>Category product</b>
                                        </label>
                                    </div>
                                    <div class="col-md-4 insert-product-input">
                                        <input 
                                            type="text" 
                                            id="category" 
                                            class="rounded-sm border w-100 fs-3 py-4 px-3" 
                                            name="idProduct" 
                                            value="${categoy}" 
                                            style="background-color: #CFCFCF"
                                            readonly 
                                            />  
                                    </div>

                                    <div class="col-md-2 text-end">
                                        <label class="fs-4">
                                            <b>Sub category product</b>
                                        </label>
                                    </div>
                                    <div class="col-md-4 insert-product-input">
                                        <input 
                                            type="text" id="subCategory" 
                                            class="rounded-sm border w-100 fs-3 py-4 px-3" 
                                            style="background-color: #CFCFCF"
                                            name="idProduct" 
                                            value="${subCategoy}" 
                                            readonly 
                                            />
                                    </div>
                                </div>

                                <form action="productProcess" method="post" enctype="multipart/form-data">
                                    <!--name product-->
                                    <div class="row mt-5 d-flex align-items-center">
                                        <div class="col-md-2 text-end d-flex">
                                            <label class="fs-4" for="product-name">
                                                <b>Name Product</b>
                                            </label>
                                            <strong style="color: red">(*)</strong>
                                        </div>
                                        <div class="col-md-10 insert-product-input">
                                            <input 
                                                type="text" 
                                                id="product-name" 
                                                class="rounded-sm border w-100 fs-3 py-4 px-3" 
                                                name="nameProduct" 
                                                value="${product.name}" 
                                                placeholder="Product name" 
                                                required
                                                />  
                                        </div>
                                    </div>

                                    <!--price and discount-->
                                    <div class="container mt-5">
                                        <div class="row d-flex align-items-center">
                                            <!--price-->
                                            <div class="col-md-2 text-end d-flex ">
                                                <label class="fs-4 ml-5" for="product-price">
                                                    <b>Price product</b>
                                                </label>
                                                <strong style="color: red">(*)</strong>
                                            </div>
                                            <div class="col-md-4 insert-product-input">
                                                <input type="number" min="0" id="product-price" 
                                                       name="price" value="${product.price}"
                                                       class="rounded-sm border w-100 fs-3 py-3 px-3" 
                                                       placeholder="price" required/>
                                                <span class="input-suffix fs-3">VNĐ</span>
                                            </div>
                                            <!--discount-->
                                            <div class="col-md-2 text-end ">
                                                <h3 class="fs-4">
                                                    <b>Sale</b> <sup><strong style="color: red">(*)</strong></sup>
                                                </h3>

                                            </div>
                                            <div class="col-md-4 insert-product-input">
                                                <input type="number" 
                                                       name="discount" 
                                                       value="${product.discount}"
                                                       class="rounded-sm border w-100 fs-3 py-3 px-3"/>
                                                <span class="input-suffix fs-3">%</span>
                                            </div>
                                        </div>
                                    </div>

                                    <!--gender-->
                                    <div class="row mt-5 d-flex align-items-center">
                                        <div class="col-md-2 text-end">
                                            <label class="fs-4 ml-5" for="product-price">
                                                <b>Product for</b>
                                            </label>
                                        </div>
                                        <div class="col-md-10 d-flex align-items-center insert-product-input">
                                            <label class="d-flex align-items-center me-4">
                                                <input type="radio" value="1" name="gender" ${(product.gender == 1 ? "checked" : "")} />
                                                <span class="px-2 fs-3 mb-0">Male</span>
                                            </label>
                                            <label class="d-flex align-items-center me-4">
                                                <input type="radio" value="2" name="gender" ${(product.gender == 2 ? "checked" : "")} />
                                                <span class="px-2 fs-3 mb-0">Female</span>
                                            </label>
                                            <label class="d-flex align-items-center me-4">
                                                <input type="radio" value="0" name="gender" ${(product.gender == 0 ? "checked" : "")} />
                                                <span class="px-2 fs-3 mb-0">Both male and female</span>
                                            </label>
                                        </div>
                                    </div>

                                    <!-- Image -->
                                    <div class="row mt-5 d-flex align-items-center justify-content-between" >
                                        <div class="col-md-2 text-end">
                                            <label class="fs-4">
                                                <b>Image product</b>
                                            </label>
                                        </div>
                                        <div 
                                            class="row col-md-10 ml-2 " 
                                            style="border: 1px solid rgba(122, 122, 122, 0.5);
                                            background-color: #ccc;
                                            border-radius: 5px"
                                            >
                                            <div class="row justify-content-between">
                                                <c:forEach var="img" items="${listImage}">
                                                    <div style="position: relative;" class="col-md-2 text-center d-flex flex-column align-items-center mb-3 img-update">
                                                        <!--print image-->
                                                        <img class="mt-2" style="width: 100%;
                                                             height: auto;
                                                             object-fit: fill;" 
                                                             src="./uploadFiles/${img.name}" 
                                                             alt="can not print img ${img.name} "
                                                             />
                                                        <!--button delete-->
                                                        <button 
                                                            type="submit" 
                                                            name="idImg" 
                                                            value="${img.id}" 
                                                            class="img"
                                                            style="position: absolute; top: -2px; right: -2px; width: 20px; height: 20px;"
                                                            >
                                                            x
                                                        </button>
                                                    </div>
                                                </c:forEach>
                                            </div>
                                            <div class="row col-md-12 d-flex justify-content-center" style="margin: 0 auto;">
                                                <div class="col-md-7 row pt-3 d-flex justify-content-center">
                                                    <div class="col-md-9">
                                                        <input type="file" name="file" multiple class="form-control">
                                                        <label style="font-size: 7px" class="col-md-12 col-form-label text-center">
                                                            Image <= 10MB, total image <= 50MB.
                                                        </label>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <!--Product detail-->
                                    <div class="row mt-5 d-flex align-items-center justify-content-between" >
                                        <div class="col-md-2 text-end">
                                            <label class="fs-4">
                                                <b>Product Details</b>
                                            </label>
                                        </div>
                                        <div 
                                            class="row col-md-10 ml-2" 
                                            style="border: 1px solid rgba(122, 122, 122, 0.5);
                                            border-radius: 5px"
                                            >
                                            <div class="root col-md-12 mt-3  row  d-flex justify-content-between">
                                                <c:forEach var="pd" items="${listProductDetail}">
                                                    <div class = "row mt-3 justify-content-between">
                                                        <div class="row col-md d-flex justify-content-between">
                                                            <div class="col-md-3 text-end">
                                                                <label class="fs-4 mt-4">Size</label>
                                                            </div>
                                                            <div class="col-md-8 insert-product-input">
                                                                <input 
                                                                    type="text" 
                                                                    class="rounded-sm border w-100 fs-4 py-4 px-3" 
                                                                    value="${pd.size == null ? "" : pd.size}" 
                                                                    name="size" 
                                                                    placeholder="Size" 
                                                                    required
                                                                    />  
                                                            </div>
                                                        </div>

                                                        <div class="row col-md d-flex justify-content-between">
                                                            <div class="col-md-3 text-end">
                                                                <label class="fs-4 mt-4" >Color</label>
                                                            </div>
                                                            <div class="col-md-8 insert-product-input">
                                                                <input 
                                                                    type="text" 
                                                                    class="rounded-sm border w-100 fs-4 py-4 px-3" 
                                                                    value="${pd.color == null ? "" : pd.color}" 
                                                                    name="color" 
                                                                    placeholder="Color" 
                                                                    required
                                                                    />  
                                                            </div>
                                                        </div>

                                                        <div class="row col-md d-flex justify-content-between">
                                                            <div class="col-md-4">
                                                                <label class="fs-5 mt-4" >Quantity</label>
                                                            </div>
                                                            <div class="col-md-8 insert-product-input">
                                                                <input type="number" class="rounded-sm border w-100 fs-4 py-4 px-3" min="1" value="${pd.quantity == null ? "" : pd.quantity}" value="1" name="quantity"/>
                                                            </div>
                                                        </div>
                                                        <input name="idPDUpdate"
                                                               value="${pd.id}"
                                                               type="hidden"
                                                               />
                                                        <!--button delete-->
                                                        <div class="col-md-1" style="">
                                                            <button name="idPD"
                                                                    value="${pd.id}"
                                                                    type="submit"
                                                                    id =" deletePD"
                                                                    style="height: 100%; width: 100%;" 
                                                                    class="btn btn-light text-danger "
                                                                    > 
                                                                x
                                                            </button>
                                                        </div>
                                                    </div>
                                                </c:forEach>
                                            </div>
                                            <div class="d-flex justify-content-center" style="magin: 0, auto">
                                                <button class="col-md-12 mt-3 mb-3" type="button" id="add-product-btn" onclick="addMoreProductDetail()" style="font-size: 19px; width: 60px">+</button>
                                            </div>
                                        </div>
                                    </div>

                                    <!--description-->
                                    <div class="row mt-5 d-flex align-items-center">
                                        <div class="col-md-2 text-end">
                                            <label class="fs-4">
                                                <b>Description</b>
                                                <sup><strong style="color: red">(*)</strong></sup>
                                            </label>
                                        </div>
                                        <div class="col-md-10">
                                            <div class="form-floating">
                                                <textarea name='describe' oninput="autoResize(this)" style="min-height: 100px; font-size: 16px" class="form-control" placeholder="Enter describe product..." required>${product.description}</textarea>
                                            </div>
                                        </div>
                                    </div>

                                    <!--submit-->
                                    <div class="row py-5 d-flex">
                                        <button type="submmit" name="btnUpdate" value="update" class="col-md-1 d-block mx-auto w-25 px-4 py-4 border-0 rounded-sm min-w20 text-center bg-success text-white shadow-lg">
                                            <span class="fs-3 fw-semibold">Update</span>
                                        </button>
                                        <a  href="${pageContext.request.contextPath}/homemanager" class="col-md-1 d-block mx-auto w-25 px-4 py-4 border-0 rounded-sm min-w20 text-center bg-success text-white shadow-lg">
                                            <span class="fs-3 fw-semibold">Cancel</span>
                                        </a>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </body>
</html>
