package view;

import controller.Backend_DAO_List;

import javax.swing.*;
import javax.swing.event.AncestorListener;
import java.awt.event.*;

public class StoreManagerGUIViewForm {


    private JButton addC;
    private JButton Order;
    private JButton seeOrder;
    private JButton product;
    private JPanel panel1;

    public StoreManagerGUIViewForm() {
        addC.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddNewCustomerForm().setVisible(true);
            }
        });
        product.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame();
                frame.setContentPane(new ManageCatalogForm().panel2);
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
            }
        });
        Order.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame();
                frame.setContentPane(new PlaceOrderForm().panel4);
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
            }
        });
        seeOrder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame();
                frame.setContentPane(new ViewPurchasesForm().panel5);
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
            }
        });
        panel1.addContainerListener(new ContainerAdapter() {
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("StoreManagerGUIViewForm");
        frame.setContentPane(new StoreManagerGUIViewForm().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                Backend_DAO_List.get().loadDataFromFile();
            }

            @Override
            public void windowClosing(WindowEvent e) {
                Backend_DAO_List.get().savaDataToFile();
            }
        });
        frame.pack();
        frame.setVisible(true);
    }
}
