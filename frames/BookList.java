package frames;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import entity.User;
import repository.BookingRepo;
import java.util.ArrayList;

public class BookList extends JFrame {
    public BookList(User user) {
        super("TourEase - Consolidated Operations Ledger");
        this.setSize(680, 400);
        this.setLayout(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        DefaultTableModel model = new DefaultTableModel(
                new String[] { "Booking ID", "Client Name", "Destination", "Date", "Collected", "Status" }, 0);
        JTable table = new JTable(model);
        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(20, 20, 640, 280);
        this.add(sp);

        JButton back = new JButton("Return to Panel");
        back.setBounds(240, 315, 200, 35);
        this.add(back);
        back.addActionListener(e -> {
            new Home(user).setVisible(true);
            this.dispose();
        });

        ArrayList<Object[]> report = new BookingRepo().getBookingReport();
        for (Object[] row : report)
            model.addRow(row);
    }
}