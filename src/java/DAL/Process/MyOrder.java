/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL.Process;

import DAL.DAO;
import Models.Orders;
import Models.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

/**
 *
 * @author admin
 */
public class MyOrder extends DAO {

    public static MyOrder INSTANCE = new MyOrder();

    public MyOrder() {
    }
    private ArrayList<MyOrder> listOrder = new ArrayList<>();

    public Vector<Orders> getAllOrderByAccount(int accId) {
        String sql = "Select*from Orders O\n"
                + "join Users U ON U.id = O.userID where U.id = ? order by createdAt desc ";
        Vector<Orders> list = new Vector<>();

        try {
            ResultSet rs;
            try (PreparedStatement ps = this.connection.prepareStatement(sql)) {
                ps.setInt(1, accId);
                rs = ps.executeQuery();
                while (rs.next()) {
                    User acc = new User(
                            rs.getInt("id"),
                            rs.getInt("roleID"),
                            rs.getString("username"),
                            rs.getString("fullname"),
                            rs.getString("idCitizen"),
                            rs.getString("email"),
                            rs.getString("phoneNumber"),
                            rs.getString("password"),
                            rs.getString("address"),
                            rs.getBoolean("gender"),
                            rs.getDate("dob"),
                            rs.getString("image"),
                            rs.getInt("status"),
                            rs.getDate("dateStart"),
                            rs.getDate("dateEnd"),
                            rs.getDate("updatedAt")
                    );
                    Orders order = new Orders(
                            rs.getInt("id"),
                            rs.getInt("status"),
                            rs.getInt("quantity"),
                            rs.getString("size"),
                            rs.getString("color"),
                            rs.getInt("price"),
                            rs.getDate("createdAt"),
                            rs.getDate("updatedAt")
                                    
                    );
                }
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("Error at get hisstory order: " + e.getMessage());
        }
        return list;
    }
    
 
}
