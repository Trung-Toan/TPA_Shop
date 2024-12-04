/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import DAL.Process.CategoryProcess;
import DAL.Process.SubCategoryProcess;
import Models.Categorys;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
@WebServlet(name = "Category", urlPatterns = {"/category"})
public class Category extends HttpServlet {

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
        ArrayList<Categorys> listCategory = CategoryProcess.INSTANCE.read();

        String messenger = request.getParameter("messenger");
        String style = request.getParameter("style");
        if (messenger == null) {
            messenger = "";
        }
        if (style == null) {
            style = "";
        }
        request.setAttribute("style", style);
        request.setAttribute("messenger", messenger);
        request.setAttribute("listCategory", listCategory);
        request.setAttribute("subCategory", SubCategoryProcess.INSTANCE);
        request.setAttribute("active", "category");
        request.getRequestDispatcher("/Views/Products/Category.jsp").forward(request, response);
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
        // id sub category
        // id category
        
        // add new subCategory
        String subAdd = request.getParameter("subAdd");
        // add new category
        String cateAdd = request.getParameter("cateAdd");
        // btn add more subCategory
        String btnAddSub = request.getParameter("btnAddSub");
        // name subcategory add more
        String nameAddSub = request.getParameter("nameAddSub" + btnAddSub);
        // btn add new Category and subCategory
        boolean btnAdd = request.getParameter("btnAdd") != null;
        // btn delete sub category
        String btnDeleteSub = request.getParameter("btnDeleteSub") ;
        // btn delete category
        String btnDeleteC = request.getParameter("btnDeleteC") ;
        // btn update full 
        String btnUpdate = request.getParameter("btnUpdate") ;
        
        // list name of supCategory update
        String[] nameSub = request.getParameterValues("nameSub" + btnUpdate);
        // list name of category update
        String[] nameC = request.getParameterValues("nameC" + btnUpdate);
        // list id sub category 
        String[] idSubUpdate = request.getParameterValues("idSubUpdate" + btnUpdate);
        /* Add new category */
        if (btnAdd) {
            if (subAdd == null && subAdd.isBlank() && cateAdd == null && cateAdd.isBlank()) {
                response.sendRedirect(request.getContextPath() + "/category?messenger=Error add new category.&style=error");
            } else {
                String id = CategoryProcess.INSTANCE.add(cateAdd);
                if (id != null) {
                    int add = SubCategoryProcess.INSTANCE.add(subAdd, id);
                    if (add >= 0) {
                        response.sendRedirect(request.getContextPath() + "/category?messenger=Add new category is success.&style=success");
                    } else {
                        response.sendRedirect(request.getContextPath() + "/category?messenger=Error add new sub category.&style=error");
                    }
                } else {
                    response.sendRedirect(request.getContextPath() + "/category?messenger=Error add new sub category.&style=error");
                }

            }
        }
        /* Add more sub category */
        if (btnAddSub != null && !btnAddSub.isBlank()) {
            if (nameAddSub == null || nameAddSub.isBlank()) {
                response.sendRedirect(request.getContextPath() + "/category?messenger=Error add more sub category.&style=error");
            } else {
                int add = SubCategoryProcess.INSTANCE.add(nameAddSub, btnAddSub);
                if (add >= 0) {
                    response.sendRedirect(request.getContextPath() + "/category?messenger=Add more sub category is success.&style=success");
                } else {
                    response.sendRedirect(request.getContextPath() + "/category?messenger=Error add more sub category.&style=error");
                }
            }
        }
        /* delete category */
        if (btnDeleteC != null && !btnDeleteC.isBlank()) {
            boolean remove = CategoryProcess.INSTANCE.remove(btnDeleteC);
            if (remove) {
                response.sendRedirect(request.getContextPath() + "/category?messenger=Delete category is success.&style=success");
            } else {
                response.sendRedirect(request.getContextPath() + "/category?messenger=Error delete category.&style=error");
            }
        }
        /* delete sub category */
        if (btnDeleteSub != null && !btnDeleteSub.isBlank()) {
            boolean remove = SubCategoryProcess.INSTANCE.remove(btnDeleteSub);
            if (remove == true) {
                response.sendRedirect(request.getContextPath() + "/category?messenger=Delete sub category is success.&style=success");
            } else {
                response.sendRedirect(request.getContextPath() + "/category?messenger=Error delete sub category.&style=error");
            }
        }
        /* update category */
        if (btnUpdate != null && !btnUpdate.isBlank()) {
            boolean update = true;
            if (nameSub == null || nameC == null) {
                response.sendRedirect(request.getContextPath() + "/category?messenger=Error update category beacuse null.&style=error");
            } else {
                for (String c : nameC) {
                    if (c == null || c.isBlank()) {
                        update = false;
                        break;
                    }
                }
                if (update == false) {
                    response.sendRedirect(request.getContextPath() + "/category?messenger=Error update category beacuse category empty.&style=error");
                } else {
                    for (String sub : nameSub) {
                        if (sub == null || sub.isBlank()) {
                            update = false;
                            break;
                        }
                    }
                    if (update == false) {
                        response.sendRedirect(request.getContextPath() + "/category?messenger=Error update category beacuse sub category empty.&style=error");
                    }
                }
                if (update) {
                    boolean check;
                    check = CategoryProcess.INSTANCE.update(nameC, btnUpdate);
                    if (check) {
                        check = SubCategoryProcess.INSTANCE.update(nameSub, idSubUpdate);
                    }
                    if (check) {
                        response.sendRedirect(request.getContextPath() + "/category?messenger=Update category is success.&style=success");
                    } else {
                        response.sendRedirect(request.getContextPath() + "/category?messenger=Error update category.&style=error");
                    }
                }
            }
        }
    }
}
