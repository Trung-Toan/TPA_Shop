<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <title>Information</title>
        <meta content="width=device-width, initial-scale=1.0" name="viewport">
        <meta content="" name="keywords">
        <meta content="" name="description">

        <!-- Favicon -->
        <link href="images/favicon.ico" rel="icon">

        <!-- Google Web Fonts -->
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;500;700&display=swap" rel="stylesheet">

        <!-- Custom styles -->
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
                        <h2 class="main-title" style="text-align: center">Company Information</h2>
                        <div class="row" style="justify-content: center; display: flex">
                            <div class="form-container">
                                <div class="users-table table-wrapper">
                                    <table class="posts-table">
                                        <thead>
                                            <tr class="users-table-info">
                                                <th>Name</th>
                                                <th>Location</th>
                                                <th>Date Established</th>
                                                <th>Tax</th>
                                                <th>Last Updated</th>
                                                <th>Edit</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach var="compa" items="${listCompanyInfor}">
                                                <tr>
                                                    <td>${compa.name}</td>
                                                    <td>${compa.address}</td>
                                                    <td>${compa.dateStart}</td>
                                                    <td>${compa.tax}</td>
                                                    <td>${compa.updatedAt}</td>
                                                    <td>
                                                        <button type="button" class="btn btn-primary" onclick="openEditModal('${compa.name}', '${compa.address}', '${compa.dateStart}', '${compa.tax}')">Edit</button>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </main>
                <%@include file="./footer.jsp" %>
            </div>
        </div>

        <!-- Edit Modal -->
        <div id="editModal" class="modal">
            <div class="modal-content">
                <span class="close" onclick="closeEditModal()">&times;</span>
                <form id="editForm" action="inforCompany" method="post">
                    <input type="hidden" id="originalCompanyName" name="originalCompanyName">
                    <div class="form-group">
                        <label for="companyName">Name</label>
                        <input type="text" id="companyName" name="companyName" required>
                    </div>
                    <div class="form-group">
                        <label for="companyAddress">Location</label>
                        <input type="text" id="companyAddress" name="companyAddress" required>
                    </div>
                    <div class="form-group">
                        <label for="companyDateStart">Date Established</label>
                        <input type="date" id="companyDateStart" name="companyDateStart" required>
                    </div>
                    <div class="form-group">
                        <label for="companyTax">Tax</label>
                        <input type="text" id="companyTax" name="companyTax" required>
                    </div>
                    <div class="form-group">
                        <label for="companyDateStart">Last Updated</label>
                        <input type="date" id="companyLastUpdated" name="companyLastUpdated" required>
                    </div>
                    <button type="submit" class="btn btn-primary">Save</button>
                </form>
            </div>
        </div>

        <script>
            function openEditModal(name, address, dateStart, tax) {
                document.getElementById('originalCompanyName').value = name;
                document.getElementById('companyName').value = name;
                document.getElementById('companyAddress').value = address;
                document.getElementById('companyDateStart').value = dateStart;
                document.getElementById('companyTax').value = tax;
                document.getElementById('editModal').style.display = 'block';
            }

            function closeEditModal() {
                document.getElementById('editModal').style.display = 'none';
            }
        </script>

        <style>
            .modal {
                display: none;
                position: fixed;
                z-index: 1;
                left: 0;
                top: 0;
                width: 100%;
                height: 100%;
                overflow: auto;
                background-color: rgb(0,0,0);
                background-color: rgba(0,0,0,0.4);
            }

            .modal-content {
                background-color: #fefefe;
                margin: 15% auto;
                padding: 20px;
                border: 1px solid #888;
                width: 80%;
                max-width: 500px;
            }

            .close {
                color: #aaa;
                float: right;
                font-size: 28px;
                font-weight: bold;
            }

            .close:hover,
            .close:focus {
                color: black;
                text-decoration: none;
                cursor: pointer;
            }

            .form-group {
                margin-bottom: 15px;
            }

            .form-group label {
                display: block;
                margin-bottom: 5px;
            }

            .form-group input {
                width: 100%;
                padding: 8px;
                box-sizing: border-box;
            }

            .btn {
                display: inline-block;
                padding: 10px 20px;
                font-size: 16px;
                cursor: pointer;
                text-align: center;
                text-decoration: none;
                outline: none;
                color: #fff;
                background-color: #4CAF50;
                border: none;
                border-radius: 15px;
                box-shadow: 0 9px #999;
            }

            .btn:hover {
                background-color: #3e8e41
            }

            .btn:active {
                background-color: #3e8e41;
                box-shadow: 0 5px #666;
                transform: translateY(4px);
            }
        </style>
    </body>
</html>
