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


    //to store date correct
    @Temporal(TemporalType.TIMESTAMP)
    private Date checkoutDateTime;

    @Transient
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

    public Order() {
        items = new ArrayList<>();
    }

    public void addProduct(Item product) {
        items.add(product);
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

    public Long getId() {
        return id;
    }
}