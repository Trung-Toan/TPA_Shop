package DAL.Process;

import Models.Products;
import Models.SubCategorys;
import Models.Images;
import Models.ProductDetails;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class DAOCategory extends DAL.DAO {

    public SubCategorys getSubCategoryById(int id) {
        String sql = """
                     SELECT [id]
                           ,[name]
                           ,[categoryID]
                       FROM [dbo].[SubCategorys]
                     
                     where id =?""";
        
       SubCategorys p = null;
        
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
              
                p = new SubCategorys(
                        rs.getInt("id"),
                        rs.getString("name"),
                       rs.getInt("categoryID")       
                );
                return p;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return p;
    }

}