/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL.Process;

import DAL.DAO;
import Models.Categorys;
import java.util.ArrayList;
import java.sql.*;

/**
 *
 * @author trantoan
 */
public class CategoryProcess extends DAO {

    public static CategoryProcess INSTANCE = new CategoryProcess();

    private CategoryProcess() {
    }

    private ArrayList<Categorys> listCategorys;

    public ArrayList<Categorys> getListCategorys() {
        return listCategorys;
    }

    public void setListCategorys(ArrayList<Categorys> listCategorys) {
        this.listCategorys = listCategorys;
    }

    /**
     * read all category from database
     *
     * @return list category
     */
    public ArrayList<Categorys> read() {
        String sql = "SELECT * FROM [Categorys]";
        listCategorys = new ArrayList<>();
        try {
            ResultSet rs;
            try (PreparedStatement ps = this.connection.prepareStatement(sql)) {
                rs = ps.executeQuery();
                while (rs.next()) {
                    Categorys category = new Categorys();
                    category.setId(rs.getInt(1));
                    category.setName(rs.getString(2));
                    listCategorys.add(category);
                }
            }
            rs.close();
        } catch (SQLException e) {
            status = "Error at read category:" + e.getMessage();
        }
        return listCategorys;
    }

    /**
     * get category by id
     *
     * @param id property id need to get
     * @return null if not found and object category if finding id
     */
    public Categorys getCategoryByID(int id) {
        Categorys category = null;
        for (Categorys cate : listCategorys) {
            if (cate.getId() == id) {
                category = cate;
                break;
            }
        }
        return category;
    }

    /**
     * get object category by id from an list
     *
     * @param list list to find
     * @param id id category
     * @return an object category find in list
     */
    public Categorys getCategory(ArrayList<Categorys> list, int id) {
        Categorys category = null;
        for (Categorys cate : list) {
            if (cate.getId() == id) {
                category = cate;
                break;
            }
        }
        return category;
    }

    /**
     * remove an category by id
     *
     * @param CID id category
     * @return true if remove success
     */
    public boolean remove(String CID) {
        boolean check = true;
        String sql = "DELETE FROM [SubCategorys] WHERE [categoryID] = ? "
                + "DELETE FROM [Categorys] WHERE ID = ?";
        try {
            PreparedStatement ps = this.connection.prepareStatement(sql);
            ps.setString(1, CID);
            ps.setString(2, CID);
            ps.execute();
        } catch (SQLException e) {
            check = false;
            this.status = ("Error at delete sub category: " + e.getMessage());
        }
        return check;
    }

    /**
     * update name category by id category
     *
     * @param nameC name category
     * @param CID id category
     * @return true if update success
     */
    public boolean update(String[] nameC, String CID) {
        boolean check = true;
        String sql = "UPDATE [Categorys] set [name] = ? where [id] = ?";
        try {
            PreparedStatement ps = this.connection.prepareStatement(sql);
            for (String nameC1 : nameC) {
                ps.setString(1, nameC1);
                ps.setString(2, CID);
                ps.execute();
            }
        } catch (SQLException e) {
            check = false;
            this.status = ("Error cannot update category: " + e.getMessage());
        }
        return check;
    }

    /**
     * add new category
     *
     * @param cateAdd name category
     * @return id category after update success and empty if not success
     */
    public String add(String cateAdd) {
        int id = 0;
        String sql = "{call insertCategory(?)}";

        try {
            ResultSet rs;
            try (PreparedStatement ps = this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, cateAdd);
                ps.execute();
                // callback to get id after insert
                rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    id = rs.getInt(1);
                }
            }
            rs.close();
        } catch (SQLException e) {
            this.status = ("================================");
            status = "Error at insert product: " + e.getMessage();
            e.getStackTrace();
        }
        return id == 0 ? "" : id + "";
    }
}
