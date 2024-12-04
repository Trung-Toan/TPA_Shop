<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Reset Password</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@10/swiper-bundle.min.css" />
        <link href="${pageContext.request.contextPath}/style.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA==" crossorigin="anonymous" referrerpolicy="no-referrer"
              />
        <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
    </head>

    <body>
        <section id="header" class="header-login">
            <div class="container">
                <div class="row align-items-center h-78">
                    <div class="col-md-1">
                        <div class="header-logo">
                            <a href="home"><img src="./images/headerLogo.png" alt=""></a>
                        </div>
                    </div>
                </div>
            </div>
        </section>


        <section class="signup-body">
            <form action="/TPA_Shop/resetpass" method="post" class="mx-auto">
                <div class="bg-white p-5 form-login">
                    <h1 class="text-center fw-bold">Reset Password</h1>
                    <div class="">
                        <div class="border-bottom">
                            <span class="fs-5 fw-bold text-black-weak">Verification Code</span>
                            <div class="d-flex align-items-center fs-4 mt-3">
                                <i class='bx bx-user'></i>
                                <input type="text" name="code" value="" placeholder="Verification Code" class="w-100 py-4 outline-0 border-0 ms-3" />
                            </div>
                        </div>
                        <div class="border-bottom mt-5">
                            <span class="fs-5 fw-bold text-black-weak">New password</span>
                            <div class="d-flex align-items-center fs-4 mt-3">
                                <i class='bx bx-lock'></i>
                                <input type="password" value="" name="newPassword" placeholder="New Password" class="w-100 py-4 outline-0 border-0 ms-3" />
                            </div>
                        </div>
                        <div class="border-bottom mt-5">
                            <span class="fs-5 fw-bold text-black-weak">Confirm New Password</span>
                            <div class="d-flex align-items-center fs-4 mt-3">
                                <i class='bx bx-lock'></i>
                                <input type="password" value="" name="confirmPassword" placeholder="New Password" class="w-100 py-4 outline-0 border-0 ms-3" />
                            </div>
                        </div>
                    </div>
                    <% 
                        String message = (String) request.getAttribute("message");
                        if (message != null) {
                    %>
                    <div class="message" style="color: red; font-size: 15px">
                        <%= message %>
                    </div>
                    <% 
                        }
                    %>
                    <button type="submit" name="btnreset" class="d-flex align-items-center justify-content-center mt-5 fw-bold py-2 fs-4 px-5 btn bg-danger text-white  mx-auto">Submit</button>
                </div>
            </form>
            <br>

        </section>
    </body>
</html>
