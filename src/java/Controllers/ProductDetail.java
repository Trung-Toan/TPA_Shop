/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import DAL.Process.DAOProduct;
import DAL.Process.ImageProcess;
import DAL.Process.ProductDetailProcess;
import Models.Images;
import Models.ProductDetails;
import Models.Products;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "ProductDetail", urlPatterns = {"/ProductDetail"})
public class ProductDetail extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //get id product selected 
        String productId = (String) request.getAttribute("productIdSelect");
        if (productId == null) {
            productId = request.getParameter("productIdSelect");
        }
        if (productId == null) {
            response.sendRedirect(request.getContextPath() + "/home");
        } else {
            //load image
            ArrayList<Images> listImg = (ArrayList<Images>) request.getAttribute("listImage");
            if (listImg == null) {
                listImg = ImageProcess.INSTANCE.read();
            }
            if (listImg != null) {
                ArrayList<Images> getImage;
                getImage = ImageProcess.INSTANCE.getImage(listImg, Integer.parseInt(productId));
                request.setAttribute("listImage", getImage);
            }
            //load product detail
            ArrayList<ProductDetails> listPD = ProductDetailProcess.INSTANCE.getProductDetail(Integer.parseInt(productId));
            if (listPD == null) {
                response.sendRedirect(request.getContextPath() + "/home");
            } else {
                request.getSession().setAttribute("productIdSelect", productId);
                DAOProduct daoProduct = new DAOProduct();
                Products product = daoProduct.getProduct(Integer.parseInt(productId));
                ArrayList<Images> listImage = (ArrayList<Images>) request.getAttribute("listImage");
                if (listImage == null) {
                    listImage = ImageProcess.INSTANCE.read();
                    request.setAttribute("listImage", listImage);
                }
                request.setAttribute("listPD", listPD);
                request.setAttribute("product", product);
                request.getRequestDispatcher("./Views/Home/ProductDetail.jsp").forward(request, response);
            }
        }
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
