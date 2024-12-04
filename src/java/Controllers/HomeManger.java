/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import DAL.Process.CategoryProcess;
import DAL.Process.ImageProcess;
import DAL.Process.ProductProcess;
import DAL.Process.SubCategoryProcess;
import Models.Categorys;
import Models.Images;
import Models.Products;
import Models.SubCategorys;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Admin
 */
public class HomeManger extends HttpServlet {

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
        String loadIMG = (String) request.getSession().getAttribute("loadIMG") == null
                ? "n"
                : (String) request.getSession().getAttribute("loadIMG");
        String loadPD = (String) request.getSession().getAttribute("loadPD") == null
                ? "n"
                : (String) request.getSession().getAttribute("loadPD");
        request.getSession().setAttribute("loadimg", loadIMG);
        request.getSession().setAttribute("loadpd", loadPD);
        /* get messes and style */
        String messenger = request.getParameter("messenger");
        String style = request.getParameter("style");
        String mm = (String) request.getSession().getAttribute("mm");
        String ss = (String) request.getSession().getAttribute("ss");
        if (mm != null && ss != null) {
            messenger = mm;
            style = ss;
            request.getSession().removeAttribute("mm");
            request.getSession().removeAttribute("ss");
        }
        if (messenger == null) {
            messenger = "";
        }
        if (style == null) {
            style = "";
        }
        /* end */
        String sortAsc = (String) request.getSession().getAttribute("sortAsc");
        String sortDes = (String) request.getSession().getAttribute("sortDes");
        String searchBy = (String) request.getSession().getAttribute("searchBy");
        if (sortAsc == null) {
            sortAsc = "none";
        }
        if (sortDes == null) {
            sortDes = "none";
        }
        if (searchBy == null) {
            searchBy = "";
        }
        String loadProduct = (String) request.getSession().getAttribute("loadProduct");
        if (loadProduct == null) {
            loadProduct = "0";
        }
        ArrayList<Products> listProduct = (ArrayList<Products>) request.getSession().getAttribute("filterProduct");
        if (listProduct == null || loadProduct.equals("2")) {
            listProduct = (ArrayList<Products>) request.getSession().getAttribute("listProduct");
        }
        if (listProduct == null || loadProduct.equals("1")) {
            listProduct = ProductProcess.INSTANCE.read();
            request.getSession().setAttribute("listProduct", listProduct);
        }
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
        //load all Image
        ArrayList<Images> listImage = (ArrayList<Images>) request.getSession().getAttribute("listImage");
        if (listImage == null || loadIMG.equals("1")) {
            listImage = ImageProcess.INSTANCE.read();
            request.getSession().setAttribute("listImage", listImage);
        }
        String fCategory = (String) request.getSession().getAttribute("fCategory");
        String fSubCategory = (String) request.getSession().getAttribute("fSubCategory");
        if (fCategory == null) {
            fCategory = "all";
        }
        if (fSubCategory == null) {
            fSubCategory = "all";
        }

        if (!fCategory.equals("all")) {
            listSubCategory = SubCategoryProcess.INSTANCE.getSubCategory(fCategory);
        }
        
        request.setAttribute("fCategory", fCategory);
        request.setAttribute("fSubCategory", fSubCategory);
        request.getSession().getAttribute("sortAsc");
        request.getSession().getAttribute("sortDes");
        request.getSession().getAttribute("searchBy");
        request.getSession().removeAttribute("messenger");
        request.getSession().removeAttribute("style");
        request.getSession().removeAttribute("loadProduct");
        request.setAttribute("active", "product");
        request.setAttribute("style", style);
        request.setAttribute("messenger", messenger);
        request.setAttribute("sortAsc", sortAsc);
        request.setAttribute("sortDes", sortDes);
        request.setAttribute("valueSearch", searchBy);
        request.setAttribute("image", ImageProcess.INSTANCE);
        request.setAttribute("listImage", listImage);
        request.setAttribute("date", new SimpleDateFormat("yyyy/MM/dd"));
        request.setAttribute("listCategory", listCategory);
        request.setAttribute("listSubCategory", listSubCategory);
        request.setAttribute("subCategory", SubCategoryProcess.INSTANCE);
        request.setAttribute("product", listProduct);
        request.getRequestDispatcher("/Views/Home/Manager.jsp").forward(request, response);
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
        ArrayList<Products> listProduct = (ArrayList<Products>) request.getSession().getAttribute("listProduct");
        if (listProduct == null) {
            request.getSession().setAttribute("mm", "Error cannot load product!");
            request.getSession().setAttribute("ss", "Error");
        } else {
            String fCategory = request.getParameter("fCategery");
            String fSubCategory = request.getParameter("fSubCategery");

            String sortAsc = request.getParameter("sortAsc");
            String sortDes = request.getParameter("sortDes");
            String searchBy = request.getParameter("searchBy");
            if (sortAsc != null && sortDes != null) {
                String preAsc = (String) request.getSession().getAttribute("preAsc");
                String preDes = (String) request.getSession().getAttribute("preDes");
                if (preAsc == null) {
                    preAsc = sortAsc;
                }
                if (preDes == null) {
                    preDes = sortDes;
                }
                if (!sortAsc.equals("none")) {
                    if (preAsc.equals("none")) {
                        sortDes = "none";
                    }
                } else {
                    if (sortDes.equals("none")) {
                        sortAsc = "none";
                        sortDes = "none";
                        preDes = sortDes;
                    }
                }
                if (!sortDes.equals("none")) {
                    if (preDes.equals("none")) {
                        sortAsc = "none";
                    }
                } else {
                    if (sortAsc.equals("none")) {
                        sortAsc = "none";
                        sortDes = "none";
                    }
                }
                preAsc = sortAsc;
                preDes = sortDes;
                request.getSession().setAttribute("preAsc", preAsc);
                request.getSession().setAttribute("preDes", preDes);
            }

            if (request.getParameter("clear") != null) {
                fCategory = "all";
                fSubCategory = "all";
                sortAsc = "none";
                sortDes = "none";
                searchBy = "";
            }

            String qrSelect = "";
            if (fCategory.equals("all")) {
                if (fSubCategory.equals("all")) {
                    qrSelect = "";
                } else {
                    qrSelect = "[subCategoryID] = " + fSubCategory + " AND ";
                }
            } else {
                if (fSubCategory.equals("all")) {
                    qrSelect = "[subCategoryID] IN (SELECT [id] FROM [SubCategorys] WHERE [categoryID] = " + fCategory + ") AND ";
                } else {
                    qrSelect = "[subCategoryID] = " + fSubCategory + " AND ";
                }
            }

            request.getSession().setAttribute("fCategory", fCategory);
            request.getSession().setAttribute("fSubCategory", fSubCategory);
            request.getSession().setAttribute("sortAsc", sortAsc);
            request.getSession().setAttribute("sortDes", sortDes);
            request.getSession().setAttribute("searchBy", searchBy);
            if (searchBy == null || searchBy.isBlank()) {
                if ("none".equals(sortAsc) && "none".equals(sortDes)) {
                    listProduct = ProductProcess.INSTANCE.sortDefault(searchBy, qrSelect);
                }
                if (!"none".equals(sortAsc)) {
                    listProduct = ProductProcess.INSTANCE.sortAsc(sortAsc, searchBy, qrSelect);
                } else if (!"none".equals(sortDes)) {
                    listProduct = ProductProcess.INSTANCE.sortDes(sortDes, searchBy, qrSelect);
                }
                request.getSession().setAttribute("filterProduct", listProduct);
            } else {
                String s = searchBy;
                List<Products> valueSearch = listProduct.stream()
                        .filter(product -> product.getName().toLowerCase().contains(s.toLowerCase()))
                        .collect(Collectors.toList());

                if (valueSearch != null) {
                    if ("none".equals(sortAsc) && "none".equals(sortDes)) {
                        valueSearch = ProductProcess.INSTANCE.sortDefault(searchBy, qrSelect);
                    }
                    if (!"none".equals(sortAsc)) {
                        valueSearch = ProductProcess.INSTANCE.sortAsc(sortAsc, searchBy, qrSelect);
                    } else if (!"none".equals(sortDes)) {
                        valueSearch = ProductProcess.INSTANCE.sortDes(sortDes, searchBy, qrSelect);
                    }
                }
                request.getSession().setAttribute("filterProduct", valueSearch);
            }

        }
        doGet(request, response);
    }
}
