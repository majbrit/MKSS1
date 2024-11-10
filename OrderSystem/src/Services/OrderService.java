package Services;

import Entities.Item;
import Entities.Order;

import java.util.List;

public class OrderService {
    private Order order;
    private EventHandler<OrderEvent> eventHandler;
    private ItemFactory itemFactory;


    private static final class InstanceHolder {
        static final OrderService INSTANCE = new OrderService();
    }

    private OrderService() {
    }

    public static OrderService getInstance() {
        return InstanceHolder.INSTANCE;
    }

    public void setItemFactory(ItemFactory itemFactory){
        this.itemFactory = itemFactory;
    }

    public void newOrder() {
        this.order = new Order();
    }

    public void addProduct(String name, int price, int quantity) {
        System.out.println("Adding product " + name + " with price " + price);
        order.addProduct(itemFactory.createProduct(name, price, quantity));

    }

    public void addService(String name, int persons, int hours) {
        order.addService(itemFactory.createService(name, persons, hours));

    }

    public List<Item> getItems() {
        System.out.println("Getting items");
        order.sortItems();
        System.out.println(order.getItems());
        return order.getItems();
    }

    public String checkoutDateTime(){
        order.setCheckoutDateTime();
        return order.checkoutDateTime();
    }

    public int getSum(){
        return order.getSum();
    }

    public String getSumString() {
        return order.getSumString();
    }


}