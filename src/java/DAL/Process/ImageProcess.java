/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL.Process;

import DAL.DAO;
import Models.Images;
import Models.ProductDetails;
import java.util.ArrayList;
import java.sql.*;
import java.util.List;

public class ImageProcess extends DAO {

    public static ImageProcess INSTANCE = new ImageProcess();

    private ImageProcess() {
    }

    private ArrayList<Images> listImages;

    public ArrayList<Images> getListImages() {
        return listImages;
    }

    public void setListImages(ArrayList<Images> listImages) {
        this.listImages = listImages;
    }

    /**
     * insert images
     *
     * @param img list images
     * @param idProduct id product need to insert images
     * @return true if insert is successful
     */
    public boolean insert(List<String> img, int idProduct) {
        boolean check = false;
        String sql = "INSERT INTO [Images] ([name], [productID]) VALUES (?,?)";

        try {
            if (img.isEmpty()) {
                throw new IllegalArgumentException("Image list cannot be null or empty");
            }

            for (String imageName : img) {
                try (PreparedStatement ps = connection.prepareStatement(sql)) {
                    ps.setString(1, imageName);
                    ps.setInt(2, idProduct);
                    ps.execute();
                    check = true;
                }
            }

        } catch (SQLException e) {
            this.status = ("===============================================");
            this.status = ("Error at insert Images: " + e.getMessage());
            check = false;
        }
        return check;
    }

    /**
     * get all images
     *
     * @return all images from database return null if don't have value
     */
    public ArrayList<Images> read() {
        listImages = new ArrayList<>();
        String sql = "SELECT * FROM [Images]";
        int count = 0;

        try {
            ResultSet rs;
            try (PreparedStatement ps = this.connection.prepareStatement(sql)) {
                rs = ps.executeQuery();
                while (rs.next()) {
                    listImages.add(new Images(rs.getInt(1), rs.getString(2), rs.getInt(3)));
                    count++;
                }
            }
            rs.close();
        } catch (SQLException e) {
            this.status = ("================================================");
            this.status = ("Error at get value Images: " + e.getMessage());
        }
        if (count == 0) {
            listImages = null;
        }
        return listImages;
    }

    /**
     * get all image of product from list image by id product
     *
     * @param list list images
     * @param idProduct id product
     * @return list image from product else return null
     */
    public ArrayList<Images> getImage(ArrayList<Images> list, int idProduct) {
        listImages = new ArrayList<>();
        int count = 0;
        for (Images image : list) {
            if (image.getProductID() == idProduct) {
                listImages.add(image);
                count++;
            }
        }
        if (count == 0) {
            listImages = null;
        }
        return listImages;
    }

    /**
     * remove image in session by id image
     *
     * @param list list image
     * @param idImage is image
     * @return list image after remove
     */
    public ArrayList<Images> removeImage(ArrayList<Images> list, int idImage) {
        for (Images img : list) {
            if (img.getId() == idImage) {
                list.remove(img);
                break;
            }
        }
        return list;
    }

    /**
     * delete images by id images
     *
     * @param imgDelete list id image need to delete
     */
    public void delete(ArrayList<String> imgDelete) {
        String sql = "DELETE FROM [Images] WHERE [id] = ?";

        try {
            try (PreparedStatement ps = this.connection.prepareStatement(sql)) {
                for (String pd : imgDelete) {
                    ps.setInt(1, Integer.parseInt(pd));
                    ps.execute();
                }
            }
        } catch (NumberFormatException | SQLException e) {
            this.status = ("Cannot delete an images at: " + e.getMessage());
        }
    }

}
