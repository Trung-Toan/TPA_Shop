/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL.Process;

import DAL.DAO;
import java.sql.PreparedStatement;
import java.util.Date;

/**
 *
 * @author admin
 */
public class Profile extends DAO {

    public static Profile INSTANCE = new Profile();

    public Profile() {
    }

    public boolean updateProfile(int id, String fullName, String email, int phoneNumber, String address, boolean gender, String dob, String image) {
        String sql = "UPDATE [Users] SET [fullName] = ?, [email] = ?, [phoneNumber] = ?, [address] = ?, [gender] = ?, [dob] = ?, [image] = ? WHERE id = ?";
        boolean check;

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, fullName);
            st.setString(2, email);
            st.setInt(3, phoneNumber);
            st.setString(4, address);
            st.setBoolean(5, gender);
            st.setString(6, dob);
            st.setString(7, image);
            st.setInt(8, id);
            st.execute();
            check = true;
        } catch (Exception e) {
            System.out.println("===================================================");
            status = "Error at read Products" + e.getMessage();
            check = false;
        }
        return check;
    }

}
