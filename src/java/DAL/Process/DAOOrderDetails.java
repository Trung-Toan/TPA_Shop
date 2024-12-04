/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL.Process;

import Models.OrderDetails;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

/**
 *
 * @author ADMIN
 */
public class DAOOrderDetails extends DAL.DAO {

    public Vector<OrderDetails> getAllOrderDetails() {
        String sql = "select * from OrderDetails";
        Vector<OrderDetails> list = new Vector<>();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                OrderDetails OD = new OrderDetails(
                        rs.getInt("id"),
                        rs.getInt("quantity"),
                        rs.getInt("totalPrice"),
                        rs.getInt("orderID"),
                        rs.getInt("productDetailID"),
                        rs.getString("size"),
                        rs.getString("color"),
                        rs.getInt("price")
                );
                list.add(OD);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

}
