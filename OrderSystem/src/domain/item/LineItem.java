package domain.item;

public class LineItem {
    private String name;
    private int unitPrice;
    private int quantity;

    public LineItem(String name, int unitPrice, int quantity) {
        this.name = name;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public int getUnitPrice() {
        return unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getTotalPrice() {
        return unitPrice * quantity;
    }

    @Override
    public String toString() {
        return getQuantity() + " * " + getName() + " = " + formatPrice(getTotalPrice());
    }

    public String formatPrice(int priceInCent) {
        return String.format("%.2f EUR", priceInCent / 100.0);
    }
}
