package view;

import controller.Backend_DAO_List;
import model.HardwareProduct;
import model.Product;
import model.SoftwareProduct;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddNewProductForm {
    public JPanel panel3;
    private JTextField Description;
    private JTextField lblOtherTxt;
    private JTextField PricePeriod;
    private JButton addButton;
    private JTextField NAME;
    private JComboBox cmbType;
    private JLabel pricePerUnit;
    private JLabel description;
    private JLabel name;
    private JLabel lblOther;
  private ManageCatalogForm manageCatalogForm;
    public AddNewProductForm(ManageCatalogForm catalogForm) {
        manageCatalogForm = catalogForm;
        DefaultComboBoxModel model = new DefaultComboBoxModel(Product.ProductType.values());
        cmbType.setModel(model);
        cmbType.setSelectedIndex(0);
        cmbType.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isInHardwareMode())
                    lblOther.setText("warrantyPeriod");
                else
                    lblOther.setText("numberOfUsers");
            }
        });
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Long id=12545L;
                    Product newProduct;
                    String name=NAME.getText();
                    String d=Description.getText();
                    float price= Float.parseFloat(PricePeriod.getText());
                    int ibi=Integer.parseInt(lblOtherTxt.getText());
                    // בדיקת תקינות עבור שם
                    if (name.trim().isEmpty()) {
                        throw new IllegalArgumentException("Name cannot be empty. Please enter a valid name.");
                    }
                    // בדיקת תקינות עבור תאור
                    if (d.trim().isEmpty()) {
                        throw new IllegalArgumentException("Description cannot be empty. Please enter a valid Description.");
                    }
                    // בדיקת תקינות עבור מחיר
                    if (price<0) {
                        throw new IllegalArgumentException("price cannot be ziro. Please enter a valid price.");
                    }
                    // בדיקת תקינות עבור Ibi
                    if (ibi<0) {
                        throw new IllegalArgumentException("ibi cannot be zero. Please enter a valid ibi.");
                    }

                    if (isInHardwareMode()) {
                        newProduct = new HardwareProduct(id,name,d,price,ibi);
                    } else {
                        newProduct= new SoftwareProduct(id,name,d,price,ibi);
                    }
                    Backend_DAO_List.get().AddProduct((Product) newProduct);
                    System.out.println(Backend_DAO_List.get().getAllProducts());

                    manageCatalogForm.RefreshProductList();
                        JOptionPane.showMessageDialog(null, "Product added successfully");
                    } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error adding prudoct: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    private boolean isInHardwareMode() {
        var x = cmbType.getSelectedItem();
        if (x.equals("HARDWARE"))
            return true;
        return false;
    }
}

