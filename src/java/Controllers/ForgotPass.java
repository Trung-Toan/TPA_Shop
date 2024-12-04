/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import DAL.Process.DAOForgot;
import Models.User;
import Validations.RandomCode;
import Validations.SendEmail;
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
public class ForgotPass extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("Views/Login/forgotpass.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        String email = request.getParameter("email");
        String mess = "";

        DAOForgot dao = new DAOForgot();
        User user = dao.checkUsersForChangePass(email);
        if (user != null) {
            //Generate a new random code
            int code = RandomCode.randomCode(6);
            String newCode = String.valueOf(code);

            //Send Email
            String subject = "Code Reset Resquest";
            String content = "<h1>Code to change pasword</h1>"
                    + "<p>Your Code is: <strong>" + newCode + "</strong></p>";
            SendEmail.sendMail(email, subject, content);
            
            session.setAttribute("resetCode", code);
            session.setAttribute("userEmail", email);
            response.sendRedirect("Views/Login/resetpassword.jsp");
        } else {
            mess = "Account not exist!!";
            request.setAttribute("message", mess);
            request.getRequestDispatcher("Views/Login/forgotpass.jsp").forward(request, response);
        }
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
