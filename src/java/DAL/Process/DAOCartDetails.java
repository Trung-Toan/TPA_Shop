/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL.Process;

import Models.CartDetails;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

/**
 *
 * @author ADMIN
 */
public class DAOCartDetails extends DAL.DAO {

    public Vector<CartDetails> getAllCartDetailsByCartId(int cartID) {
        String sql = "select * from [CartDetails] where [cartID] = ?";
        Vector<CartDetails> list = new Vector<>();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, cartID);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                CartDetails cartDetail = new CartDetails(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getInt(3),
                        rs.getInt(4)
                );
                list.add(cartDetail);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public void insertCartDetail(CartDetails cartDetail) {
        String sql = "insert into [CartDetails] (quantity, productDetailID, cartID) values (?, ?, ?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, cartDetail.getQuantity());
            st.setInt(2, cartDetail.getProductDetailID());
            st.setInt(3, cartDetail.getCartID());
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void updateCartDetail(CartDetails cartDetail) {
        String sql = "update [CartDetails] set [quantity] = ? where [productDetailID] = ? and [cartID] = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, cartDetail.getQuantity());
            st.setInt(2, cartDetail.getProductDetailID());
            st.setInt(3, cartDetail.getCartID());
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public CartDetails getCartDetailByProductDetailIdAndCartId(int productDetailID, int cartID) {
        String sql = "select * from [CartDetails] where [productDetailID] = ? and [cartID] = ?";
        CartDetails cartDetail = null;
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, productDetailID);
            st.setInt(2, cartID);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                cartDetail = new CartDetails(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getInt(3),
                        rs.getInt(4)
                );
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return cartDetail;
    }

    public void deleteCartDetail(int productDetailID, int cartID) {
        String sql = "delete from [CartDetails] where [productDetailID] = ? and [cartID] = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, productDetailID);
            st.setInt(2, cartID);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void clearCartDetailsByCartId(int cartID) {
        String sql = "delete from [CartDetails] where [cartID] = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, cartID);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
