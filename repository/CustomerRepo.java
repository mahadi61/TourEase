package repository;

import entity.Customer;
import interfaces.ICustomerRepo;
import java.sql.*;
import java.util.ArrayList;

public class CustomerRepo implements ICustomerRepo {
    public boolean addCustomer(Customer c) {
        String sql = "INSERT INTO customers VALUES (?,?,?,?,?)";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, c.getCustomerId());
            pst.setString(2, c.getName());
            pst.setString(3, c.getPhone());
            pst.setString(4, c.getEmail());
            pst.setString(5, c.getNidOrPassport());
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateCustomer(Customer c) {
        String sql = "UPDATE customers SET name=?, phone=?, email=?, nidOrPassport=? WHERE customerId=?";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, c.getName());
            pst.setString(2, c.getPhone());
            pst.setString(3, c.getEmail());
            pst.setString(4, c.getNidOrPassport());
            pst.setString(5, c.getCustomerId());
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteCustomer(String id) {
        String sql = "DELETE FROM customers WHERE customerId=?";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, id);
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<Customer> searchCustomer(String keyword) {
        ArrayList<Customer> list = new ArrayList<>();
        String sql = "SELECT * FROM customers WHERE name LIKE ? OR phone LIKE ?";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, "%" + keyword + "%");
            pst.setString(2, "%" + keyword + "%");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new Customer(rs.getString("customerId"), rs.getString("name"), rs.getString("phone"),
                        rs.getString("email"), rs.getString("nidOrPassport")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}