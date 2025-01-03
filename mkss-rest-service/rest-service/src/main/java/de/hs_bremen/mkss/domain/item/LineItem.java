package de.hs_bremen.mkss.domain.item;

import de.hs_bremen.mkss.domain.order.Order;
import jakarta.persistence.*;

//entity managed by JPA
@Entity
public class LineItem extends Item {

    private int quantity;

    public LineItem() {
        super("", 0, null);
    }

    public LineItem(String name, int unitPrice, int quantity, Order order) {
        super(name, unitPrice, order);
        this.quantity = quantity;
        this.totalPrice = unitPrice * quantity;
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