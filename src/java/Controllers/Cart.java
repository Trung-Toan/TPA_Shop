package Controllers;

import DAL.Process.DAOCart;
import DAL.Process.DAOCartDetails;
import DAL.Process.DAOOrder;
import DAL.Process.DAOProduct;
import DAL.Process.ImageProcess;
import DAL.Process.ProductProcess;
import Models.CartDetails;
import Models.Carts;
import Models.Images;
import Models.Products;
import Models.User;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class Cart extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         HttpSession session = request.getSession();
        User account = (User) session.getAttribute("account");

        if (account == null) {
            response.sendRedirect("./Views/Login/Login.jsp");
            return;
        }

        int userID = account.getId();

        ArrayList<Products> listProduct = (ArrayList<Products>) request.getSession().getAttribute("listProduct");
        if (listProduct == null) {
            listProduct = ProductProcess.INSTANCE.read();
        }

        // Load all Image
        ArrayList<Images> listImage = (ArrayList<Images>) request.getSession().getAttribute("listImage");
        if (listImage == null) {
            listImage = ImageProcess.INSTANCE.read();
            request.getSession().setAttribute("listImage", listImage);
            request.setAttribute("image", ImageProcess.INSTANCE);
        }

        request.setAttribute("listImage", listImage);
        request.setAttribute("image", ImageProcess.INSTANCE);
        request.setAttribute("listProduct", listProduct);

        DAOCart daoCart = new DAOCart();
        DAOCartDetails daoCartDetails = new DAOCartDetails();
        Carts userCart = daoCart.getCartByUserId(userID);

        if (userCart == null) {
            userCart = new Carts();
            userCart.setUserID(userID);
            daoCart.insertCart(userCart);
            userCart = daoCart.getCartByUserId(userID);
        }

        int cartID = userCart.getId();
        String Service = request.getParameter("Service");

        if (Service == null) {
            List<CartDetails> cartDetailsList = daoCartDetails.getAllCartDetailsByCartId(cartID);
            List<Products> productsLi = new ArrayList<>();
            float total = 0;

            for (CartDetails cartDetail : cartDetailsList) {
                DAOProduct daoProduct = new DAOProduct();
                Products pro = daoProduct.getProductById(cartDetail.getProductDetailID());
                pro.setQuantity(cartDetail.getQuantity());
                productsLi.add(pro);
                total += pro.getPriceAfterDiscount2() * cartDetail.getQuantity();
            }

            String formattedTotal = String.format("%,.0f", total).replace(",", ".");

            request.setAttribute("total", formattedTotal);
            request.setAttribute("products", productsLi);
            request.getRequestDispatcher("./Views/Customer/Cart.jsp").forward(request, response);

        } else if ("minusQuantity".equals(Service)) {
            String p_id = request.getParameter("product_id");
            CartDetails cartDetail = daoCartDetails.getCartDetailByProductDetailIdAndCartId(Integer.parseInt(p_id), cartID);

            if (cartDetail != null) {
                int num = cartDetail.getQuantity() - 1;
                if (num > 0) {
                    cartDetail.setQuantity(num);
                    daoCartDetails.updateCartDetail(cartDetail);
                } else {
                    daoCartDetails.deleteCartDetail(cartDetail.getProductDetailID(), cartDetail.getCartID());
                }
            }

            response.sendRedirect("Cart");

        } else if ("addQuantity".equals(Service)) {
            String p_id = request.getParameter("product_id");
            CartDetails cartDetail = daoCartDetails.getCartDetailByProductDetailIdAndCartId(Integer.parseInt(p_id), cartID);

            if (cartDetail != null) {
                cartDetail.setQuantity(cartDetail.getQuantity() + 1);
                daoCartDetails.updateCartDetail(cartDetail);
            }

            response.sendRedirect("Cart");

        } else if ("deleteInCart".equals(Service)) {
            String p_id = request.getParameter("product_id");
            daoCartDetails.deleteCartDetail(Integer.parseInt(p_id), cartID);
            response.sendRedirect("Cart");

        } else if (Service.equals("continue")) {
            List<CartDetails> cartDetailsList = daoCartDetails.getAllCartDetailsByCartId(cartID);
            Vector<Products> productsLi = new Vector<>();
            float total = 0;

            for (CartDetails cartDetail : cartDetailsList) {
                DAOProduct daoProduct = new DAOProduct();
                Products pro = daoProduct.getProductById(cartDetail.getProductDetailID());
                pro.setQuantity(cartDetail.getQuantity());
                productsLi.add(pro);
                total += pro.getPriceAfterDiscount2() * cartDetail.getQuantity();
            }

            String formattedTotal = String.format("%,.0f", total).replace(",", ".");

            request.setAttribute("total", formattedTotal);
            request.setAttribute("products", productsLi);
            request.getRequestDispatcher("./Views/Customer/Checkout.jsp").forward(request, response);

        } else if (Service.equals("checkout")) {
            List<CartDetails> cartDetailsList = daoCartDetails.getAllCartDetailsByCartId(cartID);
            Vector<Products> productsLi = new Vector<>();

            for (CartDetails cartDetail : cartDetailsList) {
                DAOProduct daoProduct = new DAOProduct();
                Products pro = daoProduct.getProductById(cartDetail.getProductDetailID());
                pro.setQuantity(cartDetail.getQuantity());
                productsLi.add(pro);
            }

            if (account != null) {
                try {
                    DAOOrder daoOrder = new DAOOrder();
                    daoOrder.checkout(account, productsLi);

                    for (Products pro : productsLi) {
                        DAOProduct daoProduct = new DAOProduct();
                        daoProduct.updateQuantity(pro);
                    }

                    daoCartDetails.clearCartDetailsByCartId(cartID);

                    request.setAttribute("message", "Checkout successfully");
                    request.getRequestDispatcher("./Views/Customer/Cart.jsp").forward(request, response);
                } catch (Exception e) {
                    request.setAttribute("message", "Checkout failed");
                    request.getRequestDispatcher("./Views/Customer/Cart.jsp").forward(request, response);
                }
            } else {
                request.getRequestDispatcher("./Views/Login/Login.jsp").forward(request, response);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer userID = (Integer) session.getAttribute("userID");

        if (userID == null) {
            response.sendRedirect("./Views/Login/Login.jsp");
            return;
        }

        String productIDStr = request.getParameter("id");
        if (productIDStr == null || productIDStr.isEmpty()) {
            response.sendRedirect("home");
            return;
        }

        int productID = Integer.parseInt(productIDStr);
        int quantity = 1;

        DAOCart daoCart = new DAOCart();
        DAOCartDetails daoCartDetails = new DAOCartDetails();
        Carts userCart = daoCart.getCartByUserId(userID);

        if (userCart == null) {
            userCart = new Carts();
            userCart.setUserID(userID);
            daoCart.insertCart(userCart);
            userCart = daoCart.getCartByUserId(userID);
        }

        int cartID = userCart.getId();
        CartDetails existingCartDetail = daoCartDetails.getCartDetailByProductDetailIdAndCartId(productID, cartID);

        if (existingCartDetail != null) {
            existingCartDetail.setQuantity(existingCartDetail.getQuantity() + quantity);
            daoCartDetails.updateCartDetail(existingCartDetail);
        } else {
            CartDetails newCartDetail = new CartDetails();
            newCartDetail.setQuantity(quantity);
            newCartDetail.setProductDetailID(productID);
            newCartDetail.setCartID(cartID);
            daoCartDetails.insertCartDetail(newCartDetail);
        }

        response.sendRedirect("Cart");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
