/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL.Process;

import DAL.DAO;
import Models.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author ADMIN
 */
public class DAOAccount extends DAL.DAO {
    
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

    public User getAccountByEmail(String email) {
        String sql = "SELECT * FROM Users WHERE email = ?";
        User acc = null;
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, email);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                acc = new User(
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
                        rs.getDate("updateAt"))
                        ;
                return acc;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public User getAccountByUserName(String user_name) {
        String sql = "SELECT * FROM Users WHERE [userName] = ?";
        User acc = null;
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, user_name);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                acc = new User(
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
                        rs.getDate("updateAt")
                );
                return acc;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return acc;
    }

    public boolean addAccount(User acc) {
        String sql = "INSERT INTO [Users] (\n"
                + "[roleID],\n"
                + "[userName],\n"
                + "[password],\n"
                + "[email],\n"
                + "[phoneNumber],\n"
                + "[status]\n"
                + ") VALUES (0, ?, ?, ?, ?, 1) ";
        int n = 0;
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, acc.getUsername());
            st.setString(2, acc.getPassword());
            st.setString(3, acc.getEmail());
            st.setString(4, acc.getPhoneNumber());
            n = st.executeUpdate();
            n=1;
        } catch (SQLException e) {
            System.out.println(e);
        }
        
        return n > 0;
    }

}
