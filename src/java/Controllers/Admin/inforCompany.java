/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers.Admin;

import DAL.Admin.CompanyInformation;
import Models.*;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *
 * @author xuxum
 */
@WebServlet(name = "inforCompany", urlPatterns = {"/inforCompany"})
public class inforCompany extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    @Override
    protected void
            doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        CompanyInformation companies = new CompanyInformation();

        List<Company> listCompanyInfor = companies.getAll();

        request.setAttribute("listCompanyInfor", listCompanyInfor);
        request.getRequestDispatcher("Views/Admin/company.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        CompanyInformation editInfo = new CompanyInformation();
        String originalCompanyName = request.getParameter("originalCompanyName");
        String companyName = request.getParameter("companyName");
        String companyAddress = request.getParameter("companyAddress");
        String companyDateStart = request.getParameter("companyDateStart");
        String companyTax = request.getParameter("companyTax");
        String companyLastUpdated = request.getParameter("companyLastUpdated");
        editInfo.updateCompany(originalCompanyName, companyName, companyAddress, companyDateStart, companyTax, companyLastUpdated);
        response.sendRedirect("inforCompany");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
