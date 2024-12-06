package domain.item;

public class LineItem extends Item {
    private int quantity;

    public LineItem(String name, int unitPrice, int quantity) {
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

    @Override
    public String toString() {
        return getQuantity() + " * " + getName() + " = " + formatPrice(getTotalPrice());
    }


}