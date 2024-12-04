/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import DAL.Process.DAOUser;
import Models.GoogleAccount;
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
 * @author ADMIN
 */
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //dang nhap bang gg
        String code = request.getParameter("code");
        if (code != null) {
            GoogleLogin gg = new GoogleLogin();
            String accessToken = gg.getToken(code);
            GoogleAccount ac = gg.getUserInfo(accessToken);
            String email = ac.getEmail();

            request.setAttribute("email", email);
            request.getRequestDispatcher("Views/Home/Home.jsp").forward(request, response);
            return;
        }

        request.getRequestDispatcher("Views/Login/Login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DAOUser da = new DAOUser();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        if (request.getParameter("btnLogin") != null) {
            User account = da.validateCustomer(username, password);
            if (account != null) {
                if (account.getStatus() == 0) {
                    // User account is inactive
                    request.setAttribute("mess", "Your account has been banned");
                    request.getRequestDispatcher("Views/Login/Login.jsp").forward(request, response);
                } else {
                    HttpSession session = request.getSession();
                    session.setAttribute("account", account);
                    if (account.getRoleID() == 1) {
                        response.sendRedirect(request.getContextPath() + "/View");
                    } else if (account.getRoleID() == 2) {
                        response.sendRedirect(request.getContextPath() + "/homemanager");
                    } else if (account.getRoleID() == 3) {
                        response.sendRedirect(request.getContextPath() + "/homestaff");
                    } else if (account.getRoleID() == 0) {
                        response.sendRedirect(request.getContextPath() + "/home");
                    }
                }
            } else {
                request.setAttribute("mess", "Login not successful");
                request.getRequestDispatcher("Views/Login/Login.jsp").forward(request, response);
            }
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
