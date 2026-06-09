package repository;

import entity.User;
import interfaces.IUserRepo;
import java.sql.*;

public class UserRepo implements IUserRepo {
    public User checkLogin(String username, String password) {
        String sql = "SELECT * FROM users WHERE username=? AND password=?";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, username);
            pst.setString(2, password);
            ResultSet rs = pst.executeQuery();
            if (rs.next())
                return new User(rs.getString("userId"), rs.getString("username"), null, rs.getString("role"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean changePassword(String username, String newPass) {
        String sql = "UPDATE users SET password=? WHERE username=?";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, newPass);
            pst.setString(2, username);
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}