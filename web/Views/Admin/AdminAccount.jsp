<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Elegant Dashboard | Dashboard</title>
        <link rel="shortcut icon" href="./img/svg/logo.svg" type="image/x-icon">
        <link href="${pageContext.request.contextPath}/css/style.min.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div class="layer"></div>
        <a class="skip-link sr-only" href="#skip-target">Skip to content</a>
        <div class="page-flex">
            <%@include file="./sidebar.jsp" %>
            <div class="main-wrapper">
                <%@include file="./mainNav.jsp" %>
                <main class="main users chart-page" id="skip-target">
                    <div class="container">
                        <h2 class="main-title">User Account</h2>
                        <div class="row ">
                            <div class="col-lg-9">

                                <!-- Message display section -->
                                <c:if test="${not empty message}">
                                    <div class="alert alert-info" role ="alert">
                                        ${message}
                                    </div>
                                </c:if>
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
                                                <th>Role</th>
                                                <th>Status</th>
                                                <th>Date Start</th>
                                                <th>Action</th>
                                                <th>Delete</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach var="users" items="${listAcc}">
                                                <tr>
                                                    <td>
                                                        <label class="users-table__checkbox">
                                                            <div class="categories-table-img">
                                                                ${users.username}
                                                            </div>
                                                        </label>
                                                    </td>
                                                    <td>
                                                        ${users.fullname}
                                                    </td>
                                                    <td>
                                                        <div class="pages-table-img">
                                                            <c:choose>
                                                                <c:when test="${users.roleID == 3}">Staff</c:when>
                                                                <c:when test="${users.roleID == 2}">Manager</c:when>
                                                                <c:when test="${users.roleID == 1}">Admin</c:when>
                                                                <c:when test="${users.roleID == 0}">User</c:when>
                                                            </c:choose>
                                                        </div>
                                                    </td>
                                                    <td>
                                                        <span class="badge-pending">
                                                            <c:choose>
                                                                <c:when test="${users.status == 1}">Active</c:when>
                                                                <c:otherwise>Banned</c:otherwise>
                                                            </c:choose>
                                                        </span>
                                                    </td>
                                                    <td>${users.dateStart}</td>
                                                    <td>
                                                        <span class="p-relative">
                                                            <button class="dropdown-btn transparent-btn" type="button" title="More info">
                                                                <div class="sr-only">More info</div>
                                                                <i data-feather="more-horizontal" aria-hidden="true"></i>
                                                            </button>
                                                            <ul class="users-item-dropdown dropdown">
                                                                <li>
                                                                    <form action="managerAccounts" method="post">
                                                                        <input type="hidden" name="username" value="${users.username}">
                                                                        <input type="hidden" name="action" value="ban">
                                                                        <button type="submit" class="badge-pending">Ban</button>
                                                                    </form>
                                                                </li>
                                                                <li>
                                                                    <form action="managerAccounts" method="post">
                                                                        <input type="hidden" name="username" value="${users.username}">
                                                                        <input type="hidden" name="action" value="activate">
                                                                        <button type="submit" class="badge-pending">Activate</button>
                                                                    </form>
                                                                </li>
                                                            </ul>
                                                        </span>
                                                    </td>
                                                    <td>
                                                        <form action="managerAccounts" method="post" onsubmit="return confirm('Are you sure you want to delete this account?');">
                                                            <input type="hidden" name="Service" value="Delete">
                                                            <input type="hidden" name="accid" value="${users.id}">
                                                            <button type="submit" class="badge-pending">Delete</button>
                                                        </form>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                                <nav aria-label="Page navigation">
                                    <ul class="pagination justify-content-center" style="display: flex">
                                        <c:if test="${currentPage > 1}">
                                            <li class="page-item">
                                                <a class="page-link" href="managerAccounts?page=${currentPage - 1}" aria-label="Previous">
                                                    <span aria-hidden="true">&laquo;</span>
                                                </a>
                                            </li>
                                        </c:if>
                                        <c:forEach var="i" begin="1" end="${totalPages}">
                                            <li class="page-item ${currentPage == i ? 'active' : ''}">
                                                <a class="page-link" href="managerAccounts?page=${i}"> ${i} , </a>
                                            </li>
                                        </c:forEach>
                                        <c:if test="${currentPage < totalPages}">
                                            <li class="page-item">
                                                <a class="page-link" href="managerAccounts?page=${currentPage + 1}" aria-label="Next">
                                                    <span aria-hidden="true">&raquo;</span>
                                                </a>
                                            </li>
                                        </c:if>
                                    </ul>
                                </nav>
                            </div>
                        </div>
                    </div>
                </main>
                <%@include file="./footer.jsp" %>
            </div>
        </div>
        <script src="${pageContext.request.contextPath}/plugins/chart.min.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/plugins/feather.min.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/js/adminPage.js" type="text/javascript"></script>
    </body>
</html>
