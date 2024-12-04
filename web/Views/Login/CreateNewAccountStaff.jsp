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
                    create new account staff 
                </div>
            </div>
        </section>
        <%@include file="../Admin/footer.jsp" %>
        <script src="../js/app.js"></script>
    </body>
</html>
