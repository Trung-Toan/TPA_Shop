<%-- 
    Document   : AdminPage
    Created on : Jun 2, 2024, 9:46:55 AM
    Author     : xuxum
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <title>Admin Home</title>
        <meta content="width=device-width, initial-scale=1.0" name="viewport">
        <meta content="" name="keywords">
        <meta content="" name="description">

        <!-- Favicon -->
        <link href="images/favicon.ico" rel="icon">

        <!-- Google Web Fonts -->
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preco<!DOCTYPE html>
              <html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Elegant Dashboard | Dashboard</title>
        <!-- Favicon -->
        <link rel="shortcut icon" href="./img/svg/logo.svg" type="image/x-icon">
        <!-- Custom styles -->
        <link href="${pageContext.request.contextPath}/css/style.min.css" rel="stylesheet" type="text/css"/>
    </head>

    <body>
        <div class="layer"></div>
        <!-- ! Body -->
        <a class="skip-link sr-only" href="#skip-target">Skip to content</a>
        <div class="page-flex">
            <!-- ! Sidebar -->
            <%@include file="./sidebar.jsp" %>
            <div class="main-wrapper">
                <!-- ! Main nav -->
                <%@include file="./mainNav.jsp" %>
                <!-- ! Main -->
                <main class="main users chart-page" id="skip-target">
                    <div class="container">
                        <h2 class="main-title" style="text-align: center">Create Account For Manager</h2>
                        <div class="row" style="justify-content: center; display: flex">
                            <div class="form-container">
                                <form action="${pageContext.request.contextPath}/createAccount" method="post">
                                    <% 
                                        String message = (String) request.getAttribute("message");
                                        if (message != null) {
                                    %>
                                    <div class="alert" style="color: red">
                                        <%= message %>
                                    </div>
                                    <script>
                                        alert('<%= message %>');
                                    </script>
                                    <% 
                                        }
                                    %>  
                                    <table>
                                        <tr>
                                            <td><label for="fullname" class="main-title" style="font-size: 16px">Account:</label></td>
                                            <td><input style="background-color: #cfd1e0" type="text" id="fullname" name="fullname" required></td>
                                        </tr>
                                        <tr>
                                            <td><label for="email" class="main-title" style="font-size: 16px">Email:</label></td>
                                            <td><input style="background-color: #cfd1e0" type="email" id="email" name="email" required></td>
                                        </tr>
                                        <tr>
                                            <td><label for="phoneNumber" class="main-title" style="font-size: 16px">Phone Number:</label></td>
                                            <td><input style="background-color: #cfd1e0" type="text" id="phoneNumber" name="phoneNumber" required></td>
                                        </tr>
                                        <tr>
                                            <td><label for="password" class="main-title" style="font-size: 16px">Password:</label></td>
                                            <td><input style="background-color: #cfd1e0" type="password" id="password" name="password" required></td>
                                        </tr>
                                        <tr>
                                            <td><label for="confirmPassword" class="main-title" style="font-size: 16px">Confirm Password:</label></td>
                                            <td><input style="background-color: #cfd1e0" type="password" id="confirmPassword" name="confirmPassword" required></td>
                                        </tr>
                                        <tr>
                                            <td><label for="status" class="main-title" style="font-size: 16px">Status:</label></td>
                                            <td style="display: flex">
                                                <input type="radio" id="active" name="status" value="active" required>
                                                <label for="active" class="main-title" style="font-size: 16px">Active</label><br>
                                                <input type="radio" id="inactive" name="status" value="inactive" required>
                                                <label for="inactive" class="main-title" style="font-size: 16px">Inactive</label>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td colspan="2" style="text-align: center;">
                                                <input type="hidden" id="roleID" name="roleID" value="2">
                                                <input style="background-color: #cfd1e0" type="submit" value="Create">
                                            </td>
                                        </tr>
                                    </table>
                                </form>
                            </div>
                        </div>
                    </div>
                </main>
                <!-- ! Footer -->
                <%@include file="./footer.jsp" %>
            </div>
        </div>
        <!-- Chart library -->
        <script src="${pageContext.request.contextPath}/plugins/chart.min.js" type="text/javascript"></script>
        <!-- Icons library -->
        <script src="${pageContext.request.contextPath}/plugins/feather.min.js" type="text/javascript"></script>
        <!-- Custom scripts -->
        <script src="${pageContext.request.contextPath}/js/adminPage.js" type="text/javascript"></script>
    </body>

</html>