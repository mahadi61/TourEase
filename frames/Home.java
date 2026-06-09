package frames;

import javax.swing.*;
import entity.User;
import repository.DatabaseConnection;
import java.sql.*;

public class Home extends JFrame {
    private JLabel cCount, bCount, rCount;

    public Home(User user) {
        super("TourEase - Main Command Dashboard");
        this.setSize(600, 450);
        this.setLayout(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        JLabel welcome = new JLabel("Welcome, " + user.getUsername() + " (" + user.getRole() + ")");
        welcome.setBounds(20, 20, 300, 25);
        this.add(welcome);

        // Advanced Dynamic Metric Counter Panel UI Blocks
        JPanel p1 = new JPanel();
        p1.setBorder(BorderFactory.createTitledBorder("Total Clients"));
        p1.setBounds(20, 60, 160, 80);
        cCount = new JLabel("0");
        p1.add(cCount);
        this.add(p1);

        JPanel p2 = new JPanel();
        p2.setBorder(BorderFactory.createTitledBorder("Bookings"));
        p2.setBounds(200, 60, 160, 80);
        bCount = new JLabel("0");
        p2.add(bCount);
        this.add(p2);

        JPanel p3 = new JPanel();
        p3.setBorder(BorderFactory.createTitledBorder("Revenue"));
        p3.setBounds(380, 60, 180, 80);
        rCount = new JLabel("0.00 BDT");
        p3.add(rCount);
        this.add(p3);

        // Navigation Options
        JButton cBtn = new JButton("Customer Management");
        cBtn.setBounds(40, 180, 220, 40);
        this.add(cBtn);
        JButton pBtn = new JButton("Tour Package Setup");
        pBtn.setBounds(300, 180, 220, 40);
        this.add(pBtn);
        JButton bBtn = new JButton("New Booking Engine");
        bBtn.setBounds(40, 240, 220, 40);
        this.add(bBtn);
        JButton rBtn = new JButton("Billing & Operations Report");
        rBtn.setBounds(300, 240, 220, 40);
        this.add(rBtn);
        JButton passBtn = new JButton("Change Password");
        passBtn.setBounds(40, 300, 220, 40);
        this.add(passBtn);
        JButton outBtn = new JButton("Logout");
        outBtn.setBounds(300, 300, 220, 40);
        this.add(outBtn);

        // Navigation Action Routing Listeners
        cBtn.addActionListener(e -> {
            new ManageCustomer(user).setVisible(true);
            this.dispose();
        });
        pBtn.addActionListener(e -> {
            new ManagePackage(user).setVisible(true);
            this.dispose();
        });
        bBtn.addActionListener(e -> {
            new IssueBook(user).setVisible(true);
            this.dispose();
        });
        rBtn.addActionListener(e -> {
            new BookList(user).setVisible(true);
            this.dispose();
        });
        passBtn.addActionListener(e -> {
            new PasswordChange(user).setVisible(true);
            this.dispose();
        });
        outBtn.addActionListener(e -> {
            new LoginFrame().setVisible(true);
            this.dispose();
        });

        calculateMetrics();
    }

    private void calculateMetrics() {
        try (Connection conn = DatabaseConnection.getConnection()) {
            Statement s = conn.createStatement();
            ResultSet r = s.executeQuery("SELECT COUNT(*) FROM customers");
            if (r.next())
                cCount.setText(r.getString(1));
            r = s.executeQuery("SELECT COUNT(*) FROM bookings");
            if (r.next())
                bCount.setText(r.getString(1));
            r = s.executeQuery("SELECT SUM(amountPaid) FROM payments");
            if (r.next())
                rCount.setText(r.getDouble(1) + " BDT");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}