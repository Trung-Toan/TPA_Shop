/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import DAL.Process.CategoryProcess;
import DAL.Process.ProductProcess;
import DAL.Process.SubCategoryProcess;
import Models.Categorys;
import Models.Products;
import Models.SubCategorys;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import Validations.GetDate;
import java.text.ParseException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * insert product, image of product, product detail
 */
public class InsertProduct extends HttpServlet {

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
        /* get messes and style */
        String messenger = (String) request.getSession().getAttribute("msg");
        String style = (String) request.getSession().getAttribute("style");
        String remove = request.getParameter("remove");
        if (messenger == null) {
            messenger = "";
        }
        if (style == null) {
            style = "";
        }
        request.setAttribute("style", style);
        request.setAttribute("messenger", messenger);
        request.getSession().removeAttribute("style");
        request.getSession().removeAttribute("msg");
        /* end */
        // load all category
        ArrayList<Categorys> listCategory = (ArrayList<Categorys>) request.getSession().getAttribute("listCategory");
        if (listCategory == null) {
            listCategory = CategoryProcess.INSTANCE.read();
            request.getSession().setAttribute("listCategory", listCategory);
        }
        // load all sub category
        ArrayList<SubCategorys> listSubCategory = (ArrayList<SubCategorys>) request.getSession().getAttribute("listSubCategory");
        if (listSubCategory == null) {
            listSubCategory = SubCategoryProcess.INSTANCE.read();
            request.getSession().setAttribute("listSubCategory", listSubCategory);
        }
        // get id category after select at Views
        Object idCategory = request.getSession().getAttribute("idCategory");
        Object idSubCategory = request.getSession().getAttribute("idSubCategory");

        // if don't selelect get value at index 0
        if (idCategory == null) {
            idCategory = listCategory.get(0).getId();
            request.getSession().setAttribute("idCategory", idCategory);
        }

        if (idSubCategory == null) {
            idSubCategory = SubCategoryProcess.INSTANCE.findSubCategory((Integer) idCategory).get(0).getId();
            request.getSession().setAttribute("idSubCategory", idSubCategory);
        }

        // push into views
        request.setAttribute("idCategory", idCategory);
        request.setAttribute("idSubCategory", idSubCategory);
        request.setAttribute("category", listCategory);
        request.setAttribute("subCategory", SubCategoryProcess.INSTANCE);
        request.getRequestDispatcher("Views/Products/InsertProduct.jsp").forward(request, response);
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
        // get data from Views
        String nameProduct = request.getParameter("productName");
        int price = Integer.parseInt(request.getParameter("price"));
        int discount = Integer.parseInt(request.getParameter("discount"));
        int gender = Integer.parseInt(request.getParameter("gender"));
        String describe = request.getParameter("describe");
        String idSub = request.getParameter("idSub");
        // check null
        if (nameProduct.trim().isBlank() || discount > 100 || discount < 0 || price < 1000 || describe.trim().isBlank()) {
            request.getSession().setAttribute("msg", "Không được để trường nào trống,<br>giá phải > 1000, và 0 <= giảm giá <= 100");
            request.getSession().setAttribute("style", "error");
            doGet(request, response);
        } else {
            if (request.getParameter("submit") != null) {
                // add product 
                Products product = new Products(nameProduct.replaceAll("\\s+", " "), describe, price, discount, gender, Integer.parseInt(idSub));
                int idProduct = ProductProcess.INSTANCE.insert(product);
                ArrayList<Products> listProduct = (ArrayList<Products>) request.getSession().getAttribute("listProduct");
                if (listProduct == null) {
                } else {
                    try {
                        Date createdAt = GetDate.getDate();
                        Date updatedAt = GetDate.getDate();
                        listProduct.add(new Products(idProduct, nameProduct, describe, price, discount, gender, Integer.parseInt(idSub), createdAt, updatedAt));
                        request.getSession().setAttribute("listProduct", listProduct);
                    } catch (ParseException ex) {
                        Logger.getLogger(InsertProduct.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                // set value need into session
                request.getSession().setAttribute("idPro", idProduct);
                request.getSession().setAttribute("pro", product);
                request.getSession().setAttribute("msg", "");
                response.sendRedirect(request.getContextPath() + "/addproductdetail?messenger=Continute insert&style=success");
            }
        }

    }
}
