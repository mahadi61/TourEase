package frames;

import javax.swing.*;
import entity.User;
import repository.UserRepo;

public class LoginFrame extends JFrame {
    private JTextField uTxt;
    private JPasswordField pTxt;

    public LoginFrame() {
        super("TourEase - Portal Setup");
        this.setSize(350, 220);
        this.setLayout(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        JLabel l1 = new JLabel("User:");
        l1.setBounds(30, 30, 80, 25);
        this.add(l1);
        uTxt = new JTextField();
        uTxt.setBounds(120, 30, 170, 25);
        this.add(uTxt);

        JLabel l2 = new JLabel("Password:");
        l2.setBounds(30, 70, 80, 25);
        this.add(l2);
        pTxt = new JPasswordField();
        pTxt.setBounds(120, 70, 170, 25);
        this.add(pTxt);

        JButton btn = new JButton("Login");
        btn.setBounds(120, 110, 100, 30);
        this.add(btn);
        btn.addActionListener(ae -> {
            User u = new UserRepo().checkLogin(uTxt.getText(), new String(pTxt.getPassword()));
            if (u != null) {
                new Home(u).setVisible(true);
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid Credentials");
            }
        });
    }
}