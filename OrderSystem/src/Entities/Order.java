package Entities;

import java.util.ArrayList;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.*;

public class Order {
    private final List<Item> items;
    private Date checkoutDateTime;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

    public Order() {
        this.items = new ArrayList<>();
    }

    public void addProduct(Item product) {
        items.add(product);
    }

    public void addService(Item service) {
        items.add(service);
    }

    public List<Item> getItems() {
        return items;
    }

    public void sortItems() {
        items.sort((item1, item2) -> Integer.compare(item1.getTotalPrice(), item2.getTotalPrice()));
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
        return formatPrice(sum);
    }

    private String formatPrice(int priceInCent) {
        return String.format("%.2f EUR", priceInCent / 100.0);
    }
}