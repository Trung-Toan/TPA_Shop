/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import DAL.Process.Profile;
import Models.User;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


/**
 *
 * @author admin
 */
@WebServlet(name = "Profile", urlPatterns = {"/profile"})
public class ProfileServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

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
        String msg = request.getParameter("mess");
        String[] listService = {"Account info", "My order", "Change password"};
        request.setAttribute("listService", listService);
        User user = (User) request.getSession().getAttribute("account");
        String service = request.getParameter("Service");
        if (user != null) {
            if (service == null) {
                service = listService[0];
            }
        }
        request.setAttribute("current", service);
        request.setAttribute("msg", msg);
        request.getRequestDispatcher("Views/Profile.jsp").forward(request, response);
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
        String mess = "";
        String Service = request.getParameter("Service");
        String fullName = request.getParameter("fullName");
        String email = request.getParameter("email");
        String phoneNumberStr = request.getParameter("phoneNumber");
        String address = request.getParameter("address");
        String genderStr = request.getParameter("gender");
        String dob = request.getParameter("dob");
        // Retrieve accountImage parameter
        String image = request.getParameter("image");
        // Update not have value in input
        if (image == null || image.equals("")) {
            image = request.getParameter("beforeImage");
        }

        User user = (User) request.getSession().getAttribute("account");
        if (user == null) {
            mess = "Pless login!";
            request.getSession().setAttribute("message", mess);
        } else {
            if (phoneNumberStr != null && !phoneNumberStr.isEmpty() && genderStr != null && !genderStr.isEmpty()) {
                try {
                    int phoneNumber = Integer.parseInt(phoneNumberStr);

                    boolean gender = Boolean.parseBoolean(genderStr);

                    // Update profile using the Profile class
                    boolean isUpdated = Profile.INSTANCE.updateProfile(user.getId(), fullName, email, phoneNumber, address, gender, dob, image);

                    // Check the result and respond accordingly
                    if (isUpdated) {
                        mess = "Update information Success";
                        request.getSession().setAttribute("status", "success");
                        request.getSession().setAttribute("message", mess);
                    } else {
                        mess = "Update information Fail";
                        request.getSession().setAttribute("status", "fail");
                        request.getSession().setAttribute("message", mess);
                    }
                } catch (NumberFormatException e) {
                    mess = "Invalid input format";
                    request.getSession().setAttribute("status", "fail");
                    request.getSession().setAttribute("message", mess);
                }
            } else {
                mess = "Invalid input";
                request.getSession().setAttribute("status", "fail");
                request.getSession().setAttribute("message", mess);
            }
        }

        // Redirect back to doGet method
        response.sendRedirect(request.getContextPath() + "/profile?Service=" + Service + "&mess=" + mess);
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
