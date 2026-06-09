package frames;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import entity.Customer;
import entity.User;
import repository.CustomerRepo;
import java.util.ArrayList;

public class ManageCustomer extends JFrame {
    private JTextField idT, nameT, phT, emT, nidT, sTxt;
    private JTable table;
    private DefaultTableModel model;
    private CustomerRepo repo = new CustomerRepo();
    private User currUser;

    public ManageCustomer(User user) {
        super("TourEase - Client Registry");
        this.setSize(750, 480);
        this.setLayout(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.currUser = user;

        JLabel l1 = new JLabel("ID:");
        l1.setBounds(20, 20, 50, 20);
        this.add(l1);
        idT = new JTextField();
        idT.setBounds(80, 20, 120, 20);
        this.add(idT);
        JLabel l2 = new JLabel("Name:");
        l2.setBounds(20, 50, 50, 20);
        this.add(l2);
        nameT = new JTextField();
        nameT.setBounds(80, 50, 120, 20);
        this.add(nameT);
        JLabel l3 = new JLabel("Phone:");
        l3.setBounds(20, 80, 50, 20);
        this.add(l3);
        phT = new JTextField();
        phT.setBounds(80, 80, 120, 20);
        this.add(phT);
        JLabel l4 = new JLabel("Email:");
        l4.setBounds(20, 110, 50, 20);
        this.add(l4);
        emT = new JTextField();
        emT.setBounds(80, 110, 120, 20);
        this.add(emT);
        JLabel l5 = new JLabel("NID/Pass:");
        l5.setBounds(20, 140, 70, 20);
        this.add(l5);
        nidT = new JTextField();
        nidT.setBounds(80, 140, 120, 20);
        this.add(nidT);

        JButton addB = new JButton("Save");
        addB.setBounds(20, 180, 85, 25);
        this.add(addB);
        JButton upB = new JButton("Update");
        upB.setBounds(115, 180, 85, 25);
        this.add(upB);
        JButton delB = new JButton("Delete");
        delB.setBounds(20, 215, 180, 25);
        this.add(delB);
        JButton back = new JButton("Back to Dashboard");
        back.setBounds(20, 380, 180, 30);
        this.add(back);

        JLabel sLabel = new JLabel("Search Name/Phone:");
        sLabel.setBounds(230, 20, 150, 20);
        this.add(sLabel);
        sTxt = new JTextField();
        sTxt.setBounds(380, 20, 180, 20);
        this.add(sTxt);
        JButton sBtn = new JButton("Find");
        sBtn.setBounds(570, 18, 80, 24);
        this.add(sBtn);

        model = new DefaultTableModel(new String[] { "ID", "Name", "Phone", "Email", "NID" }, 0);
        table = new JTable(model);
        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(230, 50, 480, 360);
        this.add(sp);

        addB.addActionListener(e -> {
            repo.addCustomer(
                    new Customer(idT.getText(), nameT.getText(), phT.getText(), emT.getText(), nidT.getText()));
            refresh("");
        });
        upB.addActionListener(e -> {
            repo.updateCustomer(
                    new Customer(idT.getText(), nameT.getText(), phT.getText(), emT.getText(), nidT.getText()));
            refresh("");
        });
        delB.addActionListener(e -> {
            repo.deleteCustomer(idT.getText());
            refresh("");
        });
        sBtn.addActionListener(e -> refresh(sTxt.getText()));
        back.addActionListener(e -> {
            new Home(currUser).setVisible(true);
            this.dispose();
        });

        table.getSelectionModel().addListSelectionListener(x -> {
            int r = table.getSelectedRow();
            if (r >= 0) {
                idT.setText(model.getValueAt(r, 0).toString());
                nameT.setText(model.getValueAt(r, 1).toString());
                phT.setText(model.getValueAt(r, 2).toString());
                emT.setText(model.getValueAt(r, 3).toString());
                nidT.setText(model.getValueAt(r, 4).toString());
            }
        });
        refresh("");
    }

    private void refresh(String k) {
        model.setRowCount(0);
        ArrayList<Customer> res = repo.searchCustomer(k);
        for (Customer c : res)
            model.addRow(
                    new Object[] { c.getCustomerId(), c.getName(), c.getPhone(), c.getEmail(), c.getNidOrPassport() });
    }
}