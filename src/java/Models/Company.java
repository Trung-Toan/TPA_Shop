/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author xuxum
 */
public class Company {

    private String name;
    private String address;
    private String dateStart;
    private String tax;
    private String updatedAt;

    public Company() {
    }

    public Company(String name, String address, String dateStart, String tax, String updatedAt) {
        this.name = name;
        this.address = address;
        this.dateStart = dateStart;
        this.tax = tax;
        this.updatedAt = updatedAt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDateStart() {
        return dateStart;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    public String getTax() {
        return tax;
    }

    public void setTax(String tax) {
        this.tax = tax;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "Company{" + "name=" + name + ", address=" + address + ", dateStart=" + dateStart + ", tax=" + tax + ", updatedAt=" + updatedAt + '}';
    }

}
