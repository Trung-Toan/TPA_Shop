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
                        <h1 class="fw-bold my-4">Manager Product</h1>
                        <!-- create new account staff -->
                        <div class="row my-5">
                            <a href="./create-new-account-for-staff" class="text-decoration-none fs-3 text-success
                               border px-3 py-2 fw-bold w-25 mx-auto text-center rounded-lg" >
                                Create new account staff
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

                        <!--main-->
                        <div class=" mr-3 ">
                            <div class=" row bg-light rounded-lg pt-4 pb-4 text-center mb-4">
                                <div class="col-md-1" style="font-size: 14px"><b>ID</b></div>
                                <div class="col-md-1" style="font-size: 14px"><b>Image</b></div>
                                <div class="col-md" style="font-size: 14px"><b>Name</b></div>
                                <div class="col-md" style="font-size: 14px"><b>ID citizen</b></div>
                                <div class="col-md" style="font-size: 14px"><b>Gender</b></div>
                                <div class="col-md" style="font-size: 14px"><b>Date start</b></div>
                                <div class="col-md" style="font-size: 14px"><b>Status</b></div>
                                <div class="col-md" style="font-size: 14px"><b>Setting</b></div>
                            </div>
                            <c:forEach var="u" items="${listUser}">
                                <div class=" border-top bg-white rounded-lg pt-4 mb-3 pb-4 text-center shadow-sm">
                                    <div class="row align-items-center fs-5">
                                        <div class="col-md-1" style="font-size: 14px"><b>${u.id}</b></div>
                                        <div class="col-md-1" style="font-size: 14px">
                                            <img src="./uploadFiles/${u.image}" alt="${u.image}"  style="width: 40px; height: 40px;"/>
                                        </div>
                                        <div class="col-md" style="font-size: 14px">${u.fullname}</div>
                                        <div class="col-md" style="font-size: 14px">${u.idCitizen}</div>
                                        <div class="col-md" style="font-size: 14px">${u.gender == true ? "Male" : "Female"}</div>
                                        <div class="col-md" style="font-size: 14px">${u.dateStart}</div>
                                        <div class="col-md d-flex justify-content-center align-items-center">
                                            <span class="${ u.status == 1
                                                  ? "badge text-success d-flex justify-content-center align-items-center" 
                                                  : "badge text-danger d-flex justify-content-center align-items-center"}" 
                                                  style="font-size: 14px; height: 35px; width: 75px">
                                                ${u.status == 1 ? "Active" : "In active"}
                                            </span>
                                        </div>
                                        <div class="col-md d-flex justify-content-center">
                                            <a href="mangerstaff?id=${u.id}&status=${u.status}" style="font-size: 14px; height: 35px; width: 75px" 
                                               class="${u.status == 1
                                                    ? "btn btn-outline-danger d-flex justify-content-center align-items-center"
                                                    : "btn btn-outline-success d-flex justify-content-center align-items-center"}"
                                               >
                                                ${u.status == 1 ? "Disable " : "Enable"}
                                            </a>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <%@include file="../Admin/footer.jsp" %>
        <script src="../js/app.js"></script>
    </body>
</html>
