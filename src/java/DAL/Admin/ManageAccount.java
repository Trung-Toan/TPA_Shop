/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL.Admin;

import DAL.DBContext;
import Models.Products;
import java.sql.*;
import Models.User;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author xuxum
 */
//Admin need to Ban and active account
public class ManageAccount extends DBContext {

    public void deleteAccount(String accid) {
        String sql = """
                     DELETE FROM [dbo].[Users]
                           WHERE id = ?""";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, accid);
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ManageAccount.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void accountStatus(String username, String action) {
        String sql = "UPDATE Users SET status = ? WHERE userName = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            if ("ban".equals(action)) {
                //0 for banned
                statement.setInt(1, 0);
            } else if ("activate".equals(action)) {
                //1 for active
                statement.setInt(1, 1);
            }
            statement.setString(2, username);
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ManageAccount.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int getAccountCount() {
        String sql = "SELECT COUNT(*) FROM User";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ManageAccount.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public List<User> getAccountsByPage(int page, int pageSize) {
        List<User> accounts = new ArrayList<>();
        String sql = "SELECT * FROM User ORDER BY id OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, (page - 1) * pageSize);
            statement.setInt(2, pageSize);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                int roleID = rs.getInt(2);
                String username = rs.getString(3);
                String fullname = rs.getString(4);
                String idCitizen = rs.getString(5);
                String email = rs.getString(6);
                String phoneNumber = rs.getString(7);
                String password = rs.getString(8);
                String address = rs.getString(9);
                boolean gender = rs.getBoolean(10);
                java.util.Date dob = rs.getDate(11);
                String image = rs.getString(12);
                int status = rs.getInt(13);
                java.util.Date dateStart = rs.getDate(14);
                java.util.Date dateEnd = rs.getDate(15);
                java.util.Date updatedAt = rs.getDate(16);

                User acc = new User(id, roleID, username, fullname, idCitizen, email, phoneNumber, password, address,
                        gender, dob, image, status, dateStart, dateEnd, updatedAt);
                accounts.add(acc);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ManageAccount.class.getName()).log(Level.SEVERE, null, ex);
        }
        return accounts;
    }

    //Get All Account
    public List<User> getAllAccount() {
        List<User> accounts = new ArrayList<>();
        String sql = "select * from User";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                int roleID = rs.getInt(2);
                String username = rs.getString(3);
                String fullname = rs.getString(4);
                String idCitizen = rs.getString(5);
                String email = rs.getString(6);
                String phoneNumber = rs.getString(7);
                String password = rs.getString(8);
                String address = rs.getString(9);
                boolean gender = rs.getBoolean(10);
                java.util.Date dob = rs.getDate(11);
                String image = rs.getString(12);
                int status = rs.getInt(13);
                java.util.Date dateStart = rs.getDate(14);
                java.util.Date dateEnd = rs.getDate(15);
                java.util.Date updatedAt = rs.getDate(16);

                User acc = new User(id, roleID, username, fullname, idCitizen, email, phoneNumber, password, address,
                        gender, dob, image, status, dateStart, dateEnd, updatedAt);
                accounts.add(acc);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ManageAccount.class.getName()).log(Level.SEVERE, null, ex);
        }
        return accounts;
    }

    public List<Products> getAllProduct() {
        List<Products> product = new ArrayList<>();
        String sql = "select * from Products";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                String description = rs.getString(3);
                int price = rs.getInt(4);
                int discount = rs.getInt(5);
                int gender = rs.getInt(6);
                java.util.Date createdAt = rs.getDate(7);
                java.util.Date updatedAt = rs.getDate(8);
                int subCategoryID = rs.getInt(9);

                Products pro = new Products(id, name, description, price,
                        discount, gender, subCategoryID, createdAt, updatedAt);
                product.add(pro);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ManageAccount.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return product;
    }

    public static void main(String[] args) {
        ManageAccount man = new ManageAccount();
        int page = 1;
        int pageSize = 7;
        List<User> account = man.getAccountsByPage(page, pageSize);

        for (User users : account) {
            System.out.println(users.getUsername());
            System.out.println(users.getFullname());
            System.out.println(users.getId());
        }
        int accountCount = man.getAccountCount();
        System.out.println("Total number of account: " + accountCount);
        
        String accid = "34";
        man.deleteAccount(accid);
    }
}
