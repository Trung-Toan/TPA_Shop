package DAL.Admin;

import DAL.DBContext;
import Models.Company;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CompanyInformation extends DBContext {

    public List<Company> getAll() {
        List<Company> companies = new ArrayList<>();
        String sql = "SELECT * FROM Company";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                String address = rs.getString("address");
                String dateStart = rs.getString("dateStart");
                String tax = rs.getString("tax");
                String updatedAt = rs.getString("updatedAt");

                Company comp = new Company(name, address, dateStart, tax, updatedAt);
                companies.add(comp);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CompanyInformation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return companies;
    }

    public void updateCompany(String originalName, String name, String address, String dateStart, String tax, String updatedAt) {
        String sql = """
                     UPDATE Company
                     SET name = ?, address = ?, dateStart = ?, tax = ?, updatedAt = ?
                     WHERE name = ?""";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.setString(2, address);
            statement.setString(3, dateStart);
            statement.setString(4, tax);
            statement.setString(5, updatedAt);
            statement.setString(6, originalName);
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CompanyInformation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        CompanyInformation companyInfo = new CompanyInformation();
        List<Company> companies = companyInfo.getAll();
        for (Company company : companies) {
            System.out.println("Name: " + company.getName());
            System.out.println("Address: " + company.getAddress());
            System.out.println("Date Start: " + company.getDateStart());
            System.out.println("Tax: " + company.getTax());
            System.out.println("Updated At: " + company.getUpdatedAt());
            System.out.println("-----------------------------");
        }
    }
}
