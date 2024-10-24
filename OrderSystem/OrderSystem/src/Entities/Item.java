package Entities;

// Abstract class is useful here, as there are many common functions and properties that can be implemented here in summarised form
public abstract class Item {
    protected String name;
    protected int totalPrice;
    protected int unitPrice;

    public Item(String name, int unitPrice) {
        this.name = name;
        this.unitPrice = unitPrice;
    }

    public String getName() {
        return name;
    }

    public int getUnitPrice() {
        return unitPrice;
    }

    public abstract String getDescription();

    public abstract int getTotalPrice();

}
