<%-- 
    Document   : Admin
    Created on : 13 Oct, 2023, 12:03:22 PM
    Author     : HP
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.text.Normalizer" %>
<%@page import="java.util.regex.Pattern" %>
<%@page import="DAL.*" %>
<%@page import="Models.*" %>
<%@page import="java.util.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home manager</title>
        <link rel="stylesheet" href="./style.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@10/swiper-bundle.min.css" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA==" crossorigin="anonymous" referrerpolicy="no-referrer">
        <style>
            .homeAdmin #left {
                position: fixed;
                top: 0;
                left: 0;
            }
            .homeAdmin #alert-mess {
                position: fixed;
                top: 12%;
                right: 25px;
            }
        </style>
        <script>
            function myFun() {
                document.getElementById("frm").submit(); // Gửi biểu mẫu sau khi đã thay đổi
            }
        </script>
    </head>
    <body>
        <%@include file="../Headers.jsp" %>
        <section>
            <div class="homeAdmin row mb-5">
                <div class="col-md-3 container" id="left" style="height: 90%; margin-top: 5%;">
                    <%@include file="../Left.jsp" %>
                </div>
                <div class="container col-md-9 row h-100" style="margin-left: auto">
                    <div class="col-md h-100">
                        <!--filter-->
                        <form action="homemanager" method="post" id="frm">
                            <div class="d-flex justify-content-between align-items-center my-4">
                                <h1 class="fw-bold text-center">Product</h1>
                                <div class="d-flex justify-content-center align-items-center ms-4" style="width: 350px">
                                    <div class="bg-white border rounded-md overflow-hidden d-flex align-items-center w-100">
                                        <button type="submit" class="btn p-3 border-0 text-secondary" name="Service" value="SearchProduct" />
                                        <i class="fa-solid fa-magnifying-glass fs-3"></i>
                                        </button>
                                        <input 
                                            class="border-0 w-100 py-4 px-3 fs-4" 
                                            type="text" 
                                            name="searchBy" 
                                            value="${valueSearch}" 
                                            placeholder="Search product by name"
                                            />
                                    </div>
                                </div>
                            </div>
                            <!--Add product-->
                            <div class="row my-5">
                                <a href="./addproduct" class="text-decoration-none fs-3 text-success
                                   border px-3 py-2 fw-bold w-25 mx-auto text-center rounded-lg" >
                                    Add more product
                                </a>
                            </div>

                            <!--Alert messenger-->
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
                            <!--filter-->
                            <div class="row my-5 d-flex justify-content-between">
                                <div class="col-md-5 row">
                                    <div class="col-md row d-flex justify-content-center align-items-center" style="margin-right: 18px">
                                        <label class="ms-auto fs-5 col-md text-end">Category</label>
                                        <select name="fCategery" class="form-select ms-auto fs-5 col-md" onchange="myFun()" aria-label="Default select example">
                                            <option value="all" ${fCategory.equals("all") ? "selected" : ""}>All</option>
                                            <c:catch var="catchException">
                                                <c:forEach var="c" items="${listCategory}">
                                                    <option value="${c.id}" ${!fCategory.equals("all") ? Integer.parseInt(fCategory) == c.id ? "selected" : "" : ""}>
                                                        ${c.name}
                                                    </option>
                                                </c:forEach>
                                            </c:catch>
                                            <c:if test="${not empty catchException}">
                                                <p>Thông tin ngoại lệ: ${catchException}</p>
                                            </c:if>
                                        </select>
                                    </div>
                                    <div class="col-md row d-flex justify-content-center align-items-center" style="margin-right: 18px">
                                        <label class="ms-auto fs-5 col-md text-end">Subcategory</label>
                                        <select name="fSubCategery" class="form-select ms-auto fs-5 col-md" onchange="myFun()" aria-label="Default select example">
                                            <option value="all" ${"all".equals(fSubCategory) ? "selected" : ""}>All</option>
                                            <c:catch var="catchException">
                                                <c:forEach var="sc" items="${listSubCategory}">
                                                    <option value="${sc.id}" ${!fSubCategory.equals("all") ? Integer.parseInt(fSubCategory) == sc.id ? "selected" : "": ""}>
                                                        ${sc.name}
                                                    </option>
                                                </c:forEach>
                                            </c:catch>
                                            <c:if test="${not empty catchException}">
                                                <p>Thông tin ngoại lệ: ${catchException}</p>
                                            </c:if>
                                        </select>
                                    </div>
                                </div>
                                <div class="col-md-5 row">
                                    <div class="col-md row d-flex justify-content-center align-items-center" style="margin-right: 18px">
                                        <label class="ms-auto fs-5 col-md text-end">Ascending</label>
                                        <select name="sortAsc" class="form-select ms-auto fs-5 col-md" onchange="myFun()" aria-label="Default select example">
                                            <option value="none" ${sortAsc.equals("none") ? "selected" : ""}>None</option>
                                            <option value="id" ${sortAsc.equals("id") ? "selected" : ""}>Id</option>
                                            <option value="name" ${sortAsc.equals("name") ? "selected" : ""}>Name product</option>
                                            <option value="price" ${sortAsc.equals("price") ? "selected" : ""}>Price product</option>
                                            <option value="createdAt" ${sortAsc.equals("createdAt") ? "selected" : ""}>Date created</option>
                                            <option value="updatedAt" ${sortAsc.equals("updatedAt") ? "selected" : ""}>Date updated</option>
                                        </select>
                                    </div>
                                    <div class="col-md row d-flex justify-content-center align-items-center" style="margin-right: 18px">
                                        <label class="ms-auto fs-5 col-md text-end">Descending</label>
                                        <select name="sortDes" class="form-select ms-auto fs-5 col-md" onchange="myFun()" aria-label="Default select example">
                                            <option value="none" ${sortDes.equals("none") ? "selected" : ""}>None</option>
                                            <option value="id" ${sortDes.equals("id") ? "selected" : ""}>Id</option>
                                            <option value="name" ${sortDes.equals("name") ? "selected" : ""}>Name product</option>
                                            <option value="price" ${sortDes.equals("price") ? "selected" : ""}>Price product</option>
                                            <option value="createdAt" ${sortDes.equals("createdAt") ? "selected" : ""}>Date created</option>
                                            <option value="updatedAt" ${sortDes.equals("updatedAt") ? "selected" : ""}>Date updated</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="col-md-12 mt-3">
                                    <c:if test='${fCategory ne "all" || fSubCategory ne "all" || sortAsc ne "none" || sortDes ne "none" || valueSearch ne ""}'>
                                        <button type="submit" class="btn btn-outline-success" name="clear">
                                            Clear all
                                        </button>
                                    </c:if>

                                </div>
                            </div>

                        </form>

                        <!--main-->
                        <c:choose>
                            <c:when test="${product.size() == 0}">
                                <div class="d-flex justify-content-center h-100" style="margin-top: 10%">
                                    <h1 style="font-size: 80px; font-family: Arial, sans-serif; font-weight: bold; color: #d3d3d3; text-shadow: 1px 1px 2px rgba(0, 0, 0, 0.1);">
                                        Not found
                                    </h1>
                                </div>
                            </c:when>
                            <c:otherwise>
                                <div class="">
                                    <div class="row container bg-weak rounded-lg px-3 py-4 fs-4 text-center">
                                        <div class="col-md-1"><b>ID</b></div>
                                        <div class="col-md-1"><b>Image</b></div>
                                        <div class="col-md"><b>Name</b></div>
                                        <div class="col-md-1"><b>Price</b></div>
                                        <div class="col-md-1"><b>Discount</b></div>
                                        <div class="col-md-1"><b>Sub category</b></div>
                                        <div class="col-md-1"><b>Date create</b></div>
                                        <div class="col-md-1"><b>Date update</b></div>
                                        <div class="col-md-1"><b>Setting</b></div>
                                    </div>
                                    <div class="">
                                        <c:forEach var="pro" items="${product}">
                                            <div class="mt-5 border-top bg-white rounded-lg border text-center">
                                                <div class="row container position-relative fs-4 px-3 py-4 d-flex align-items-center justify-content-between">
                                                    <!--id-->
                                                    <div class="col-md-1">${pro.id}</div>
                                                    <!--image-->
                                                    <div class="col-md-1"> 
                                                        <c:choose>
                                                            <c:when test="${image.getImage(listImage, pro.id) != null && !image.getImage(listImage, pro.id).isEmpty()}">
                                                                <img src="./images/${image.getImage(listImage, pro.id).get(0).getName()}" alt="${pro.name}"/>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <img src="" alt="${pro.name}"/>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </div>
                                                    <!--name-->
                                                    <div class="col-md">${pro.name}</div>
                                                    <!--price-->
                                                    <div class="col-md-1">${pro.price}</div>
                                                    <!--discount-->
                                                    <div class="col-md-1">${pro.discount}</div>
                                                    <!--sub category-->
                                                    <div class="col-md-1">${(subCategory.getSubCategory(listSubCategory, pro.subCategoryID)).getName()}</div>
                                                    <!--date create-->
                                                    <div class="col-md-1">${date.format(pro.createdAt)}</div>
                                                    <!--date update-->
                                                    <div class="col-md-1">${date.format(pro.updatedAt)}</div>
                                                    <!--button-->
                                                    <div class="col-md-1 button-submit">
                                                        <div class=" d-flex">
                                                            <a href="productProcess?type=0&id=${pro.id}" class="me-4 text-info fw-bold fs-5 text-decoration-none">Detail</a>
                                                            <a href="productProcess?type=1&id=${pro.id}" class="text-danger fw-bold fs-5 text-decoration-none">Delete</a>
                                                        </div>
                                                    </div>

                                                </div>
                                            </div>
                                        </c:forEach>  
                                    </div>
                                </c:otherwise>
                            </c:choose>
                        </div> 
                    </div>
                </div>
            </div>
        </section>
        <%@include file="../Admin/footer.jsp" %>
        <script src="../js/app.js"></script>
    </body>
</html>
