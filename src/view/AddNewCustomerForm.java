package view;

import controller.Backend_DAO_List;
import model.Customer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddNewCustomerForm extends JFrame {
    private JButton jButtonOK;
    private JLabel jLabelID;
    private JLabel jLabelName;
    private JLabel jLabelAddress;
    private JTextField jTextFieldID;
    private JTextField jTextFieldName;
    private JTextField jTextFieldAddress;

    public AddNewCustomerForm() throws HeadlessException {
        jButtonOK = new JButton("OK");
        jLabelID = new JLabel("ID:");
        jLabelName = new JLabel("Name:");
        jLabelAddress = new JLabel("Address:");
        jTextFieldID = new JTextField();
        jTextFieldName = new JTextField();
        jTextFieldAddress = new JTextField();
        getContentPane().add(jLabelID);
        getContentPane().add(jTextFieldID);
        getContentPane().add(jLabelName);
        getContentPane().add(jTextFieldName);
        getContentPane().add(jLabelAddress);
        getContentPane().add(jTextFieldAddress);
        getContentPane().add(jButtonOK);
        this.setPreferredSize(new Dimension(400, 200));
        getContentPane().setLayout(new GridLayout(0, 2, 10, 10));
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.pack();
        jButtonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    long id = Long.parseLong(jTextFieldID.getText());
                    String name = jTextFieldName.getText();
                    String address = jTextFieldAddress.getText();

                    if (!isValidID(id)) {
                        throw new IllegalArgumentException("Invalid ID. Please enter a valid ID.");
                    }

                    if (name.trim().isEmpty()) {
                        throw new IllegalArgumentException("Name cannot be empty. Please enter a valid name.");
                    }

                    if (address.trim().isEmpty()) {
                        throw new IllegalArgumentException("Address cannot be empty. Please enter a valid address.");
                    }

                    Customer newCustomer = new Customer(id, name, address);

                    Backend_DAO_List.get().AddCustomer(newCustomer);

                    JOptionPane.showMessageDialog(null, "Customer added successfully");
                    System.out.println(Backend_DAO_List.get().getAllCustomers());
                } catch (Exception ee) {
                    ee.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error adding customer: " + ee.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private boolean isValidID(long id) {
        String idString = Long.toString(id);
        return idString.length() == 9;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new AddNewCustomerForm().setVisible(true);
            }
        });
    }
}
