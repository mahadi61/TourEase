package frames;

import javax.swing.*;
import entity.User;
import repository.UserRepo;

public class PasswordChange extends JFrame {
    public PasswordChange(User user) {
        super("Security Settings");
        this.setSize(320, 220);
        this.setLayout(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        JLabel l1 = new JLabel("New Password:");
        l1.setBounds(20, 30, 120, 25);
        this.add(l1);
        JPasswordField passF = new JPasswordField();
        passF.setBounds(130, 30, 150, 25);
        this.add(passF);

        JButton save = new JButton("Update");
        save.setBounds(40, 90, 100, 30);
        this.add(save);
        JButton back = new JButton("Back");
        back.setBounds(160, 90, 100, 30);
        this.add(back);

        save.addActionListener(e -> {
            if (new UserRepo().changePassword(user.getUsername(), new String(passF.getPassword()))) {
                JOptionPane.showMessageDialog(this, "Password Updated Successfully");
            }
        });
        back.addActionListener(e -> {
            new Home(user).setVisible(true);
            this.dispose();
        });
    }
}