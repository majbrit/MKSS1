package model;

import java.text.SimpleDateFormat;
import java.util.*;


public class Order {
    // List e.g. Array List for storing products and services
    private List<Item> items;
    private Date checkoutDateTime;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

    public Order() {
        items = new ArrayList<Item>();
    }

    public void addItem(Item item) {
        this.items.add(item);
    }

    public List<Item> getItems() {
        return items;
    }

    // Sorting: Collection.sort() or List.sort()
    public void sortItemsByPrice() {
        Collections.sort(this.items);
    }

    public void setCheckoutDateTime() {
        checkoutDateTime = new Date();
    }

    public Date getCheckoutDateTime() {
        return checkoutDateTime;
    }

    public void printCheckoutDateTime() {
        System.out.println("Checkout at " + simpleDateFormat.format(checkoutDateTime));
    }

    public void printSum() {
        int sum = getSum();
        System.out.println("Sum: "+ formatPrice(sum));
    }

    public int getSum() {
        int sum = 0;
        for (Item item : items) {
            if (item != null) {
                sum += item.getPrice();
            }
        }
        return sum;
    }

    public void printItems() {
        for (Item item : items) {
            System.out.println(item.getDescription() + " = " + formatPrice(item.getPrice()));
        }
    }

    private String formatPrice(int priceInCent) {
        return String.format("%.2f EUR", priceInCent / 100.0);
    }
}
