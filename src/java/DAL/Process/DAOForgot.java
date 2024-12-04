/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL.Process;

import java.sql.*;
import DAL.DBContext;
import Models.User;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author xuxum
 */
public class DAOForgot extends DBContext {

    //check User
    public User checkUsersForChangePass(String email) {
        String sql = "Select * from Users Where email = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, email);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                java.util.Date dateStart = rs.getDate(14);
                java.util.Date dateEnd = rs.getDate(15);
                java.util.Date updatedAt = rs.getDate(16);
                User use = new User(rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getBoolean(10),
                        rs.getDate(11),
                        rs.getString(12),
                        rs.getInt(13),
                        dateStart, dateEnd, updatedAt);
                return use;
            }
        } catch (SQLException ex) {
        }
        return null;
    }

    //change password
    public void changePassword(int id, String newPass) {
        String sql = "update Users set [password] = ? Where id = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, newPass);
            statement.setInt(2, id);
            statement.executeUpdate();
        } catch (SQLException ex) {
        }
    }

    public static void main(String[] args) {
        DAOForgot dao = new DAOForgot();

        String testEmail = "phongnnhe176274@fpt.edu.vn";

        User user = dao.checkUsersForChangePass(testEmail);

        if (user != null) {
            System.out.println("User found:" + testEmail);
        } else {
            System.out.println("User not found");
        }
    }
}
