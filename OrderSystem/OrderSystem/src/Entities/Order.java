package Entities;

import java.util.ArrayList;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.*;

public class Order {

    private List<Item> items;
    private Date checkoutDateTime;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

    public Order() {
        this.items = new ArrayList<>();
    }

    public void addProduct(Product product) {
        items.add(product);
    }

    public void addService(Service service) {
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

    public void printItems() {
        sortItems();
        for (Item item : items) {
            System.out.println(item.getDescription() + " = " + formatPrice(item.getTotalPrice()));
        }
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

    public void printSum() {
        System.out.println("Sum: " + formatPrice(getSum()));
    }

    public void printCheckoutDateTime() {
        System.out.println("Checkout at " + simpleDateFormat.format(getCheckoutDateTime()));
    }

    // Shorter and simpler conversion from cent to euro
    private String formatPrice(int priceInCent) {
        return String.format("%.2f EUR", priceInCent / 100.0);
    }
}

