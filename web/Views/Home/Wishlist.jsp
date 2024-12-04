<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
        <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Wishlist</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@10/swiper-bundle.min.css" />
        <link rel="stylesheet" href="./style.css">
        <link href="../../css/wishlist.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA==" crossorigin="anonymous" referrerpolicy="no-referrer/>
        <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
    </head>
    <body>
        <%@include file="../Headers.jsp" %>
        <div class="container">
            <table class="wishlist-table">
                <thead>
                    <tr>
                        <th>Product</th>
                        <th>Details</th>
                        <th>Price</th>
                        <th>Favorite</th>
                    </tr>
                </thead>
                <tbody>
                    <tr class="wishlist-row">
                        <td>
                            <img src="https://via.placeholder.com/100" alt="Product Image" class="thumb-img"/>
                        </td>
                        <td>
                            <div class="info">
                                <div><strong>Mã Sản Phẩm:</strong> 470251</div>
                                <div><strong>Màu sắc:</strong> Blue</div>
                                <div><strong>Kích thước:</strong> XXL</div>
                            </div>
                        </td>
                        <td>
                            <div class="price-original">784.000 VND</div>
                        </td>
                        <td>
                            <div class="favorite large swiper-no-swiping">
                                <button aria-label="favorite">
                                    <span class="fr-icon active favorite_large" role="img" aria-label="Favorite" style="font-size: 24px;">
                                        <span class="fr-implicit">Thêm vào danh sách mong muốn</span>
                                    </span>
                                </button>
                            </div>
                        </td>
                    </tr>
                    <!-- Repeat similar rows for more products -->
                </tbody>
            </table>
        </div>

        <footer>
            <p>Wishlist Page &copy; 2024</p>
        </footer>
    </body>
</html>

