/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL.Process;

import Models.Carts;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

/**
 *
 * @author ADMIN
 */
public class DAOCart extends DAL.DAO {

    public Vector<Carts> getAllCarts() {
        String sql = "select * from [Carts]";
        Vector<Carts> list = new Vector<>();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Carts cart = new Carts(
                        rs.getInt(1),
                        rs.getInt(2)
                );
                list.add(cart);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public Carts getCartByUserId(int userID) {
        String sql = "select * from [Carts] where [userID] = ?";
        Carts cart = null;
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, userID);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                cart = new Carts(
                        rs.getInt(1),
                        rs.getInt(2)
                );
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return cart;
    }

    public int insertCart(Carts cart) {
        String sql = "insert into [Carts] (userID) values (?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            st.setInt(1, cart.getUserID());
            st.executeUpdate();
            ResultSet rs = st.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1); // Trả về ID của cart vừa được thêm
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return -1; // Trả về -1 nếu có lỗi xảy ra
    }

}
