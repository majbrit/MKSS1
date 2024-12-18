package de.hs_bremen.mkss.domain.order;

import de.hs_bremen.mkss.common.PriceFormatter;
import de.hs_bremen.mkss.domain.item.Item;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import java.util.ArrayList;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.*;

//for newer spring boot versions use jakarta
import jakarta.persistence.*;


//use orders instead of order in database, because order is a reserved keyword
@Entity
@Table(name = "orders")
public class Order {

    // primary key
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id")
    @OrderBy("totalPrice")
    private List<Item> items;

    private String customerName;
    //to store date correct
    @Temporal(TemporalType.TIMESTAMP)
    private Date checkoutDateTime;

    @Enumerated(EnumType.STRING)
    private OrderStatus status = OrderStatus.EMPTY;

    @Transient
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

    public enum OrderStatus {
        EMPTY,
        IN_PREPARATION,
        COMMITTED,
        ACCEPTED,
        REJECTED
    }

    public Order() {
        items = new ArrayList<>();
    }

    public void addProduct(Item Item) {
        if (this.status == OrderStatus.COMMITTED) {
            throw new IllegalStateException("Cannot modify a committed order.");
        }

        items.add(Item);
        updateStatus();
    }
    public void removeItem(Item item) {
        if (this.status == OrderStatus.COMMITTED) {
            throw new IllegalStateException("Cannot modify a committed order.");
        }

        this.items.remove(item);
        updateStatus();
    }

    private void updateStatus() {
        if (this.items.isEmpty()) {
            this.status = OrderStatus.EMPTY;
        } else {
            this.status = OrderStatus.IN_PREPARATION;
        }
    }


    public List<Item> getItems() {
        return items;
    }

    public void sortItems() {
        items.sort(Comparator.comparingInt(Item::getTotalPrice));
    }

    public void setCheckoutDateTime() {
        checkoutDateTime = new Date();
    }

    public Date getCheckoutDateTime() {
        return checkoutDateTime;
    }

    public String checkoutDateTime() {
        return simpleDateFormat.format(getCheckoutDateTime());
    }

    public int getSum() {
        int sum = 0;
        for (Item item : items) {
            if (item != null) {
                sum += item.getTotalPrice();
            }
        }
        return sum;
    }

    public String getSumString() {
        int sum = getSum();
        return PriceFormatter.formatPrice(sum);
    }
    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public boolean deleteItem(Long itemId) {
        var item = items.stream()
                .filter(x -> x.getId().equals(itemId))
                .findFirst();

        if (item.isEmpty()) {
            return false;
        }

        items.remove(item.get());

        return !items.contains(item.get());
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
}