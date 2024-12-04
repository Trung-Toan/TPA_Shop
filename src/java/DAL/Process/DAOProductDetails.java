
package DAL.Process;

import java.util.List;
import Models.ProductDetails;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;


public class DAOProductDetails extends DAL.DAO{
    public List<ProductDetails> getProductDetailsByProductId(int productId) {
        List<ProductDetails> list = new ArrayList<>();
        String sql = "SELECT * FROM [ProductDetails] WHERE [productID] = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, productId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                ProductDetails pd = new ProductDetails();
                pd.setId(rs.getInt("id"));
                pd.setProductID(rs.getInt("productID"));
                pd.setSize(rs.getString("size"));
                pd.setColor(rs.getString("color"));
                pd.setQuantity(rs.getInt("quantity"));
                list.add(pd);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }
}
