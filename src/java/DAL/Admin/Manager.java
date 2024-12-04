/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL.Admin;

import DAL.DBContext;
import Models.User;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author xuxum
 */
public class Manager extends DBContext {

    public int insertAccount(User user) {
        int n = 0;
        String sql = """
                     INSERT INTO [dbo].[Users]
                                ([roleID]
                                ,[userName]
                                ,[fullName]
                                ,[idCitizen]
                                ,[email]
                                ,[phoneNumber]
                                ,[password]
                                ,[address]
                                ,[gender]
                                ,[dob]
                                ,[image]
                                ,[status]
                                ,[dateStart]
                                ,[dateEnd]
                                ,[updatedAt])
                          VALUES
                                (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)""";
        PreparedStatement prestate = null;
        try {
            prestate = connection.prepareStatement(sql);
            prestate.setInt(1, user.getRoleID());
            prestate.setString(2, user.getUsername());
            prestate.setString(3, user.getFullname());
            prestate.setString(4, user.getIdCitizen());
            prestate.setString(5, user.getEmail());
            prestate.setString(6, user.getPhoneNumber());
            prestate.setString(7, user.getPassword());
            prestate.setString(8, user.getAddress());
            prestate.setBoolean(9, user.isGender());
            prestate.setDate(10, user.getDob() != null ? new java.sql.Date(user.getDob().getTime()) : null);
            prestate.setString(11, user.getImage());
            prestate.setInt(12, user.getStatus());
            prestate.setDate(13, user.getDateStart() != null ? new java.sql.Date(user.getDateStart().getTime()) : null);
            prestate.setDate(14, user.getDateEnd() != null ? new java.sql.Date(user.getDateEnd().getTime()) : null);
            prestate.setTimestamp(15, user.getUpdatedAt() != null ? new java.sql.Timestamp(user.getUpdatedAt().getTime()) : null);

            n = prestate.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Manager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
}
