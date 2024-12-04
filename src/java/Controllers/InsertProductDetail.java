/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import DAL.Process.CategoryProcess;
import DAL.Process.ImageProcess;
import DAL.Process.ProductDetailProcess;
import DAL.Process.SubCategoryProcess;
import Models.Categorys;
import Models.Images;
import Models.Products;
import Models.SubCategorys;
import Validations.UploadFile;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * add image and product detail
 *
 * @author Admin
 */
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50 // 50MB
)
public class InsertProductDetail extends HttpServlet {

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
        String messenger = request.getParameter("messenger");
        String style = request.getParameter("style");

        if (request.getSession().getAttribute("m") != null && request.getSession().getAttribute("st") != null) {
            messenger = (String) request.getSession().getAttribute("m");
            style = (String) request.getSession().getAttribute("st");
            request.getSession().removeAttribute("m");
            request.getSession().removeAttribute("st");
        }
        if (messenger == null) {
            messenger = "";
        }
        if (style == null) {
            style = "";
        }
        request.setAttribute("style", style);
        request.setAttribute("messenger", messenger);
        /* end */
        // get value from session
        Products product = (Products) request.getSession().getAttribute("pro");
        ArrayList<Categorys> listCategory = (ArrayList<Categorys>) request.getSession().getAttribute("listCategory");
        ArrayList<SubCategorys> listSubcCategory = (ArrayList<SubCategorys>) request.getSession().getAttribute("listSubCategory");
        Object idCategory = request.getSession().getAttribute("idCategory");
        Object idSubCategory = request.getSession().getAttribute("idSubCategory");

        // check value
        if (product == null) {
            request.getSession().setAttribute("msg", "Error at session of insert proDetail: product");
            request.getSession().setAttribute("style", "error");
            response.sendRedirect(request.getContextPath() + "/addproduct");
        } else if (listCategory == null) {
            request.getSession().setAttribute("msg", "Error at session of insert proDetail: listCategory");
            request.getSession().setAttribute("style", "error");
            response.sendRedirect(request.getContextPath() + "/addproduct");
        } else if (listSubcCategory == null) {
            request.getSession().setAttribute("msg", "Error at session of insert proDetail: listSubcCategory");
            request.getSession().setAttribute("style", "error");
            response.sendRedirect(request.getContextPath() + "/addproduct");
        } else if (idCategory == null) {
            request.getSession().setAttribute("msg", "Error at session of insert proDetail: idCategory");
            request.getSession().setAttribute("style", "error");
            response.sendRedirect(request.getContextPath() + "/addproduct");
        } else if (idSubCategory == null) {
            request.getSession().setAttribute("msg", "Error at session of insert proDetail: idSubCategory");
            request.getSession().setAttribute("style", "error");
            response.sendRedirect(request.getContextPath() + "/addproduct");
        } else {
            // process value
            Categorys category = CategoryProcess.INSTANCE.getCategory(listCategory, Integer.parseInt("" + idCategory));
            SubCategorys subcCategory = SubCategoryProcess.INSTANCE.getSubCategory(listSubcCategory, Integer.parseInt("" + idSubCategory));
            if (category == null || subcCategory == null) {
                request.setAttribute("msg", "Error at session of insert proDetail");
                request.getSession().setAttribute("style", "error");
                response.sendRedirect(request.getContextPath() + "/addproduct");
            } else {
                request.setAttribute("pro", product);
                request.setAttribute("category", category);
                request.setAttribute("subcCategory", subcCategory);
                request.setAttribute("idCategory", idCategory);
                request.setAttribute("idSubCategory", idSubCategory);
                request.getRequestDispatcher("Views/Products/InsertProductDetail.jsp").forward(request, response);
            }
        }
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
        // upload file 
        UploadFile uploadFile = new UploadFile();
        List<String> imgProduct = uploadFile.fileUpload(request, response);
        // get value from views
        String[] size = request.getParameterValues("size");
        String[] color = request.getParameterValues("color");
        String[] quantity = request.getParameterValues("quantity");
        int idPro = (Integer) request.getSession().getAttribute("idPro");
        if (idPro != 0) {
            if (request.getParameter("submit") != null && (imgProduct != null || !imgProduct.isEmpty() || size != null && size.length > 0 || color != null && color.length > 0 || quantity != null && quantity.length > 0)) {
                boolean insert = true;
                try {
                    if (size == null) {
                        insert = false;
                        request.getSession().setAttribute("m", "No have any empty is blank");
                        request.getSession().setAttribute("st", "error");
                    } else {
                        for (int i = 0; i < size.length; i++) {
                            int q = quantity[i].isBlank() ? 0 : Integer.parseInt(quantity[i]);
                            if (size[i].isBlank() || color[i].isBlank() || q < 1) {
                                insert = false;
                                request.getSession().setAttribute("m", "No have any empty is blank");
                                request.getSession().setAttribute("st", "error");
                                break;
                            }
                        }
                    }
                } catch (NumberFormatException e) {
                    insert = false;
                    request.getSession().setAttribute("m", "Quantity must be a number");
                    request.getSession().setAttribute("st", "error");
                    doGet(request, response);
                }
                if (insert) {
                    // add image into database after add image into session
                    if (!imgProduct.isEmpty()) {
                        boolean check = ImageProcess.INSTANCE.insert(imgProduct, idPro);
                        if (check == true) {
                            ArrayList<Images> listImage = (ArrayList<Images>) request.getSession().getAttribute("listImage");
                            for (String img : imgProduct) {
                                listImage.add(new Images(0, img, idPro));
                            }
                            request.getSession().setAttribute("listImage", listImage);
                        }
                    }
                    // add product detail
                    ProductDetailProcess.INSTANCE.insert(idPro, size, color, quantity);
                    request.getSession().removeAttribute("pro");
                    request.getSession().removeAttribute("idCategory");
                    request.getSession().removeAttribute("idSubCategory");
                    response.sendRedirect(request.getContextPath() + "/homemanager?messenger=Insert product is successfully!&style=success");
                } else {
                    doGet(request, response);
                }
            } else {
                request.getSession().setAttribute("m", "Plese choose insert product befor insert product detail!");
                request.getSession().setAttribute("st", "error");
                doGet(request, response);
            }
        } else {
            request.getSession().setAttribute("msg", "Plese choose insert product befor insert product detail!");
            request.getSession().setAttribute("style", "error");
            response.sendRedirect(request.getContextPath() + "/addproduct");
        }

    }
}
