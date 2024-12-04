/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import DAL.Process.DAOAccount;
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
 * @author AD
 */
public class CreateAccountServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("Views/Register/SignUp.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DAOAccount d = new DAOAccount();
        String user_name = request.getParameter("user_name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");

        boolean emailError = false;
        boolean passError = false;

        if (user_name.isEmpty() || email.isEmpty() || phone.isEmpty()
                || password.isEmpty() || confirmPassword.isEmpty()) {
            String mess = "Please fill in all fields";
            setCommonValues(request, response, mess, user_name, email, phone, emailError, passError);
            return;
        }

        //Check format Email
        if (!isValidEmail(email)) {
            String mess = "Invalid email format.";
            request.setAttribute("emailError", true);
            setCommonValues(request, response, mess, user_name, "", phone, emailError, passError);
            return;
        }

        //check phone
        if (!isValidPhone(phone)) {
            String mess = "The phone number is not in the correct format";
            setCommonValues(request, response, mess, user_name, email, "", emailError, passError);
        }

        //check password
        if (!isValidPassword(password)) {
            String mess = "Password must be at least 8 character and combination of letters, numbers, and special characters.";
            setCommonValues(request, response, mess, user_name, email, phone, emailError, passError);
            return;
        }

        //Check confirm Password
        if (!password.equals(confirmPassword)) {
            String mess = "Password and confirm password do not match.";
            request.setAttribute("passError", true);
            setCommonValues(request, response, mess, user_name, email, phone, emailError, passError);
            return;
        }

        //Check Account by Email
        User haveExistEmail = d.checkUsersForChangePass(email);
        if (haveExistEmail != null) {
            String mess = "Email already exists.";
            setCommonValues(request, response, mess, "", "", "", emailError, passError);
            request.getRequestDispatcher("Views/Register/SignUp.jsp").forward(request, response);
            return;
        }

        //Check Account by UserName
        User haveExistUserName = d.getAccountByUserName(user_name);
        if (haveExistUserName != null) {
            String mess = "User Name already exists.";
            setCommonValues(request, response, mess, "", "", "", emailError, passError);
            request.getRequestDispatcher("Views/Register/SignUp.jsp").forward(request, response);
            return;
        }

        User acc = new User(user_name, email, phone, password);
        boolean haveAdd = d.addAccount(acc);
        if (haveAdd == true) {
            request.getRequestDispatcher("Views/Login/Login.jsp").forward(request, response);
        } else {
            String mess = "Have error";
            request.setAttribute("message", mess);
            request.getRequestDispatcher("Views/Register/SignUp.jsp").forward(request, response);
        }

    }

    private boolean isValidEmail(String email) {
        // Kiểm tra định dạng email
        // Có thể sử dụng biểu thức chính quy hoặc thư viện kiểm tra định dạng email
        return email.matches("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}");
    }
    // validate password
    private boolean isValidPassword(String password) {
        // Kiểm tra độ dài mật khẩu và mức độ mạnh
        return password.length() >= 8 && password.matches(".*[A-Z].*") && password.matches(".*[a-z].*") && password.matches(".*\\d.*") && password.matches(".*[!@#$%^&*()].*");
    }

    private boolean isValidPhone(String phone) {
        // Kiểm tra định dạng số điện thoại
        return phone.matches("^(\\+84|0)(3[2-9]|5[6|8|9]|7[0|6-9]|8[1-9]|9[0-9])[0-9]{7}$");
    }

    private void setCommonValues(HttpServletRequest request, HttpServletResponse response, String mess,
            String user_name, String email, String phone,
            boolean emailError, boolean passError) throws ServletException, IOException {
        request.setAttribute("message", mess);
        request.setAttribute("user_name", user_name);
        request.setAttribute("email", email);
        request.setAttribute("phone", phone);
        request.setAttribute("emailError", emailError);
        request.setAttribute("passError", passError);
        request.getRequestDispatcher("Views/Register/SignUp.jsp").forward(request, response);

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
