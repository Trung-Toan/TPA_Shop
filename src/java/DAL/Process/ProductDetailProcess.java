/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL.Process;

import DAL.DAO;
import Models.ProductDetails;
import java.util.ArrayList;
import java.sql.*;

/**
 *
 * @author trantoan
 */
public class ProductDetailProcess extends DAO {

    public static ProductDetailProcess INSTANCE = new ProductDetailProcess();

    private ProductDetailProcess() {
    }

    private ArrayList<ProductDetails> listProductDetails = new ArrayList<>();

    public ArrayList<ProductDetails> getListProductDetails() {
        return listProductDetails;
    }

    public void setListProductDetails(ArrayList<ProductDetails> listProductDetails) {
        this.listProductDetails = listProductDetails;
    }

    /**
     * insert product detail into database
     *
     * @param idPro id product
     * @param sizes size product
     * @param colors color product
     * @param quantities quantity product
     */
    public void insert(int idPro, String[] sizes, String[] colors, String[] quantities) {
        String sql = "INSERT INTO [ProductDetails] ([productID],[size],[color],[quantity]) VALUES (?,?,?,?)";

        try {
            if (sizes.length == 0 || colors.length == 0 || quantities.length == 0) {
                throw new IllegalArgumentException("Lists cannot be null or empty");
            }

            for (int i = 0; i < sizes.length; i++) {
                try (
                        PreparedStatement ps = connection.prepareStatement(sql)) {
                    ps.setInt(1, idPro);
                    ps.setString(2, sizes[i]);
                    ps.setString(3, colors[i]);
                    ps.setInt(4, Integer.parseInt(quantities[i]));

                    ps.execute();
                }
            }
        } catch (SQLException e) {
            this.status = ("============================");
            this.status = ("Error at insert ProductDetails: " + e.getMessage());
        }
    }

    /**
     * get all productDetail by id product
     *
     * @param idProduct id product
     * @return list product detail by product
     */
    public ArrayList<ProductDetails> getProductDetail(int idProduct) {
        listProductDetails = new ArrayList<>();
        String sql = "select * from [ProductDetails] where [productID] = ?";
        int count = 0;
        try {
            ResultSet rs;
            try (PreparedStatement ps = this.connection.prepareStatement(sql)) {
                ps.setInt(1, idProduct);
                rs = ps.executeQuery();
                while (rs.next()) {
                    listProductDetails.add(new ProductDetails(rs.getInt(1), rs.getInt(2), rs.getString(3),
                            rs.getString(4), rs.getInt(5)));
                    count++;
                }
            }
            rs.close();
        } catch (SQLException e) {
            this.status = ("====================================");
            this.status = ("Error at get data product detail from database: " + e.getMessage());
        }
        if (count == 0) {
            listProductDetails = null;
        }
        return listProductDetails;
    }

    /**
     * remove an product detail by id product detail
     *
     * @param list list product detail
     * @param idPD id product detail
     * @return list product after remove
     */
    public ArrayList<ProductDetails> removeProductDetail(ArrayList<ProductDetails> list, int idPD) {
        for (ProductDetails pd : list) {
            if (pd.getId() == idPD) {
                list.remove(pd);
                break;
            }
        }
        return list;
    }

    /**
     * delete an product detail in database by id product detail
     *
     * @param pdDelete list product detail need to delete
     */
    public void delete(ArrayList<String> pdDelete) {
        String sql = "DELETE FROM [ProductDetails] WHERE [id] = ?";

        try {
            try (PreparedStatement ps = this.connection.prepareStatement(sql)) {
                for (String pd : pdDelete) {
                    ps.setInt(1, Integer.parseInt(pd));
                    ps.execute();
                }
            }
        } catch (NumberFormatException | SQLException e) {
            this.status = ("Cannot delete an product detail at: " + e.getMessage());
        }
    }

    /**
     * update productDetail by id product details
     *
     * @param size list size need to update
     * @param color list color need to update
     * @param quantity list color need to update
     * @param idPDUpdate list color need to update
     */
    public void update(String[] size, String[] color, String[] quantity, String[] idPDUpdate) {
        String sql = "UPDATE [ProductDetails] SET [size] = ?, [color] = ?, [quantity] = ? WHERE [id] = ?";
        try {
            PreparedStatement ps = this.connection.prepareStatement(sql);
            for (int i = 0; i < idPDUpdate.length; i++) {
                ps.setString(1, size[i]);
                ps.setString(2, color[i]);
                ps.setString(3, quantity[i]);
                ps.setString(4, idPDUpdate[i]);
                ps.execute();
            }
        } catch (SQLException e) {
            this.status = ("Cannot update product detail by: " + e.getMessage());
        }

    }
}
