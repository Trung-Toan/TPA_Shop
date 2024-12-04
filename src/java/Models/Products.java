/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import DAL.Process.DAOCategory;
import java.util.Date;

/**
 *
 * @author trantoan
 */
public class Products {

    private int id;
    private String name;
    private String description;
    private int price;
    private int discount;
    private int gender; // who for product
    private int subCategoryID;
    private Date createdAt;
    private Date updatedAt;
    private int quantity;

    private Images img;
    private ProductDetails PDetails;
    private SubCategorys subcategory;

    public Products() {
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Products(int id, String name, String description, int price, int discount, int gender, int subCategoryID) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.discount = discount;
        this.gender = gender;
        this.subCategoryID = subCategoryID;
    }

    
    
    


    public Products(String name, String description, int price, int discount, int gender, int subCategoryID) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.discount = discount;
        this.gender = gender;
        this.subCategoryID = subCategoryID;
    }

    public Products(int id, String name, String description, int price, int discount, int gender, int subCategoryID, Date createdAt, Date updatedAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.discount = discount;
        this.gender = gender;
        this.subCategoryID = subCategoryID;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Products(int id, String name, int price, int discount, int gender, Images img, ProductDetails PDetails) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.discount = discount;
        this.gender = gender;
        this.img = img;
        this.PDetails = PDetails;
    }

    public Products(int id, String name, String description, int price, int discount, int gender, int subCategoryID, Date createdAt, Date updatedAt, Images img) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.discount = discount;
        this.gender = gender;
        this.subCategoryID = subCategoryID;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.img = img;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getSubCategoryID() {
        return subCategoryID;
    }

    public void setSubCategoryID(int subCategoryID) {
        this.subCategoryID = subCategoryID;
    }

    public String getSubCategoryName() {
        DAOCategory dao = new DAOCategory();
        SubCategorys sub = dao.getSubCategoryById(this.subCategoryID);
        return sub == null ? "No category" : sub.getName();
    }

    public Images getImg() {
        return img;
    }

    public void setImg(Images img) {
        this.img = img;
    }

    public ProductDetails getPDetails() {
        return PDetails;
    }

    public void setPDetails(ProductDetails PDetails) {
        this.PDetails = PDetails;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public SubCategorys getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(SubCategorys subcategory) {
        this.subcategory = subcategory;
    }

    public String getTotalPriceOnItems() {
        double totalPriceAfterDiscount = getPriceAfterDiscount2() * getQuantity();
        return String.format("%,.0f", totalPriceAfterDiscount).replace(",", ".");
    }

    public String getPriceAfterDiscount() {
        double productPrice = price * (1 - discount * 0.01);
        String formattedPrice = String.format("%,.0f", productPrice);
        formattedPrice = formattedPrice.replace(",", ".");
        return formattedPrice;
    }

    public double getPriceAfterDiscount2() {
        return price * (1 - discount * 0.01);
    }

}
