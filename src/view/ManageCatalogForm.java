package view;

import controller.Backend_DAO_List;
import model.Product;
import model.PurchaseOrder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.util.List;

public class ManageCatalogForm {
    private JButton add;
    private JButton delete;
    private JList list1;
    public JPanel panel2;
    public DefaultListModel AllProductsListModel;

    public ManageCatalogForm() {
        AllProductsListModel = new DefaultListModel();
        list1.setModel(AllProductsListModel);
        RefreshProductList();
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame();
                frame.setContentPane(new AddNewProductForm(ManageCatalogForm.this).panel3);
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
            }
        });

        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Product> selectedValuesList = list1.getSelectedValuesList();
                for (Product p : selectedValuesList) {
                    AllProductsListModel.removeElement(p);
                    try {
                        Backend_DAO_List.get().RemoveProduct(p);
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });
    }

    public void RefreshProductList() {
        try {
            AllProductsListModel.clear();
            for (Product p : Backend_DAO_List.get().getAllProducts()) {
                AllProductsListModel.addElement(p);
            }
        } catch (Exception ex) {
            ex.getMessage();
        }
    }


}
