/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL.Process;

import DAL.DAO;
import Models.SubCategorys;
import java.util.ArrayList;
import java.sql.*;

/**
 *
 * @author trantoan
 */
public class SubCategoryProcess extends DAO {

    public static SubCategoryProcess INSTANCE = new SubCategoryProcess();

    private SubCategoryProcess() {
    }

    private ArrayList<SubCategorys> listSubCategorys = new ArrayList<>();

    public ArrayList<SubCategorys> getListSubCategorys() {
        return listSubCategorys;
    }

    public void setListSubCategorys(ArrayList<SubCategorys> listSubCategorys) {
        this.listSubCategorys = listSubCategorys;
    }

    /**
     * read all value of Sub category from database
     *
     * @return list sub category after read
     */
    public ArrayList<SubCategorys> read() {
        listSubCategorys = new ArrayList<>();
        String sql = "SELECT * FROM [SubCategorys]";
        try {
            ResultSet rs;
            try (PreparedStatement ps = this.connection.prepareStatement(sql)) {
                rs = ps.executeQuery();
                while (rs.next()) {
                    SubCategorys subCategory = new SubCategorys();
                    subCategory.setId(rs.getInt(1));
                    subCategory.setName(rs.getString(2));
                    subCategory.setCategoryID(rs.getInt(3));
                    listSubCategorys.add(subCategory);
                }
            }
            rs.close();
        } catch (SQLException e) {
            status = "Error at read sub category:" + e.getMessage();
        }
        return listSubCategorys;
    }

    /**
     * get sub category by id category
     *
     * @param categoryID id category
     * @return list subcategory from database
     */
    public ArrayList<SubCategorys> getSubCategory(String categoryID) {
        ArrayList<SubCategorys> list = new ArrayList<>();
        String sql = "SELECT * FROM [SubCategorys] WHERE [categoryID] = ?";
        try (PreparedStatement ps = this.connection.prepareStatement(sql)) {
            ps.setString(1, categoryID);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(new SubCategorys(rs.getInt(1), rs.getString(2), rs.getInt(3)));
                }
            }
        } catch (SQLException e) {
            this.status = ("Cannot get sub category because: " + e.getMessage());
        }
        return list;
    }

    /**
     * find all in list sub category by id category
     *
     * @param id id category
     * @return list sub category by id category
     */
    public ArrayList<SubCategorys> findSubCategory(int id) {
        ArrayList<SubCategorys> listSubCategory = new ArrayList<>();
        int count = 0;
        for (SubCategorys cate : listSubCategorys) {
            if (cate.getCategoryID() == (id)) {
                listSubCategory.add(cate);
                count++;
            }
        }
        if (count == 0) {
            listSubCategory = null;
        }
        return listSubCategory;
    }

    /**
     * get object sub category by id from list
     *
     * @param list list to find
     * @param id id sub category
     * @return object subcategory find by id
     */
    public SubCategorys getSubCategory(ArrayList<SubCategorys> list, int id) {
        SubCategorys subCategory = null;
        for (SubCategorys sub : list) {
            if (sub.getId() == id) {
                subCategory = sub;
                break;
            }
        }
        return subCategory;
    }

    /**
     * remove sub category by id subcategory
     *
     * @param subCID id subCategory
     * @return true if delete success
     */
    public boolean remove(String subCID) {
        boolean check = true;
        String sql = "DELETE FROM [SubCategorys] WHERE ID = ?";
        try {
            PreparedStatement ps = this.connection.prepareStatement(sql);
            ps.setString(1, subCID);
            ps.execute();
        } catch (SQLException e) {
            check = false;
            this.status = ("Error at delete sub category: " + e.getMessage());
        }
        return check;
    }

    /**
     * update all subCategory of category by id subCategory
     *
     * @param nameSub list (Array) name subCategory update
     * @param idSubUpdate list(Array) id subCategory update
     * @return true if update success
     */
    public boolean update(String[] nameSub, String[] idSubUpdate) {
        boolean check = true;
        String sql = "UPDATE [SubCategorys] set [name] = ? where [id] = ?";
        try {
            PreparedStatement ps = this.connection.prepareStatement(sql);
            for (int i = 0; i < nameSub.length; i++) {
                ps.setString(1, nameSub[i]);
                ps.setString(2, idSubUpdate[i]);
                ps.execute();
            }
        } catch (SQLException e) {
            check = false;
            this.status = ("Error cannot update sub category: " + e.getMessage());
        }
        return check;
    }

    /**
     * add new sub category
     *
     * @param nameAddSub name subCategory
     * @param CID id category
     * @return id new subCategory after insert success and -1 if not success
     */
    public int add(String nameAddSub, String CID) {
        int id = -1;
        String sql = "{call insertSubCategory(?, ?)}";
        try {
            ResultSet rs;
            try (PreparedStatement ps = this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, nameAddSub);
                ps.setString(2, CID);
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
}
