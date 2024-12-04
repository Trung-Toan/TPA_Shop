package DAL.Process;

import Models.Products;
import Models.SubCategorys;
import Models.Images;
import Models.ProductDetails;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class DAOProduct extends DAL.DAO {

    public Products getProduct(int id) {
        Products product = null;
        String sql = "select [id], [name], [description], [price], [discount], [gender], [subCategoryID] from [Products]  where [id] = ?";
        try {
            PreparedStatement st = this.connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                product = new Products(
                        rs.getInt(1), 
                        rs.getString(2), 
                        rs.getString(3), 
                        rs.getInt(4), 
                        rs.getInt(5), 
                        rs.getInt(6), 
                        rs.getInt(7)
                );
            }
        } catch (SQLException e) {
            System.out.println("Error at" + e.getMessage());
        }
        return product;
    }

    public Products getProductById(int id) {
        String sql = """
                     Select * from [Products] P
                     join [Images] I on I.productId = P.id
                     join [ProductDetails] PD on PD.productID = P.id WHERE P.id = ?""";
        Products p = null;

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Images img = new Images(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("productID")
                );
                ProductDetails PDetails = new ProductDetails(
                        rs.getInt("id"),
                        rs.getInt("productID"),
                        rs.getString("size"),
                        rs.getString("color"),
                        rs.getInt("quantity")
                );
                p = new Products(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("price"),
                        rs.getInt("discount"),
                        rs.getInt("gender"),
                        img,
                        PDetails
                );
                return p;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return p;
    }

    public void updateQuantity(Products product) {
        String sql = "UPDATE ProductDetails "
                + "SET quantity = quantity - ? "
                + "WHERE productID = ? AND size = ? AND color = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, product.getQuantity());
            statement.setInt(2, product.getId());
            statement.setString(3, product.getPDetails().getSize());
            statement.setString(4, product.getPDetails().getColor());

            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
