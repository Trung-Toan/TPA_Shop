<%-- 
    Document   : Left
    Created on : Jun 3, 2024, 11:31:26 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin</title>
        <link rel="stylesheet" href="../style.css">
        <!-- Bootstrap CSS -->
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
        <!-- Bootstrap Icons -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-icons/1.5.0/font/bootstrap-icons.min.css" rel="stylesheet">

        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@10/swiper-bundle.min.css" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA==" crossorigin="anonymous" referrerpolicy="no-referrer">
    </head>
    <body >
    <c:set var="productClass" value="py-4 ps-3 mb-3" />
    <c:if test="${active eq 'product'}">
        <c:set var="productClass" value="${productClass} active" />
    </c:if>

    <c:set var="categoryClass" value="py-4 ps-3 mb-3" />
    <c:if test="${active eq 'category'}">
        <c:set var="categoryClass" value="${categoryClass} active" />
    </c:if>

    <c:set var="staffClass" value="py-4 ps-3 mb-3" />
    <c:if test="${active eq 'staff'}">
        <c:set var="staffClass" value="${staffClass} active" />
    </c:if>

    <c:set var="createAccount" value="py-4 ps-3 mb-3" />
    <c:if test="${active eq 'createAccount'}">
        <c:set var="createAccount" value="${createAccount} active" />
    </c:if>
    <div class=" h-100 left-nav-admin p-0"  style="width: 90%">
        <div class="p-5 pe-0 vh-100">
            <ul>
                <li class="${productClass}">
                    <a href="homemanager" class="fs-3 text-white d-flex align-items-center text-decoration-none">
                        <i class='bx bxs-data me-3'></i>
                        <span>Manager products</span>
                    </a>
                </li>
                <li class="${categoryClass}">
                    <a href="category" class="fs-3 text-white d-flex align-items-center text-decoration-none">
                        <i class='bx bx-category me-3'></i>
                        <span>Manager category</span>
                    </a>
                </li>
                <li class="${staffClass}">
                    <a href="mangerstaff" class="fs-3 text-white d-flex align-items-center text-decoration-none">
                        <i class="bi bi-people me-3"></i>
                        <span>Manager staff</span>
                    </a>
                </li>
                <li class="${createAccount}">
                    <a href="create-new-account-for-staff" class="fs-3 text-white d-flex align-items-center text-decoration-none">
                        <i class="bi bi-person-plus me-3"></i>
                        <span>Create account staff</span>
                    </a>
                </li>

                <li class="py-4 ps-3 mb-3">
                    <a href="#" class="fs-3 text-white d-flex align-items-center text-decoration-none">
                        <i class='bx bx-circle-three-quarter me-3'></i>
                        <span>Statistic</span>
                    </a>
                </li> 


                <li class="py-4 ps-3 mb-3">
                    <a href="#" class="fs-2 text-white d-flex align-items-center text-decoration-none">
                        <i class='bx bx-purchase-tag-alt me-3'></i>
                        <span>Offer</span>
                    </a>
                </li>
            </ul>
        </div>
    </div>
</body>
</html>
