package view;

import controller.Backend_DAO_List;
import model.Customer;
import model.Product;
import model.PurchaseOrder;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PlaceOrderForm {
    private JComboBox customerComBox;
    private JComboBox productComboBox;
    private JButton addToOrder;
    private JList list;
    private JLabel customer;
    private JLabel product;
    public JPanel panel4;
    private JButton remove;
    private JButton orderSubmit;
    private JButton calculateTotal;
    private JLabel sum;
    DefaultListModel SelectedProductsListModel = new DefaultListModel();
    DefaultComboBoxModel CustomerModel;
    DefaultComboBoxModel ProductModel;

    public PlaceOrderForm() {
        try {
            CustomerModel = new DefaultComboBoxModel(Backend_DAO_List.get().getAllCustomers().values().toArray());
            customerComBox.setModel(CustomerModel);
            customerComBox.setSelectedIndex(0);
            ProductModel = new DefaultComboBoxModel(Backend_DAO_List.get().getAllProducts().toArray());
            productComboBox.setModel(ProductModel);
            productComboBox.setSelectedIndex(0);
            list.setModel(SelectedProductsListModel);


        } catch (Exception ex) {
            ex.getMessage();
        }
        addToOrder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SelectedProductsListModel.addElement((Product) productComboBox.getSelectedItem());
            }
        });
        remove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (Object objProduct : list.getSelectedValues()) {
                    SelectedProductsListModel.removeElement(objProduct);
                }

            }
        });
        orderSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    var customer = customerComBox.getSelectedItem();
                    ArrayList<Product> productsList = new ArrayList<>();
                    for (Object p : SelectedProductsListModel.toArray()) {
                        productsList.add((Product) p);
                    }
                    PurchaseOrder newOrder = new PurchaseOrder((Customer) customer, productsList);
                    Backend_DAO_List.get().PlaceOrder(newOrder);
                    JOptionPane.showMessageDialog(null, "Orders added successfully");
                    System.out.println(Backend_DAO_List.get().getPurchaseOrders());
                    SelectedProductsListModel.clear();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error Placing order", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    ex.getMessage();
                }
            }
        });
        calculateTotal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Product[] products = new Product[SelectedProductsListModel.size()];
                    SelectedProductsListModel.copyInto(products);
                    Float total = Backend_DAO_List.get().CalcProductsTotalCost(products);
                    sum.setText(total.toString());
                } catch (Exception ex) {
                    ex.getMessage();
                }
            }
        });
    }
}
