package controller;

import model.Customer;
import model.HardwareProduct;
import model.Product;
import model.PurchaseOrder;

import javax.swing.*;
import java.io.*;
import java.util.*;

public class Backend_DAO_List implements Backend {
    private Map<Long, Customer> _Customers;
    private Set<Product> _Products;
    private List<PurchaseOrder> _PurchaseOrders;
    private static Backend_DAO_List b;

    private Backend_DAO_List() {
        _Customers = new HashMap<>();
        _Products = new HashSet<>();
        _PurchaseOrders = new ArrayList<>();
    }

    public List<PurchaseOrder> getPurchaseOrders() {
        return _PurchaseOrders;
    }

    public static Backend_DAO_List get() {
        if (Objects.isNull(b))
            b = new Backend_DAO_List();
        return b;
    }

    public void AddCustomer(Customer c) throws Exception {
        _Customers.put(c.getId(), c);
    }

    public void AddProduct(Product p) throws Exception {
        _Products.add(p);
    }

    public void PlaceOrder(PurchaseOrder p) throws Exception {
        _PurchaseOrders.add(p);
    }

    public HashMap<Long, Customer> getAllCustomers() throws Exception {
        return (HashMap<Long, Customer>) _Customers;
    }

    public HashSet<Product> getAllProducts() throws Exception {
        return (HashSet<Product>) _Products;
    }

    public ArrayList<Product> getCustomersOrders(Customer c) throws Exception {
        ArrayList<Product> ret = new ArrayList<Product>();
        for (PurchaseOrder p : _PurchaseOrders) {
            if (p.getOrderingCustomer().equals(c))
                ret.addAll(p.getProductsList());
        }
        return ret;
    }

    public Float CalcProductsTotalCost(Product[] products) throws Exception {
        float sum = 0;
        for (Product p : products) {
            sum += p.getPrice();
        }
        return sum;
    }

    public void RemoveProduct(Product p) throws Exception {
        _Products.remove(p);
    }

    public void savaDataToFile() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("data"));
            oos.writeObject(_Customers);
            oos.writeObject(_Products);
            oos.writeObject(_PurchaseOrders);
            oos.close();
        } catch (Exception ex) {
            ex.getMessage();
        }
    }

    public void loadDataFromFile() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("data"));
            _Customers = (Map<Long, Customer>) ois.readObject();
            _Products = (Set<Product>) ois.readObject();
            _PurchaseOrders = (List<PurchaseOrder>) ois.readObject();
            ois.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "the page nut found ", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
