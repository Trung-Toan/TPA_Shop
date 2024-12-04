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

    </head>
    <body>
        <%@include file="../Headers.jsp" %>
        <section>
            <div class="homeAdmin row mb-5">
                <div class="col-md-3" id="left" style="height: 90%; margin-top: 5%;">
                    <%@include file="../Left.jsp" %>
                </div>
                <div class="container col-md-9 row h-100" style="margin-left: auto">
                    <!--Alert messenger-->
                    <div class="col-md h-100">
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
                    </div>
                    <form action="category" method="post">
                        <div class="container mt-5 p-4" style="font-size: 14px">
                            <h1 class="text-center mb-4"><b>Category Management</b></h1>
                            <div class="d-flex justify-content-end mb-3">
                                <a class="btn btn-primary text-center d-flex justify-content-center align-items-center" 
                                   style="height: 40px; width: 120px; font-size: 14px" 
                                   data-bs-toggle="modal" data-bs-target="#addCategoryModal"
                                   >
                                    Add Category
                                </a>
                            </div>

                            <% int i = 1; %>
                            <div class="container text-center">
                                <table class="table table-hover table-bordered table-striped">
                                    <thead class="">
                                        <tr class="row">
                                            <th class="col-md-1 bg-secondary">No.</th>
                                            <th class="col-md bg-secondary">Category</th>
                                            <th class="col-md bg-secondary">Sub category</th>
                                            <th class="col-md-3 bg-secondary">Action</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="c" items="${listCategory}">
                                            <tr class="row">
                                                <td class="col-md-1 d-flex justify-content-center align-items-center"><%=i++%></td>
                                                <td class="col-md text-start d-flex justify-content-center align-items-center">
                                                    <input class="form-control" type="text" value="${c.name}" name="nameC${c.id}" style="font-size: 14px; height: 30px" placeholder="Enter categories">
                                                </td>
                                                <td class="col-md">
                                                    <c:set var="listSubCategory" value="${subCategory.getSubCategory(c.id)}" />
                                                    <c:forEach var="subCategory" items="${listSubCategory}">
                                                        <div class="d-flex mb-2 justify-content-between">
                                                            <input name="idSubUpdate${c.id}" value="${subCategory.id}" type="hidden" />
                                                            <input class="form-control" type="text" name="nameSub${c.id}" value="${subCategory.name}" style="font-size: 14px; width: 80%; height: 30px" placeholder="Enter subcategories" />
                                                            <button name="btnDeleteSub" type="submit" value="${subCategory.id}" class="btn btn-outline-danger" style="font-size: 14px; width: 70px; height: 30px">Delete</button>
                                                        </div>
                                                    </c:forEach>
                                                    <div class="d-flex justify-content-between">
                                                        <input name="nameAddSub${c.id}" type="text" class="form-control" style="font-size: 14px; width: 80%; height: 30px" id="newSubcategories${c.id}" placeholder="Add...">
                                                        <button name="btnAddSub" value="${c.id}" type="submit" class="btn btn-outline-primary" style="font-size: 14px; width: 70px; height: 30px">Add</button>
                                                    </div>
                                                </td>
                                                <td class="col-md-3 d-flex justify-content-around align-items-center">
                                                    <button class="btn btn-success btn-sm text-light" value="${c.id}" name="btnUpdate" type="submit" style="font-size: 14px; height: 40px; width: 120px;">
                                                        Update
                                                    </button>
                                                    <button class="btn btn-danger btn-sm" name="btnDeleteC" value="${c.id}" type="submit" style="font-size: 14px; height: 40px; width: 120px;">
                                                        Delete
                                                    </button>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>

                            </div>
                        </div>

                        <!-- Add Category Modal -->
                        <div class="modal fade" id="addCategoryModal" tabindex="-1" aria-labelledby="addCategoryModalLabel" aria-hidden="true">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h3 class="modal-title" id="addCategoryModalLabel">Add Category</h3>
                                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                    </div>
                                    <div class="modal-body">
                                        <div class="mb-3">
                                            <label for="categoryName" class="form-label" style="font-size: 14px">Category Name</label>
                                            <input type="text" name="cateAdd" class="form-control" style="font-size: 14px; height: 30px" id="categoryName">
                                        </div>
                                        <div class="mb-3">
                                            <label for="subcategories" class="form-label" style="font-size: 14px">Subcategories (comma separated)</label>
                                            <input type="text" name="subAdd" class="form-control" style="font-size: 14px; height: 30px" id="subcategories">
                                        </div>
                                        <button type="submit" name="btnAdd" class="btn btn-primary" style="font-size: 14px">Add</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form>

                </div>
            </div>
        </section>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/5.1.3/js/bootstrap.min.js"></script>
    </body>
</html>
