<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Staff Home</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    </head>
    <body>
        <div class="container">
            <h1>Welcome, ${sessionScope.user}!</h1>
            <p>This is the staff home page.</p>
            <ul class="list-group">
                <li class="list-group-item"><a href="createBlog.jsp">Create Blog</a></li>
                <li class="list-group-item"><a href="editBlog.jsp">Edit Blog</a></li>
                <li class="list-group-item"><a href="deleteBlog.jsp">Delete Blog</a></li>
                <li class="list-group-item"><a href="viewBlog.jsp">View Blog</a></li>
            </ul>
        </div>
    </body>
</html>
