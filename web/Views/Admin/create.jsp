<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Company Information</title>
    </head>
    <body>
        <h1>Company Information</h1>
        <form action="editCompany" method="post">
            <input type="hidden" name="id" value="${company.id}">
            <label for="name">Name:</label>
            <input type="text" id="name" name="name" value="${company.name}" required><br>

            <label for="location">Location:</label>
            <input type="text" id="location" name="address" value="${company.address}" required><br>

            <label for="date_established">Date Established:</label>
            <input type="date" id="date_established" name="date_established" value="${company.dateStart}" required><br>

            <label for="registration_number">Registration Number:</label>
            <input type="text" id="registration_number" name="registration_number" value="${company.tax}" required><br>

            <label for="last_updated">Last Updated:</label>
            <input type="date" id="last_updated" name="last_updated" value="${company.updatedAt}" required><br>

            <input type="submit" value="Update">
        </form>
    </body>
</html>
s