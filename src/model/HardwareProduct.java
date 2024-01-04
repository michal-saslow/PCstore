package model;

import java.io.Serializable;

public class HardwareProduct extends Product implements Serializable {
    private int warrantyPeriod;

    public int getWarrantyPeriod() {
        return warrantyPeriod;
    }

    public void setWarrantyPeriod(int warrantyPeriod) {
        this.warrantyPeriod = warrantyPeriod;
    }

    public HardwareProduct(long id, String name, String description, float pricePerUnit, int warrantyPeriod) {
        super(id, name, description, pricePerUnit);
        this.warrantyPeriod = warrantyPeriod;
    }

    public float getPrice(){
        return this.warrantyPeriod+super.getPricePerUnit();
    }
}
