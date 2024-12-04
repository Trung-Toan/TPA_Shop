/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import DAL.Process.ImageProcess;
import DAL.Process.ProductProcess;
import Models.Images;
import Models.Products;
import Models.User;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;

/**
 *
 * @author trantoan
 */
public class Home extends HttpServlet {

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
        ArrayList<Products> listProduct = (ArrayList<Products>) request.getSession().getAttribute("listProduct");
        if (listProduct == null) {
            listProduct = ProductProcess.INSTANCE.read();
        }

        //load all Image
        ArrayList<Images> listImage = (ArrayList<Images>) request.getSession().getAttribute("listImage");
        if (listImage == null) {
            listImage = ImageProcess.INSTANCE.read();
            request.getSession().setAttribute("listImage", listImage);
            request.setAttribute("image", ImageProcess.INSTANCE);
        }

        request.setAttribute("listImage", listImage);
        request.setAttribute("image", ImageProcess.INSTANCE);

        request.setAttribute("listProduct", listProduct);
        request.getRequestDispatcher("Views/Home/Home.jsp").forward(request, response);
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
    }
}
