/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author trantoan
 */
public class ProductDetails {
    private int id;
    private int productID;
    private String size;
    private String color;
    private int quantity;
    
    private int pid;

    public ProductDetails() {
    }

    public ProductDetails(int id, int productID, String size, String color, int quantity) {
        this.id = id;
        this.productID = productID;
        this.size = size;
        this.color = color;
        this.quantity = quantity;
    }

    public ProductDetails(int pid, int quantity) {
        this.pid = pid;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }
    
    
}