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
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@10/swiper-bundle.min.css" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA==" crossorigin="anonymous" referrerpolicy="no-referrer">
        <script>
            function myFun() {
                var selectElement = event.target;
                if (selectElement.value === 'other') {
                    event.preventDefault();
                    var modal = new bootstrap.Modal(document.getElementById('addCategoryModal'));
                    modal.show();
                } else {
                    document.getElementById("frm").submit();
                }
            }
        </script>
        <style>
            #alert-mess {
                position: fixed;
                top: 10px;
                right: 100px;
            }
        </style>
    </head>
    <body>
        <section class="bg-weak">
            <div class="container bg-white">
                <div class="row px-5 justify-content-center">
                    <div class="col-md-7 mt-5">
                        <div >
                            <h1 class="my-5 text-center">Create new product</h1>
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
                            <!--from select category and sub category--> 
                            <form action="selectCategory" method="post" id="frm">
                                <!--category and sub category-->                                    
                                <div class="row mt-3">
                                    <div class="col-md-2 text-end">
                                        <label class="fs-4">
                                            <b>Category product<sup style="color: red; font-size: 14px">*</sup></b>
                                        </label>
                                    </div>
                                    <div class="col-md-4">
                                        <select name="category" class="form-select fs-4 py-3" onchange="myFun()">
                                            <c:forEach items="${category}" var="ct">
                                                <option value="${ct.id}" ${ct.id == Integer.parseInt(idCategory) ? "selected" : ""} >${ct.name}</option>
                                            </c:forEach>
                                            <option value="other">Other...</option>
                                        </select>
                                    </div>
                                    <div class="col-md-2 text-end">
                                        <label class="fs-4">
                                            <b>Sub category product<sup style="color: red; font-size: 14px">*</sup></b>
                                        </label>
                                    </div>
                                    <div class="col-md-4 ">
                                        <select name="subCategory" class="form-select fs-4 py-3" onchange="myFun()">
                                            <c:forEach items="${subCategory.findSubCategory(idCategory)}" var="st">
                                                <option  value="${st.id}" ${st.id == Integer.parseInt(idSubCategory) ? "selected" : ""} >${st.name}</option>
                                            </c:forEach>
                                            <option value="other">Other...</option>
                                        </select>
                                    </div>
                                </div>
                            </form> 

                            <!-- Add Category Modal -->
                            <div class="modal fade" id="addCategoryModal" tabindex="-1" aria-labelledby="addCategoryModalLabel" aria-hidden="true">
                                <div class="modal-dialog">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h3 class="modal-title" id="addCategoryModalLabel">Add Category</h3>
                                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                        </div>
                                        <div class="modal-body">
                                            <form>
                                                <div class="mb-3">
                                                    <label for="categoryName" class="form-label" style="font-size: 14px">Category Name</label>
                                                    <input type="text" name="cateAdd" class="form-control" style="font-size: 14px; height: 30px" id="categoryName">
                                                </div>
                                                <div class="mb-3">
                                                    <label for="subcategories" class="form-label" style="font-size: 14px">Subcategories (comma separated)</label>
                                                    <input type="text" name="subAdd" class="form-control" style="font-size: 14px; height: 30px" id="subcategories">
                                                </div>
                                                <button type="submit" name="btnAdd" class="btn btn-primary" style="font-size: 14px">Add</button>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!--form process add product-->
                            <form action="addproduct" method="post">
                                <!--id product-->
                                <input type="hidden" name="idSub" value="${idSubCategory}"/>


                                <div class="my-5">
                                    <!--name product-->
                                    <div class="row d-flex align-items-center">
                                        <div class="col-md-2 text-end">
                                            <label class="fs-4" for="product-name">
                                                <b>Name product<sup style="color: red; font-size: 14px">*</sup></b>
                                            </label>
                                        </div>
                                        <div class="col-md-10 insert-product-input">
                                            <input 
                                                type="text" id="product-name" 
                                                class="rounded-sm border w-100 fs-3 py-4 px-3" 
                                                name="productName"
                                                placeholder="Product name" required
                                                />  
                                        </div>
                                    </div>
                                    <div class="container mt-5">
                                        <div class="row d-flex align-items-center">
                                            <div class="col-md-2 text-end">
                                                <label class="fs-4" for="product-price">
                                                    <b>Price<sup style="color: red; font-size: 14px">*</sup></b>
                                                </label>
                                            </div>
                                            <div class="col-md-4 insert-product-input">
                                                <input type="number" min="0" id="product-price" name="price"
                                                       class="rounded-sm border w-100 fs-3 py-3 px-3" placeholder="price" required/>
                                                <span class="input-suffix fs-3">VNĐ</span>
                                            </div>

                                            <!--sale-->
                                            <div class="col-md-2 text-end">
                                                <label class="fs-4" for="product-price">
                                                    <b>Sale</b>
                                                </label>
                                            </div>
                                            <div class="col-md-4 insert-product-input">
                                                <input type="number" name="discount" value="0"
                                                       class="rounded-sm border w-100 fs-3 py-3 px-3"/>
                                                <span class="input-suffix fs-3">%</span>
                                            </div>
                                        </div>
                                    </div>

                                    <!--product for-->
                                    <div class="row mt-5 d-flex align-items-center">
                                        <div class="col-md-2 text-end">
                                            <label class="fs-4" for="product-price">
                                                <b>Product for<sup style="color: red; font-size: 14px">*</sup></b>
                                            </label>
                                        </div>
                                        <div class="col-md-10 d-flex align-items-center insert-product-input">
                                            <label class="d-flex align-items-center me-4">
                                                <input type="radio" name="gender" value="1" checked/>
                                                <span class="px-2 fs-3 mb-0">Male</span>
                                            </label>
                                            <label class="d-flex align-items-center me-4">
                                                <input type="radio" name="gender" value="2"/>
                                                <span class="px-2 fs-3 mb-0">Female</span>
                                            </label>
                                            <label class="d-flex align-items-center">
                                                <input type="radio" name="gender" value="0"/>
                                                <span class="px-2 fs-3 mb-0">Both male and female</span>
                                            </label>
                                        </div>
                                    </div>

                                    <!--description-->
                                    <div class="row mt-5 d-flex align-items-center">
                                        <div class="col-md-2 text-end">
                                            <label class="fs-4">
                                                <b>Description <sup style="color: red; font-size: 14px">*</sup></b>
                                            </label>
                                        </div>
                                        <div class="col-md-10">
                                            <div class="form-floating">
                                                <textarea 
                                                    name='describe' 
                                                    oninput="autoResize(this)" 
                                                    style="min-height: 100px; font-size: 16px" 
                                                    class="form-control" 
                                                    placeholder="Enter describe product..." 
                                                    required
                                                    ></textarea>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <!--button submit and cancel-->
                                <div class="row py-5">
                                    <button type="submmit" name="submit" value="Insert" class="col-md-2 d-block mx-auto w-25 px-4 py-4 border-0 rounded-sm min-w20 text-center bg-success text-white shadow-lg">
                                        <span class="fs-3 fw-semibold">Insert</span>
                                    </button>
                                    <a  
                                        href="./homemanager" 
                                        class="
                                        col-md-2
                                        d-block mx-auto w-25 px-4 py-4 border-0 rounded-sm min-w20
                                        text-center text-decoration-none bg-success text-white shadow-lg">
                                        <span class="fs-3 fw-semibold">Cancel</span>
                                    </a>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>

            </div>
        </section>
    </body>
</html>
