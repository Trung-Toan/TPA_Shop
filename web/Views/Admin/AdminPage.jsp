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
                        <h2 class="main-title">Dashboard</h2>
                        <div class="row stat-cards">
                            <div class="col-md-6 col-xl-3">
                                <article class="stat-cards-item">
                                    <div class="stat-cards-icon primary">
                                        <i data-feather="bar-chart-2" aria-hidden="true"></i>
                                    </div>
                                    <div class="stat-cards-info">
                                        <p class="stat-cards-info__num">1478 286</p>
                                        <p class="stat-cards-info__title">Total visits</p>
                                        <p class="stat-cards-info__progress">
                                            <span class="stat-cards-info__profit success">
                                                <i data-feather="trending-up" aria-hidden="true"></i>4.07%
                                            </span>
                                            Last month
                                        </p>
                                    </div>
                                </article>
                            </div>
                            <div class="col-md-6 col-xl-3">
                                <article class="stat-cards-item">
                                    <div class="stat-cards-icon warning">
                                        <i data-feather="file" aria-hidden="true"></i>
                                    </div>
                                    <div class="stat-cards-info">
                                        <p class="stat-cards-info__num">1478 286</p>
                                        <p class="stat-cards-info__title">Total visits</p>
                                        <p class="stat-cards-info__progress">
                                            <span class="stat-cards-info__profit success">
                                                <i data-feather="trending-up" aria-hidden="true"></i>0.24%
                                            </span>
                                            Last month
                                        </p>
                                    </div>
                                </article>
                            </div>
                            <div class="col-md-6 col-xl-3">
                                <article class="stat-cards-item">
                                    <div class="stat-cards-icon purple">
                                        <i data-feather="file" aria-hidden="true"></i>
                                    </div>
                                    <div class="stat-cards-info">
                                        <p class="stat-cards-info__num">1478 286</p>
                                        <p class="stat-cards-info__title">Total visits</p>
                                        <p class="stat-cards-info__progress">
                                            <span class="stat-cards-info__profit danger">
                                                <i data-feather="trending-down" aria-hidden="true"></i>1.64%
                                            </span>
                                            Last month
                                        </p>
                                    </div>
                                </article>
                            </div>
                            <div class="col-md-6 col-xl-3">
                                <article class="stat-cards-item">
                                    <div class="stat-cards-icon success">
                                        <i data-feather="feather" aria-hidden="true"></i>
                                    </div>
                                    <div class="stat-cards-info">
                                        <p class="stat-cards-info__num">1478 286</p>
                                        <p class="stat-cards-info__title">Total visits</p>
                                        <p class="stat-cards-info__progress">
                                            <span class="stat-cards-info__profit warning">
                                                <i data-feather="trending-up" aria-hidden="true"></i>0.00%
                                            </span>
                                            Last month
                                        </p>
                                    </div>
                                </article>
                            </div>
                        </div>
                        <!--Company Information-->
                        <div>
                            <h3 style="color: #FFFFFF">Information Company</h3>
                        </div>
                        <div class="users-table table-wrapper">
                            <table class="posts-table">
                                <thead>
                                    <tr class="users-table-info">
                                        <th>
                                            <label class="users-table__checkbox ms-20">
                                                Name
                                            </label>
                                        </th>
                                        <th>Location</th>
                                        <th>Date Established</th>
                                        <th>Tax</th>
                                        <th>Last Updated</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="com" items="${listCompanyInfor}">
                                        <tr>
                                            <td>
                                                <label class="users-table__checkbox">
                                                    <div style="word-break: break-all;">
                                                        ${com.name}
                                                    </div>
                                                </label>
                                            </td>
                                            <td>${com.address}</td>
                                            <td>${com.dateStart}</td>
                                            <td>${com.tax}</td>
                                            <td>${com.updatedAt}</td>
                                        </tr>      
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                        <div class="row">
                            <div class="col-lg-9">
<!--                                <div class="chart">
                                    <canvas id="myChart" aria-label="Site statistics" role="img"></canvas>
                                </div>-->
                                <div>
                                    <h3 style="color: #FFFFFF">Account</h3>
                                </div>
                                <div class="users-table table-wrapper">
                                    <table class="posts-table">
                                        <thead>
                                            <tr class="users-table-info">
                                                <th>
                                                    <label class="users-table__checkbox ms-20">
                                                        Accounts
                                                    </label>
                                                </th>
                                                <th>Name</th>
                                                <th>Status</th>
                                                <th>Date of Birth</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach var="user" items="${listAccView}">
                                                <tr>
                                                    <td>
                                                        <label class="users-table__checkbox">
                                                            <div class="categories-table-img">
                                                                ${user.username}
                                                            </div>
                                                        </label>
                                                    </td>
                                                    <td>
                                                        ${user.fullname}
                                                    </td>
                                                    <td>
                                                        <span class="badge-pending">
                                                            <c:choose>
                                                                <c:when test ="${user.status == 1}">Active</c:when>
                                                                <c:otherwise>Banned</c:otherwise>
                                                            </c:choose>
                                                        </span>
                                                    </td>
                                                    <td>${user.dob}</td>
                                                </tr>      
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                                <!--list Product-->
                                <div>
                                    <h3 style="color: #FFFFFF">Product</h3>
                                </div>
                                <div class="users-table table-wrapper">
                                    <table class="posts-table">
                                        <thead>
                                            <tr class="users-table-info">
                                                <th>
                                                    <label class="users-table__checkbox ms-20">
                                                        Name
                                                    </label>
                                                </th>
                                                <th>Image</th>
                                                <th>Price</th>
                                                <th>Discount</th>
                                                <th>Gender</th>
                                                <th>Created_At</th>
                                                <th>Sub_Category</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach var="pro" items="${listProduct}">
                                                <tr>
                                                    <td>
                                                        <label class="users-table__checkbox">
                                                            <div style="word-break: break-all;">
                                                                ${pro.name}
                                                            </div>
                                                        </label>
                                                    </td>
                                                    <td>
                                                        <img src="src" alt="not images"/>
                                                    </td>
                                                    <td>${pro.price}</td>
                                                    <td>${pro.discount}</td>
                                                    <td>${pro.gender}</td>
                                                    <td>${pro.createdAt}</td>
                                                    <td>${pro.subCategoryID}</td>
                                                </tr>      
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                            <div class="col-lg-3">
                                <article class="customers-wrapper">
                                    <canvas id="customersChart" aria-label="Customers statistics" role="img"></canvas>
                                    <!--              <p class="customers__title">New Customers <span>+958</span></p>
                                    <p class="customers__date">28 Daily Avg.</p>
                                    <picture><source srcset="./img/svg/customers.svg" type="image/webp"><img src="./img/svg/customers.svg" alt=""></picture> -->
                                </article>
                                <article class="white-block">
                                    <div class="top-cat-title">
                                        <h3>Top categories</h3>
                                        <p>28 Categories, 1400 Posts</p>
                                    </div>
                                    <ul class="top-cat-list">
                                        <li>
                                            <a href="##">
                                                <div class="top-cat-list__title">
                                                    Lifestyle <span>8.2k</span>
                                                </div>
                                                <div class="top-cat-list__subtitle">
                                                    Dailiy lifestyle articles <span class="purple">+472</span>
                                                </div>
                                            </a>
                                        </li>
                                        <li>
                                            <a href="##">
                                                <div class="top-cat-list__title">
                                                    Tutorials <span>8.2k</span>
                                                </div>
                                                <div class="top-cat-list__subtitle">
                                                    Coding tutorials <span class="blue">+472</span>
                                                </div>
                                            </a>
                                        </li>
                                        <li>
                                            <a href="##">
                                                <div class="top-cat-list__title">
                                                    Technology <span>8.2k</span>
                                                </div>
                                                <div class="top-cat-list__subtitle">
                                                    Dailiy technology articles <span class="danger">+472</span>
                                                </div>
                                            </a>
                                        </li>
                                        <li>
                                            <a href="##">
                                                <div class="top-cat-list__title">
                                                    UX design <span>8.2k</span>
                                                </div>
                                                <div class="top-cat-list__subtitle">
                                                    UX design tips <span class="success">+472</span>
                                                </div>
                                            </a>
                                        </li>
                                        <li>
                                            <a href="##">
                                                <div class="top-cat-list__title">
                                                    Interaction tips <span>8.2k</span>
                                                </div>
                                                <div class="top-cat-list__subtitle">
                                                    Interaction articles <span class="warning">+472</span>
                                                </div>
                                            </a>
                                        </li>
                                        <li>
                                            <a href="##">
                                                <div class="top-cat-list__title">
                                                    App development <span>8.2k</span>
                                                </div>
                                                <div class="top-cat-list__subtitle">
                                                    Mobile development articles <span class="warning">+472</span>
                                                </div>
                                            </a>
                                        </li>
                                        <li>
                                            <a href="##">
                                                <div class="top-cat-list__title">
                                                    Nature <span>8.2k</span>
                                                </div>
                                                <div class="top-cat-list__subtitle">
                                                    Wildlife animal articles <span class="warning">+472</span>
                                                </div>
                                            </a>
                                        </li>
                                        <li>
                                            <a href="##">
                                                <div class="top-cat-list__title">
                                                    Geography <span>8.2k</span>
                                                </div>
                                                <div class="top-cat-list__subtitle">
                                                    Geography articles <span class="primary">+472</span>
                                                </div>
                                            </a>
                                        </li>
                                    </ul>
                                </article>
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