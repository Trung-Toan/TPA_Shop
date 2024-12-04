/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import DAL.Process.DAOForgot;
import Models.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author xuxum
 */
public class ResetPassword extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("Views/Login/resetpassword.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        String code = request.getParameter("code");
        String newPassword = request.getParameter("newPassword");
        String confirmPassword = request.getParameter("confirmPassword");
        String message = "";

        // Validate that the code is not empty and does not contain spaces
        if (code == null || code.trim().isEmpty() || code.contains(" ")) {
            message = "Verification code cannot be empty or contain spaces.";
            request.setAttribute("message", message);
            request.getRequestDispatcher("Views/Login/resetpassword.jsp").forward(request, response);
            return;
        }

        // Validate that the newPassword is not empty
        if (newPassword == null || newPassword.trim().isEmpty()) {
            message = "New password cannot be empty.";
            request.setAttribute("message", message);
            request.getRequestDispatcher("Views/Login/resetpassword.jsp").forward(request, response);
            return;
        }

        // Validate that the confirmPassword is not empty
        if (confirmPassword == null || confirmPassword.trim().isEmpty()) {
            message = "Confirm password cannot be empty.";
            request.setAttribute("message", message);
            request.getRequestDispatcher("Views/Login/resetpassword.jsp").forward(request, response);
            return;
        }

        // Validate newPassword against the password pattern
        String passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,15}$";
        if (!newPassword.matches(passwordPattern)) {
            message = "Password must be 8-15 characters long, include a number, a lowercase letter, an uppercase letter, and a special character.";
            request.setAttribute("message", message);
            request.getRequestDispatcher("Views/Login/resetpassword.jsp").forward(request, response);
            return;
        }

        // Check if newPassword and confirmPassword match
        if (!newPassword.equals(confirmPassword)) {
            message = "Passwords do not match!";
            request.setAttribute("message", message);
            request.getRequestDispatcher("Views/Login/resetpassword.jsp").forward(request, response);
            return;
        }

        int resetCode;
        try {
            resetCode = (int) session.getAttribute("resetCode");
        } catch (Exception e) {
            message = "Invalid session. Please request a new password reset.";
            request.setAttribute("message", message);
            request.getRequestDispatcher("Views/Login/resetpassword.jsp").forward(request, response);
            return;
        }

        String userEmail = (String) session.getAttribute("userEmail");
        if (userEmail == null || userEmail.trim().isEmpty()) {
            message = "Invalid session. Please request a new password reset.";
            request.setAttribute("message", message);
            request.getRequestDispatcher("Views/Login/resetpassword.jsp").forward(request, response);
            return;
        }

        // Check if the entered code matches the reset code
        if (Integer.parseInt(code) == resetCode) {
            //check pass is the same as the entered pass
            if (newPassword.equals(confirmPassword)) {
                DAOForgot dao = new DAOForgot();
                User user = dao.checkUsersForChangePass(userEmail);
                //check user
                if (user != null) {
                    dao.changePassword(user.getId(), newPassword);
                    message = "Password changed successfully";
                    response.sendRedirect(request.getContextPath() + "/Login");
                } else {
                    message = "Account not exist";
                    request.getRequestDispatcher("Views/Login/resetpassword.jsp").forward(request, response);
                }
            } else {
                message = "Account does not exist.";
                request.setAttribute("message", message);
                request.getRequestDispatcher("Views/Login/resetpassword.jsp").forward(request, response);
            }
        } else {
            message = "Invalid verification code!";
            request.setAttribute("message", message);
            request.getRequestDispatcher("Views/Login/resetpassword.jsp").forward(request, response);
        }
        request.setAttribute("message", message);

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
