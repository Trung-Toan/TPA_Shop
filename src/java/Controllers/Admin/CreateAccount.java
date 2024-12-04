/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers.Admin;

import Models.User;
import DAL.Admin.Manager;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.regex.Pattern;

/**
 *
 * @author xuxum
 */
@WebServlet(name = "CreateAccount", urlPatterns = {"/createAccount"})
public class CreateAccount extends HttpServlet {

    // Regex for email validation
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    // Regex for phone number validation (simple example, you can adjust it to match your country's phone number format)
    private static final String PHONE_REGEX = "^\\d{10}$";

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("Views/Admin/AdminCreateAcc.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Manager acc = new Manager();

        int roleID = 2;
        String fullname = request.getParameter("fullname");
        String email = request.getParameter("email");
        String phone = request.getParameter("phoneNumber");
        String password = request.getParameter("password");
        String status = request.getParameter("status");
        String confirmPass = request.getParameter("confirmPassword");
        int accountStatus = "active".equals(status) ? 1 : 0;
        String message = "";

        if (!password.equals(confirmPass)) {
            message = "Passwords do not match";

        } else if (!Pattern.matches(EMAIL_REGEX, email)) {
            message = "Invalid email format";
        } else if (!Pattern.matches(PHONE_REGEX, phone) || phone.equals("0000000000") || phone.contains(" ")) {
            message = "Invalid phone number";
        } else if (fullname.contains(" ")) {
            message = "Full name should not contain spaces";
        } else {
            Date dateStart = new Date(System.currentTimeMillis());
            User user = new User(fullname, email, phone, password);
            user.setRoleID(roleID);
            user.setStatus(accountStatus);
            user.setDateStart(dateStart);
            acc.insertAccount(user);
            message = "Successfull";
        }
        request.setAttribute("message", message);
        request.getRequestDispatcher("Views/Admin/AdminCreateAcc.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
