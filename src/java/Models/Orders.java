/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author trantoan
 */
public class Orders {

    private int id;
    private int userID;
    private int shipAddressID;
    private int status;
    private int quantity;
    private String size;
    private String color;
    private int price;
    private Date createdAt;
    private Date updatedAt;

    public Orders() {
    }

    public Orders(int id, int userID, int shipAddressID, int status, Date createdAt, Date updatedAt) {
        this.id = id;
        this.userID = userID;
        this.shipAddressID = shipAddressID;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Orders(int id, int status, int quantity, String size, String color, int price, Date createdAt, Date updatedAt) {
        this.id = id;
        this.status = status;
        this.quantity = quantity;
        this.size = size;
        this.color = color;
        this.price = price;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getShipAddressID() {
        return shipAddressID;
    }

    public void setShipAddressID(int shipAddressID) {
        this.shipAddressID = shipAddressID;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
