package frames;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import entity.TourPackage;
import entity.User;
import repository.PackageRepo;
import java.util.ArrayList;

public class ManagePackage extends JFrame {
    private JTextField idT, destT, prcT, durT, hIdT, tIdT;
    private JComboBox<String> catBox;
    private JTable table;
    private DefaultTableModel model;
    private PackageRepo repo = new PackageRepo();
    private User currUser;

    public ManagePackage(User user) {
        super("TourEase - Product Management");
        this.setSize(750, 440);
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
        JLabel l2 = new JLabel("Dest:");
        l2.setBounds(20, 50, 50, 20);
        this.add(l2);
        destT = new JTextField();
        destT.setBounds(80, 50, 120, 20);
        this.add(destT);
        JLabel l3 = new JLabel("Category:");
        l3.setBounds(20, 80, 70, 20);
        this.add(l3);
        catBox = new JComboBox<>(new String[] { "Domestic", "International" });
        catBox.setBounds(80, 80, 120, 20);
        this.add(catBox);
        JLabel l4 = new JLabel("Price:");
        l4.setBounds(20, 110, 50, 20);
        this.add(l4);
        prcT = new JTextField();
        prcT.setBounds(80, 110, 120, 20);
        this.add(prcT);
        JLabel l5 = new JLabel("Duration:");
        l5.setBounds(20, 140, 70, 20);
        this.add(l5);
        durT = new JTextField();
        durT.setBounds(80, 140, 120, 20);
        this.add(durT);
        JLabel l6 = new JLabel("Hotel ID:");
        l6.setBounds(20, 170, 70, 20);
        this.add(l6);
        hIdT = new JTextField();
        hIdT.setBounds(80, 170, 120, 20);
        this.add(hIdT);
        JLabel l7 = new JLabel("Trans ID:");
        l7.setBounds(20, 200, 70, 20);
        this.add(l7);
        tIdT = new JTextField();
        tIdT.setBounds(80, 200, 120, 20);
        this.add(tIdT);

        JButton addB = new JButton("Add Package");
        addB.setBounds(20, 240, 180, 30);
        this.add(addB);
        JButton back = new JButton("Back");
        back.setBounds(20, 340, 180, 30);
        this.add(back);

        model = new DefaultTableModel(new String[] { "ID", "Destination", "Category", "Price", "Duration" }, 0);
        table = new JTable(model);
        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(220, 20, 490, 350);
        this.add(sp);

        addB.addActionListener(e -> {
            repo.addPackage(new TourPackage(idT.getText(), destT.getText(), catBox.getSelectedItem().toString(),
                    Double.parseDouble(prcT.getText()), durT.getText(), hIdT.getText(), tIdT.getText()));
            refresh();
        });
        back.addActionListener(e -> {
            new Home(currUser).setVisible(true);
            this.dispose();
        });
        refresh();
    }

    private void refresh() {
        model.setRowCount(0);
        ArrayList<TourPackage> res = repo.getAllPackages();
        for (TourPackage p : res)
            model.addRow(new Object[] { p.getPackageId(), p.getDestination(), p.getCategory(), p.getPrice(),
                    p.getDuration() });
    }
}