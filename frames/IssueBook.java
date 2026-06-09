package frames;

import javax.swing.*;
import entity.Booking;
import entity.User;
import repository.BookingRepo;

public class IssueBook extends JFrame {
    private JTextField bIdT, cIdT, pIdT, dateT, amtT, modeT;

    public IssueBook(User user) {
        super("TourEase - New Settlement Terminal");
        this.setSize(360, 360);
        this.setLayout(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        JLabel l1 = new JLabel("Booking ID:");
        l1.setBounds(30, 20, 100, 20);
        this.add(l1);
        bIdT = new JTextField("BK-" + System.currentTimeMillis() % 10000);
        bIdT.setBounds(140, 20, 160, 20);
        this.add(bIdT);
        JLabel l2 = new JLabel("Client ID:");
        l2.setBounds(30, 50, 100, 20);
        this.add(l2);
        cIdT = new JTextField();
        cIdT.setBounds(140, 50, 160, 20);
        this.add(cIdT);
        JLabel l3 = new JLabel("Package ID:");
        l3.setBounds(30, 80, 100, 20);
        this.add(l3);
        pIdT = new JTextField();
        pIdT.setBounds(140, 80, 160, 20);
        this.add(pIdT);
        JLabel l4 = new JLabel("Date (YYYY-MM-DD):");
        l4.setBounds(30, 110, 130, 20);
        this.add(l4);
        dateT = new JTextField("2026-06-09");
        dateT.setBounds(140, 110, 160, 20);
        this.add(dateT);
        JLabel l5 = new JLabel("Pay Amount:");
        l5.setBounds(30, 140, 100, 20);
        this.add(l5);
        amtT = new JTextField();
        amtT.setBounds(140, 140, 160, 20);
        this.add(amtT);
        JLabel l6 = new JLabel("Pay Method:");
        l6.setBounds(30, 170, 100, 20);
        this.add(l6);
        modeT = new JTextField("Cash");
        modeT.setBounds(140, 170, 160, 20);
        this.add(modeT);

        JButton confirm = new JButton("Execute Transaction");
        confirm.setBounds(30, 210, 270, 35);
        this.add(confirm);
        JButton back = new JButton("Cancel");
        back.setBounds(30, 255, 270, 30);
        this.add(back);

        confirm.addActionListener(e -> {
            Booking b = new Booking(bIdT.getText(), cIdT.getText(), pIdT.getText(), dateT.getText(), "Confirmed");
            boolean success = new BookingRepo().createBooking(b, Double.parseDouble(amtT.getText()), modeT.getText());
            if (success) {
                JOptionPane.showMessageDialog(this, "Receipt Generated Successfully");
                new Home(user).setVisible(true);
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Transaction Aborted - Check IDs");
            }
        });
        back.addActionListener(e -> {
            new Home(user).setVisible(true);
            this.dispose();
        });
    }
}