<%-- 
    Document   : ChangePass
    Created on : May 19, 2024, 11:43:37 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Change Password</title>
    </head>
    <body>
        <h1>Change Password</h1>
        <form action="changepass" method="post">
            <table>
                <tr>
                    <td>Current Password</td>
                    <td><input type="password" name="currentPassword" ></td></tr>
                <tr>
                    <td>New Password</td>
                    <td><input type="password" name="newPassword"></td>
                </tr>
                <tr>
                    <td>Confirm Password</td>
                    <td><input type="password" name="confirmPassword"></td>
                </tr>
                <tr>
                    <td><input type="submit" name="submit" value="Change Password"></td>
                </tr>
                
            </table>
            <h2 style = "color: #ED4D2E ">${mess}</h2>
    </body>
</html>
