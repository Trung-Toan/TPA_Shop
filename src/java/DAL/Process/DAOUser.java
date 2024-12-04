/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL.Process;

import Models.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class DAOUser extends DAL.DAO {

    public static DAOUser INSTANCE = new DAOUser();

    /**
     * get all user by role id
     *
     * @param roleID role id of user
     * @return list users
     */
    public List getUser(int roleID) {
        List<User> listUsers = new ArrayList<>();
        String sql = "select * from [Users] where [roleID] = ?";
        try {
            ResultSet rs;
            try (PreparedStatement ps = this.connection.prepareStatement(sql)) {
                ps.setInt(1, roleID);
                rs = ps.executeQuery();
                while (rs.next()) {
                    listUsers.add(
                            new User(
                                    rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4),
                                    rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8),
                                    rs.getString(9), rs.getBoolean(10), rs.getDate(11), rs.getString(12),
                                    rs.getInt(13), rs.getDate(14), rs.getDate(15), rs.getDate(16)
                            )
                    );
                }
            }
            rs.close();
        } catch (SQLException e) {
            status = "Error at: " + e.getMessage();
        }
        return listUsers;
    }

    public User validateCustomer(String username, String password) {
        String sql = "SELECT * FROM [Users] where (email = ? or userName = ?) and password = ?";
        User acc = null;
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, username);
            st.setString(2, username);
            st.setString(3, password);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                acc = new User(
                        rs.getInt(1),
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
                        rs.getDate(14),
                        rs.getDate(15),
                        rs.getDate(16)
                );
                return acc;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return acc;
    }
    
    /**
     * update status user
     * 
     * @param id id user 
     * @param status status now
     * @return true if update success
     */
    public boolean update(String id, String status) {
        boolean check;
        String sql = "UPDATE [Users] SET [status] = ? WHERE id = ?";
        status = status.equals("1") ? "0" : "1";
        try {
            PreparedStatement ps = this.connection.prepareStatement(sql);
            ps.setString(1, status);
            ps.setString(2, id);
            ps.execute();
            check = true;
        } catch (SQLException e) {
            this.status  = "Error at: " + e.getMessage();
            check = false;
        }
        return check;
    }
}
