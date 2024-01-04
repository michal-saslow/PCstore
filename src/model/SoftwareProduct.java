package model;

import java.io.Serializable;

public class SoftwareProduct extends Product implements Serializable {
    private int numberOfUsers;

    public int getNumberOfUsers() {
        return numberOfUsers;
    }

    public void setNumberOfUsers(int numberOfUsers) {
        this.numberOfUsers = numberOfUsers;
    }

    public SoftwareProduct(long id, String name, String description, float pricePerUnit, int numberOfUsers) {
        super(id, name, description, pricePerUnit);
        this.numberOfUsers = numberOfUsers;
    }

    public float getPrice() {
        return this.numberOfUsers + super.getPricePerUnit();
    }
}
