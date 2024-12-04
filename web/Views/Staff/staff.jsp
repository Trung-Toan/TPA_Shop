<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.example.model.Blog"%>
<%@page import="com.example.dao.BlogDAO"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Staff Blog Management</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    </head>
    <body>
        <div class="container">
            <h1>Quản lý Blog</h1>

            <h2>Tạo Blog</h2>
            <form action="createBlog" method="post">
                <div class="form-group">
                    <label for="title">Tiêu đề</label>
                    <input type="text" class="form-control" id="title" name="title" required>
                </div>
                <div class="form-group">
                    <label for="content">Nội dung</label>
                    <textarea class="form-control" id="content" name="content" rows="5" required></textarea>
                </div>
                <button type="submit" class="btn btn-primary">Tạo Blog</button>
            </form>

            <h2>Chỉnh sửa Blog</h2>
            <form action="editBlog" method="post">
                <div class="form-group">
                    <label for="editId">ID Blog</label>
                    <input type="text" class="form-control" id="editId" name="editId" required>
                </div>
                <div class="form-group">
                    <label for="editTitle">Tiêu đề mới</label>
                    <input type="text" class="form-control" id="editTitle" name="editTitle">
                </div>
                <div class="form-group">
                    <label for="editContent">Nội dung mới</label>
                    <textarea class="form-control" id="editContent" name="editContent" rows="5"></textarea>
                </div>
                <button type="submit" class="btn btn-primary">Chỉnh sửa Blog</button>
            </form>

            <h2>Xóa Blog</h2>
            <form action="deleteBlog" method="post">
                <div class="form-group">
                    <label for="deleteId">ID Blog</label>
                    <input type="text" class="form-control" id="deleteId" name="deleteId" required>
                </div>
                <button type="submit" class="btn btn-danger">Xóa Blog</button>
            </form>

            <h2>Xem Blog</h2>
            <form action="viewBlog" method="get">
                <div class="form-group">
                    <label for="viewId">ID Blog</label>
                    <input type="text" class="form-control" id="viewId" name="viewId" required>
                </div>
                <button type="submit" class="btn btn-info">Xem Blog</button>
            </form>

            <h2>Danh sách Blogs</h2>
            <c:if test="${not empty blogs}">
                <table class="table">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Tiêu đề</th>
                            <th>Nội dung</th>
                        </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="blog" items="${blogs}">
                        <tr>
                            <td>${blog.id}</td>
                            <td>${blog.title}</td>
                            <td>${blog.content}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </c:if>
        </div>
    </body>
</html>
