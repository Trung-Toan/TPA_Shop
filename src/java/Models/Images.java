/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author trantoan
 */
public class Images {
    private int id;
    private String name;
    private int productID;
    private String extension;

    public Images() {
    }
    
    public Images(int id, String name, int productID) {
        this.id = id;
        this.name = name;
        this.productID = productID;
    }

    public Images(int id, String name, int productID, String extension) {
        this.id = id;
        this.name = name;
        this.productID = productID;
        this.extension = getFileExtension(name);
    }
    
    public String getFileExtension(String name) {
        if (name != null && name.contains(".")) {
            return name.substring(name.lastIndexOf("."));
        }
        return "";
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }
}
