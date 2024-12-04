/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import DAL.Process.CategoryProcess;
import DAL.Process.ImageProcess;
import DAL.Process.ProductDetailProcess;
import DAL.Process.ProductProcess;
import DAL.Process.SubCategoryProcess;
import Models.Categorys;
import Models.Images;
import Models.ProductDetails;
import Models.Products;
import Models.SubCategorys;
import Validations.UploadFile;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50 // 50MB
)
@WebServlet(name = "ManageProduct", urlPatterns = {"/productProcess"})
public class ManageProduct extends HttpServlet {

    /**
     * methods will delete product and update and delete product detail
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        /* get id product after click button detail */
        String idProduct = (String) request.getSession().getAttribute("idProductSelected");
        String idProductPrev = (String) request.getSession().getAttribute("preID");
        if (idProduct == null || idProductPrev == null) {
            idProduct = request.getParameter("id");
            idProductPrev = idProduct;
            request.getSession().setAttribute("preID", idProductPrev);
        }
        String type = (String) request.getSession().getAttribute("type");
        if (type == null) {
            type = request.getParameter("type");
        }
        idProductPrev = idProduct;

        if (request.getParameter("id") != null && !idProduct.equals(request.getParameter("id"))) {
            request.getSession().setAttribute("preID", idProductPrev);
            idProduct = request.getParameter("id");
        }
        if (request.getParameter("type") != null && !type.equals(request.getParameter("type"))) {
            type = request.getParameter("type");
        }/* End get id product after click */
 /* get id product and type after click to process */
        if (idProduct == null || type == null || idProductPrev == null) {
            response.sendRedirect(request.getContextPath() + "/homemanager?messenger=No have product. Plese select an product");
        } else { // load all product
            ArrayList<Products> listProduct = (ArrayList<Products>) request.getSession().getAttribute("listProduct");
            if (listProduct == null) {
                response.sendRedirect(request.getContextPath() + "/homemanager?messenger=No have any value product.");
            } else {
                String loadIMG = (String) request.getSession().getAttribute("loadimg") == null
                        ? "null"
                        : (String) request.getSession().getAttribute("loadimg");
                String loadPD = (String) request.getSession().getAttribute("loadpd") == null
                        ? "null"
                        : (String) request.getSession().getAttribute("loadpd");
                request.setAttribute("msg", "loadIMG: " + loadIMG + " loadPD: " + loadPD);

                /*
                    find product in list and product detail by product
                    
                    - if product in session is null or click view another product or click button cancel
                    will get reset data into start. 
                    - if click button update will update and not reset.
                 */
                ArrayList<ProductDetails> listProductDetail = (ArrayList<ProductDetails>) request.getSession().getAttribute("productDetailProcess");
                if (listProductDetail == null || !idProduct.equals(idProductPrev) || loadPD.equals("1")) {
                    listProductDetail = ProductDetailProcess.INSTANCE.getProductDetail(Integer.parseInt(idProduct));
                    if (listProductDetail == null) {
                        listProductDetail = new ArrayList<>();
                        request.getSession().setAttribute("productDetailProcess", listProductDetail);
                    } else {
                        request.getSession().setAttribute("productDetailProcess", listProductDetail);
                    }
                }

                /* get all list product in sesion 
                
                    if click button cancel will reset data to start
                    if click button update will update data
                 */
                ArrayList<Images> listImage = (ArrayList<Images>) request.getSession().getAttribute("imageProcess");
                if (listImage == null || !idProduct.equals(idProductPrev) || loadIMG.equals("1")) {
                    ArrayList<Images> listImageSession = (ArrayList<Images>) request.getSession().getAttribute("listImage");
                    if (listImageSession == null) {
                        response.sendRedirect(request.getContextPath() + "/homemanager?messenger=No have any image in session.");
                    } else {
                        listImage = ImageProcess.INSTANCE.getImage(listImageSession, Integer.parseInt(idProduct));
                        if (listImage == null) {
                            listImage = new ArrayList<>();
                            request.getSession().setAttribute("imageProcess", listImage);
                        } else {
                            request.getSession().setAttribute("imageProcess", listImage);
                        }
                    }
                }
                // get product by id procduct
                Products product = ProductProcess.INSTANCE.getProduct(listProduct, Integer.parseInt(idProduct));
                if (product == null) {
                } else {
                    request.getSession().setAttribute("productSelected", product);
                    // delete product
                    if (type.equals("1")) {
                        boolean delete = ProductProcess.INSTANCE.delete(Integer.parseInt(idProduct));
                        if (delete == true) {
                            listProduct.remove(product);
                            request.getSession().setAttribute("listProduct", listProduct);
                            response.sendRedirect(request.getContextPath() + "/homemanager?messenger=Delete successfull&style=success");
                        }
                    }
                    // update product
                    if (type.equals("0")) {
                        // load category and subCategory
                        ArrayList<Categorys> listCategory = (ArrayList<Categorys>) request.getSession().getAttribute("listCategory");
                        ArrayList<SubCategorys> listSubCategory = (ArrayList<SubCategorys>) request.getSession().getAttribute("listSubCategory");
                        if (listCategory == null || listSubCategory == null) {
                            response.sendRedirect(request.getContextPath() + "/homemanager?messenger=No have any value Categorys and SubCategorys.&style=error");
                        } else {
                            SubCategorys subCategoy = SubCategoryProcess.INSTANCE.getSubCategory(listSubCategory, product.getSubCategoryID());
                            Categorys categoy = CategoryProcess.INSTANCE.getCategory(listCategory, subCategoy.getCategoryID());
                            if (categoy == null) {
                                response.sendRedirect(request.getContextPath() + "/homemanager?messenger=Cannot find any category or subcategory in list.&style=error");
                            } else {
                                request.getSession().removeAttribute("loadimg");
                                request.getSession().removeAttribute("loadpd");
                                request.getSession().setAttribute("idProductSelected", idProduct);
                                request.getSession().setAttribute("type", type);
                                request.setAttribute("listImage", listImage);
                                request.setAttribute("msgs", idProductPrev);
                                request.setAttribute("msgs1", idProduct);
                                request.setAttribute("categoy", categoy.getName());
                                request.setAttribute("subCategoy", subCategoy.getName());
                                request.setAttribute("product", product);
                                request.setAttribute("listProductDetail", listProductDetail);
                                request.getRequestDispatcher("Views/Products/UpdateProduct.jsp").forward(request, response);
                            }
                        }
                    }
                }
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
        String msg = "";
        // list delete of image and product detail
        ArrayList<String> imgDelete = (ArrayList<String>) request.getSession().getAttribute("imgDelete");
        if (imgDelete == null) {
            imgDelete = new ArrayList<>();
        }
        ArrayList<String> pdDelete = (ArrayList<String>) request.getSession().getAttribute("pdDelete");
        if (pdDelete == null) {
            pdDelete = new ArrayList<>();
        }
        // upload file 
        UploadFile uploadFile = new UploadFile();
        List<String> imgProduct = uploadFile.fileUpload(request, response);
        // update product
        String nameProduct = request.getParameter("nameProduct");
        String price = (request.getParameter("price"));
        String discount = (request.getParameter("discount"));
        String gender = (request.getParameter("gender"));
        String describe = request.getParameter("describe");
        // get value product detail if user want add more
        String[] sizeAdd = request.getParameterValues("sizeAdd");
        String[] colorAdd = request.getParameterValues("colorAdd");
        String[] quantityAdd = request.getParameterValues("quantityAdd");
        // get value user to update
        String[] idPDUpdate = request.getParameterValues("idPDUpdate");
        String[] size = request.getParameterValues("size");
        String[] color = request.getParameterValues("color");
        String[] quantity = request.getParameterValues("quantity");

        request.getSession().setAttribute("loadPD", "1");
        request.getSession().setAttribute("loadIMG", "1");
        /* delete after submit */
        String idPD = request.getParameter("idPD");
        if (idPD == null || idPD.isBlank()) {
        } else {
            // get all productDetail
            ArrayList<ProductDetails> listProductDetail = (ArrayList<ProductDetails>) request.getSession().getAttribute("productDetailProcess");
            pdDelete.add(idPD);
            request.getSession().setAttribute("pdDelete", pdDelete);
            // update again session productDetailProcess
            listProductDetail = ProductDetailProcess.INSTANCE.removeProductDetail(listProductDetail, Integer.parseInt(idPD));
            request.getSession().setAttribute("productDetailProcess", listProductDetail);
        }
        String idImage = request.getParameter("idImg");
        if (idImage == null || idImage.isBlank()) {
        } else {
            // get all image
            ArrayList<Images> listImage = (ArrayList<Images>) request.getSession().getAttribute("imageProcess");
            imgDelete.add(idImage);
            request.getSession().setAttribute("imgDelete", imgDelete);
            // update again session imageProduct
            listImage = ImageProcess.INSTANCE.removeImage(listImage, Integer.parseInt(idImage));
            request.getSession().setAttribute("imageProcess", listImage);
        }

        // delete
        if (request.getParameter("btnUpdate") != null) {
            boolean update = true;
            boolean add = true;
            // check to update product detail
            if (size == null || size.length == 0 || idPDUpdate == null || color == null || quantity == null) {
            } else {
                for (int i = 0; i < size.length; i++) {
                    if (size[i].isBlank() || size[i].isBlank() || Integer.parseInt(quantity[i].isBlank() ? "0" : quantity[i]) < 1) {
                        update = false;
                        msg += "No have any entry is null or blank at update product detail, quantity must be greater than 1.";
                        break;
                    }
                }
            }
            // check add more product details
            if (update) {
                // check if any attribte is black will back to home
                if (sizeAdd == null || sizeAdd.length == 0) {
                    add = false;
                } else {
                    for (int i = 0; i < sizeAdd.length; i++) {
                        if (sizeAdd[i].isBlank() || colorAdd[i].isBlank() || Integer.parseInt(quantityAdd[i].isBlank() ? "0" : quantityAdd[i]) < 1) {
                            update = false;
                            msg += "No have any entry is null or blank at add more product details, quantity must be greater than 1.";
                            break;
                        }
                    }
                }
            }
            // check update product
            if (update) {
                if (nameProduct == null || price == null || discount == null || gender == null || describe == null
                        || nameProduct.isBlank() || price.isBlank() || discount.isBlank() || gender.isBlank() || describe.isBlank()
                        || Integer.parseInt(price) <= 1000 || Integer.parseInt(discount) > 100 || Integer.parseInt(discount) < 0) {
                    msg += "No have any entry is null or blank at update product, discount must between 0 both 100 and price must be greater than 1000.";
                    update = false;
                }
            }
            String pid = (String) request.getSession().getAttribute("idProductSelected");

            if (update || pid == null) {
                /* update product */
                boolean check = ProductProcess.INSTANCE.update(pid, nameProduct, price, discount, gender, describe);
                if (check != true) {
                    msg += "Update product is not successfull!<br>";
                } else {
                    msg += "Update product is successfull!<br>";
                    request.getSession().setAttribute("loadProduct", "1");
                }
                if (imgProduct == null || imgProduct.isEmpty()) {
                } else {
                    ImageProcess.INSTANCE.insert(imgProduct, Integer.parseInt(pid));
                    msg += "Add more images is succesfull! <br>";
                }

                /* delete */
                request.getSession().setAttribute("loadImg", "0");
                request.getSession().setAttribute("loadPD", "0");
                if (!imgDelete.isEmpty()) {
                    // remove img
                    ImageProcess.INSTANCE.delete(imgDelete);
                    msg += "Delete image is succesfull! <br>";
                }
                if (!pdDelete.isEmpty()) {
                    // remove pd
                    ProductDetailProcess.INSTANCE.delete(pdDelete);
                    msg += "Delete prodcut detail is succesfull! <br>";
                }
                /* update */
                if (idPDUpdate == null || idPDUpdate.length == 0) {
                } else {
                    ProductDetailProcess.INSTANCE.update(size, color, quantity, idPDUpdate);
                    msg += "Update product detail is successfull!<br>";
                } 
                
                /* insert */
                if (add) {
                    ProductDetailProcess.INSTANCE.insert(Integer.parseInt(pid), sizeAdd, colorAdd, quantityAdd);
                    msg += "Add more product detail is succesfull!<br>";
                }
                // Befor Update must detete session don't need
                request.getSession().removeAttribute("imgDelete");
                request.getSession().removeAttribute("pdDelete");
                request.getSession().removeAttribute("idProductSelected");
                request.getSession().removeAttribute("type");
                request.getSession().removeAttribute("productDetailProcess");
                request.getSession().removeAttribute("imageProcess");
                request.getSession().removeAttribute("preID");
                response.sendRedirect(request.getContextPath() + "/homemanager?messenger=<b>Update product '" + pid + "' successfully!</b><br>" + msg + "&style=success");
            } else {
                response.sendRedirect(request.getContextPath() + "/homemanager?messenger=<b>Cannot update product '" + pid + "'</b><br>" + msg + "&style=error");
            }

        } else {
            doGet(request, response);
        }

    }

}
