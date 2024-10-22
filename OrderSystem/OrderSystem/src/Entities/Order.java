package Entities;

import java.util.ArrayList;
import java.util.List;

public class Order {

    private List<Item> items;

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
        items.sort((item1, item2) -> Integer.compare(item1.getPrice(), item2.getPrice()));
    }
}
