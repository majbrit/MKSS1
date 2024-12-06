package de.hs_bremen.mkss.domain.item;

import de.hs_bremen.mkss.domain.order.Order;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.*;

// Abstract class is useful here, as there are many common functions and properties that can be implemented here in summarised form

@Entity
public abstract class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    protected String name;
    protected int totalPrice;
    protected int unitPrice;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    public Item() {}

    public Item(String name, int unitPrice, Order order) {
        System.out.println("new item");
        this.name = name;
        this.unitPrice = unitPrice;
        this.order = order;
    }



    public String getName() {
        return name;
    }

    public int getUnitPrice() {
        return unitPrice;
    }

    public abstract int getTotalPrice();

    public String formatPrice(int priceInCent) {
        return String.format("%.2f EUR", priceInCent / 100.0);
    }

}