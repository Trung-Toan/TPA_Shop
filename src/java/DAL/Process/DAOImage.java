/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL.Process;

import Models.Images;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class DAOImage extends DAL.DAO {

    public List<Images> loadall() {
        String sql = "SELECT * FROM [Images]";
        List<Images> imagesList = new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Images img = new Images(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3)
                );
                imagesList.add(img);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return imagesList;
    }
}
