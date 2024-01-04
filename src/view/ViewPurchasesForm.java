package view;

import controller.Backend_DAO_List;
import model.Customer;
import model.Product;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewPurchasesForm {
    private JComboBox customer;
    private JList list1;
    private JLabel total;
    private JLabel sum;
    public JPanel panel5;
    DefaultComboBoxModel CustomerModel;
    public DefaultListModel AllProductsListModel;

    public ViewPurchasesForm() {
        try {
            //מאתחל את רשימת הלקוחות
            CustomerModel = new DefaultComboBoxModel(Backend_DAO_List.get().getAllCustomers().values().toArray());
            customer.setModel(CustomerModel);
            customer.setSelectedIndex(0);
            //מציג את ההזמנות ללקוח מסוים
            AllProductsListModel=new DefaultListModel();
            list1.setModel(AllProductsListModel);
            //מכניס בפונקציה כל פעם מחדש את כל הרשימה
            RefreshProductList();
            System.out.println(list1.getModel());
            //סוכם את הסכום הכללי
            Product[] products = new Product[AllProductsListModel.size()];
            AllProductsListModel.copyInto(products);
            Float total = Backend_DAO_List.get().CalcProductsTotalCost(products);
            sum.setText(total.toString());
        }catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error Placing order", "Error",
                    JOptionPane.ERROR_MESSAGE);
            ex.getMessage();
        }
        customer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RefreshProductList();
            }
        });
    }
    //מכניס נתונים לרשימה
    public void RefreshProductList() {
        try {
            AllProductsListModel.clear();
            Customer cus= (Customer) customer.getSelectedItem();
            for (Product p : Backend_DAO_List.get().getCustomersOrders(cus)) {
                AllProductsListModel.addElement(p);
            }
        } catch (Exception ex) {
            ex.getMessage();
        }
    }

}
