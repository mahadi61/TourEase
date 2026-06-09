package repository;

import entity.TourPackage;
import interfaces.IPackageRepo;
import java.sql.*;
import java.util.ArrayList;

public class PackageRepo implements IPackageRepo {
    public boolean addPackage(TourPackage p) {
        String sql = "INSERT INTO packages VALUES (?,?,?,?,?,?,?)";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, p.getPackageId());
            pst.setString(2, p.getDestination());
            pst.setString(3, p.getCategory());
            pst.setDouble(4, p.getPrice());
            pst.setString(5, p.getDuration());
            pst.setString(6, p.getHotelId().isEmpty() ? null : p.getHotelId());
            pst.setString(7, p.getTransportId().isEmpty() ? null : p.getTransportId());
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<TourPackage> getAllPackages() {
        ArrayList<TourPackage> list = new ArrayList<>();
        String sql = "SELECT * FROM packages";
        try (Connection conn = DatabaseConnection.getConnection();
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new TourPackage(rs.getString("packageId"), rs.getString("destination"),
                        rs.getString("category"), rs.getDouble("price"), rs.getString("duration"),
                        rs.getString("hotelId"), rs.getString("transportId")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}