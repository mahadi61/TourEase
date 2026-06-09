package repository;

import entity.Booking;
import interfaces.IBookingRepo;
import java.sql.*;
import java.util.ArrayList;

public class BookingRepo implements IBookingRepo {
    public boolean createBooking(Booking b, double paymentAmount, String mode) {
        String bookSql = "INSERT INTO bookings VALUES (?,?,?,?,?)";
        String paySql = "INSERT INTO payments VALUES (?,?,?,?,?)";
        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            conn.setAutoCommit(false); // Transaction processing setup

            try (PreparedStatement bPst = conn.prepareStatement(bookSql)) {
                bPst.setString(1, b.getBookingId());
                bPst.setString(2, b.getCustomerId());
                bPst.setString(3, b.getPackageId());
                bPst.setString(4, b.getBookingDate());
                bPst.setString(5, b.getStatus());
                bPst.executeUpdate();
            }
            try (PreparedStatement pPst = conn.prepareStatement(paySql)) {
                pPst.setString(1, "PAY-" + (System.currentTimeMillis() % 100000));
                pPst.setString(2, b.getBookingId());
                pPst.setDouble(3, paymentAmount);
                pPst.setString(4, b.getBookingDate());
                pPst.setString(5, mode);
                pPst.executeUpdate();
            }
            conn.commit();
            return true;
        } catch (SQLException e) {
            if (conn != null)
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            e.printStackTrace();
            return false;
        } finally {
            if (conn != null)
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }
    }

    public ArrayList<Object[]> getBookingReport() {
        ArrayList<Object[]> list = new ArrayList<>();
        String sql = "SELECT b.bookingId, c.name, p.destination, b.bookingDate, pm.amountPaid, b.status " +
                "FROM bookings b JOIN customers c ON b.customerId=c.customerId " +
                "JOIN packages p ON b.packageId=p.packageId " +
                "LEFT JOIN payments pm ON b.bookingId=pm.bookingId";
        try (Connection conn = DatabaseConnection.getConnection();
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new Object[] { rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
                        rs.getDouble(5), rs.getString(6) });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}