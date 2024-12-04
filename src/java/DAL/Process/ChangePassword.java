/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL.Process;

import DAL.DAO;
import java.sql.PreparedStatement;

/**
 *
 * @author admin
 */
public class ChangePassword extends DAO{
    public static ChangePassword INSTANCE = new ChangePassword();
    private ChangePassword() {
    }
    
    /**
     * 
     * @param id
     * @param newPass 
     */
    public void changePassword(int id, String newPass) {
        String sql = "update [Users] set [password] = ? where id = ? ";
        try {
            
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, newPass);
            statement.setInt(2, id);
            statement.executeUpdate();
        } catch (Exception e) {
            status = "Error at read Products"+e.getMessage();
        }
    }
       
}
