package Entities;

public class Product extends Item {
    private int quantity;

    public Product(String name, int unitPrice, int quantity) {
        super(name, unitPrice);
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public int getTotalPrice() {
        return unitPrice * getQuantity();
    }

    public String getDescription() {
        return getQuantity() + " * " + getName() + " = " + formatPrice(getTotalPrice());
    }

    public String toString() {
        return getQuantity() + " * " + getName() + " = " + formatPrice(getTotalPrice());
    }


}