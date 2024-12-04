/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL.Process;

import Models.ShipAddresses;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

/**
 *
 * @author ADMIN
 */
public class DAOShipAddress extends DAL.DAO {

    public ShipAddresses getShipAddressByUserId(int userId) {
        String sql = "SELECT * FROM ShipAddresses WHERE userID = ?";
        ShipAddresses shipAddress = null;

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, userId);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                shipAddress = new ShipAddresses(
                        rs.getInt("id"),
                        rs.getString("userName"),
                        rs.getString("address"),
                        rs.getString("phoneNumber"),
                        rs.getInt("userID")
                );
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return shipAddress;
    }

    public static void main(String[] args) {
        DAOShipAddress dsa = new DAOShipAddress();
        System.out.println(dsa.getShipAddressByUserId(4));
    }
}
