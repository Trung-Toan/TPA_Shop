/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL.Process;

import DAL.DAO;
import Models.Products;
import java.util.ArrayList;
import java.sql.*;
import Validations.GetDate;
import java.util.Comparator;

/**
 *
 * @author trantoan
 */
public class ProductProcess extends DAO {

    public static ProductProcess INSTANCE = new ProductProcess();

    private ProductProcess() {
    }

    private ArrayList<Products> listProducts;

    public ArrayList<Products> getListProducts() {
        return listProducts;
    }

    public void setListProducts(ArrayList<Products> listProducts) {
        this.listProducts = listProducts;
    }

    /**
     * insert new product
     *
     * @param product product need to insert
     * @return id of product after insert
     */
    public int insert(Products product) {
        int id = -1;
        String sql = "{call insertProduct(?, ?, ?, ?, ?, ?)}";

        try {
            ResultSet rs;
            try (PreparedStatement ps = this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, product.getName());
                ps.setString(2, product.getDescription());
                ps.setInt(3, product.getPrice());
                ps.setInt(4, product.getDiscount());
                ps.setInt(5, product.getGender());
                ps.setInt(6, product.getSubCategoryID());
                ps.execute();
                // callback to get id after insert
                rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    id = rs.getInt(1);
                }
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("================================");
            status = "Error at insert product: " + e.getMessage();
            e.getStackTrace();
        }
        return id;
    }

    /**
     * load all product from database
     *
     * @return list data after load
     */
    public ArrayList<Products> read() {
        int count = 0;
        listProducts = new ArrayList<>();
        String sql = "select [id], [name], [description], [price], [discount], [gender], [subCategoryID], [createdAt], [updatedAt] from [Products]";
        try {
            ResultSet rs;
            try (PreparedStatement ps = this.connection.prepareStatement(sql)) {
                rs = ps.executeQuery();
                while (rs.next()) {
                    Products product = new Products(rs.getInt(1), rs.getString(2), rs.getString(3),
                            rs.getInt(4), rs.getInt(5), rs.getInt(6),
                            rs.getInt(7), rs.getDate(8), rs.getDate(9));
                    listProducts.add(product);
                    count++;
                }
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("==================================");
            this.status = ("Error at load product: " + e.getMessage());
        }
        if (count == 0) {
            listProducts = null;
        }
        return listProducts;
    }

    /**
     * delete product and all foreign key of this
     *
     * @param id id product
     * @return true if delete is successful
     */
    public boolean delete(int id) {
        boolean check;
        String sql = "DELETE FROM [ProductDetails] WHERE [productID] = ?; "
                + "DELETE FROM [Images] WHERE [productID] = ?; "
                + "DELETE FROM [Products] WHERE [id] = ?;";
        try {
            try (PreparedStatement ps = this.connection.prepareStatement(sql)) {
                ps.setInt(1, id);
                ps.setInt(2, id);
                ps.setInt(3, id);
                ps.execute();
                check = true;
            }
        } catch (SQLException e) {
            System.out.println("===========================================");
            this.status = ("Error at delete product: " + e.getMessage());
            check = false;
        }
        return check;
    }

    /**
     * get product from an list by id product
     *
     * @param list list product
     * @param id id product need to find
     * @return an product after finding else return null
     */
    public Products getProduct(ArrayList<Products> list, int id) {
        Products products = null;
        for (Products pro : list) {
            if (pro.getId() == id) {
                products = pro;
                break;
            }
        }
        return products;
    }

    /**
     * update product by id product
     *
     * @param pid id product
     * @param nameProduct name product
     * @param price price product
     * @param discount discount product
     * @param gender gender (type) product
     * @return true if update success
     */
    public boolean update(String pid, String nameProduct, String price, String discount, String gender, String description) {
        boolean check;
        String sql = "UPDATE [Products] SET [name] = ?, [price] = ?, [discount] = ?, [gender] = ?, [description] = ?, [updatedAt] = ?  WHERE [id] = ?";
        String updatedAt = GetDate.getCurrentDate();
        try {
            try (PreparedStatement ps = this.connection.prepareStatement(sql)) {
                ps.setString(1, nameProduct);
                ps.setString(2, price);
                ps.setString(3, discount);
                ps.setString(4, gender);
                ps.setString(5, description);
                ps.setString(6, updatedAt);
                ps.setString(7, pid);
                ps.execute();
            }
            check = true;
        } catch (SQLException e) {
            this.status = e.getMessage();
            check = false;
        }
        return check;
    }

    /**
     * Sort product ascending by type product
     *
     * @param list list product
     * @param type this is id, name, date create, date update of product
     * @return list product after sort
     */
    public ArrayList<Products> sortAsc(ArrayList<Products> list, String type) {
        switch (type) {
            case "id" ->
                list.sort(Comparator.comparing(Products::getId));
            case "name" ->
                list.sort(Comparator.comparing(Products::getName));
            case "price" ->
                list.sort(Comparator.comparing(Products::getPrice));
            case "createdAt" ->
                list.sort(Comparator.comparing(Products::getCreatedAt));
            case "updatedAt" ->
                list.sort(Comparator.comparing(Products::getUpdatedAt));
            default -> {
                list.sort(Comparator.comparing(Products::getId));
            }
        }
        return list;
    }

    /**
     * Sort product descending by type product
     *
     * @param list list product
     * @param type this is id, name, date create, date update of product
     * @return list product after sort
     */
    public ArrayList<Products> sortDes(ArrayList<Products> list, String type) {
        switch (type) {
            case "id" ->
                list.sort(Comparator.comparing(Products::getId).reversed());
            case "name" ->
                list.sort(Comparator.comparing(Products::getName).reversed());
            case "price" ->
                list.sort(Comparator.comparing(Products::getPrice).reversed());
            case "createdAt" ->
                list.sort(Comparator.comparing(Products::getCreatedAt).reversed());
            case "updatedAt" ->
                list.sort(Comparator.comparing(Products::getUpdatedAt).reversed());
            default -> {
                list.sort(Comparator.comparing(Products::getId));
            }
        }
        return list;
    }

    /**
     * sort default by id product ascending
     *
     * @param list list product
     * @return list product after sort
     */
    public ArrayList<Products> sortDefault(ArrayList<Products> list) {
        list.sort(Comparator.comparing(Products::getId));
        return list;
    }

    /**
     * Sort products in ascending order by specified type
     *
     * @param type the type of product attribute to sort by (id, name, price,
     * createdAt, updatedAt)
     * @return list of products sorted in ascending order
     */
    public ArrayList<Products> sortAsc(String type, String name, String qrSelect) {
        String query = getSortQuery(type, "ASC", name, qrSelect);
        return executeQuery(query);
    }

    /**
     * Sort products in descending order by specified type
     *
     * @param type the type of product attribute to sort by (id, name, price,
     * createdAt, updatedAt)
     * @return list of products sorted in descending order
     */
    public ArrayList<Products> sortDes(String type, String name, String qrSelect) {
        String query = getSortQuery(type, "DESC", name, qrSelect);
        return executeQuery(query);
    }

    /**
     * Sort products by id in ascending order by default
     *
     * @return list of products sorted by id in ascending order
     */
    public ArrayList<Products> sortDefault(String name, String qrSelect) {
        String query;
        if (qrSelect.isBlank()) {
            query = "SELECT * FROM [dbo].[Products] WHERE [name] LIKE N'%" + name + "%' ORDER BY [id] ASC;";
        } else {
            query = "SELECT * FROM [dbo].[Products] WHERE " + qrSelect + " [name] LIKE N'%" + name + "%' AND 1=1 ORDER BY [id] ASC;";
        }
        ArrayList<Products> p = executeQuery(query);
        return p;
    }

    /**
     * Generate the SQL query for sorting products by the specified type and
     * order
     *
     * @param type the type of product attribute to sort
     * @param order the order of sorting (ASC for ascending, DESC for
     * descending)
     * @return the SQL query string
     */
    private String getSortQuery(String type, String order, String name, String qrSelect) {
        if (type.equals("none")) {
            return "SELECT * FROM [dbo].[Products] WHERE " + qrSelect + "[name] LIKE N'%" + name + "%' ORDER BY [id] " + order + ";";
        } else {
            return "SELECT * FROM [dbo].[Products] WHERE " + qrSelect + "[name] LIKE N'%" + name + "%' ORDER BY [" + type + "] " + order + ";";
        }
    }

    /**
     * Execute the SQL query and return the list of products
     *
     * @param query the SQL query string to execute
     * @return the list of products resulting from the query
     */
    private ArrayList<Products> executeQuery(String query) {
        ArrayList<Products> lp = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Products product = new Products();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setDescription(rs.getString("description"));
                product.setPrice(rs.getInt("price"));
                product.setDiscount(rs.getInt("discount"));
                product.setGender(rs.getInt("gender"));
                product.setCreatedAt(rs.getDate("createdAt"));
                product.setUpdatedAt(rs.getDate("updatedAt"));
                product.setSubCategoryID(rs.getInt("subCategoryID"));
                lp.add(product);
            }
        } catch (SQLException e) {
            this.status = ("Eror at excute quẻy sort product" + e.getMessage());
        }
        return lp;
    }
}
