/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers.Admin;

import DAL.Admin.CompanyInformation;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import DAL.Admin.ManageAccount;
import Models.Products;
import Models.*;
import java.util.List;

/**
 *
 * @author xuxum
 */
@WebServlet(name = "View", urlPatterns = {"/View"})
public class View extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ManageAccount manager = new ManageAccount();
        CompanyInformation companies = new CompanyInformation();
        //List
        List<User> listAccView = manager.getAllAccount();
        List<Products> listProduct = manager.getAllProduct();
        List<Company> listCompanyInfor = companies.getAll();

        request.setAttribute("listCompanyInfor", listCompanyInfor);
        request.setAttribute("listProduct", listProduct);
        request.setAttribute("listAccView", listAccView);
        request.getRequestDispatcher("Views/Admin/AdminPage.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
