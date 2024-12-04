<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Blog</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    </head>
    <body>
        <div class="container">
            <h1>Chi tiết Blog</h1>
            <c:if test="${not empty blog}">
                <div>
                    <h2>${blog.title}</h2>
                    <p>${blog.content}</p>
                    <p><small>Created at: ${blog.createdAt}</small></p>
                </div>
            </c:if>
        </div>
    </body>
</html>
