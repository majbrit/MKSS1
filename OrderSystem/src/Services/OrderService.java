package Services;

import Entities.Item;
import Entities.Order;
import Events.OrderEvent;
import javafx.event.EventHandler;

import java.util.List;

public class OrderService {
    private Order order;
    private EventHandler<OrderEvent> eventHandler;
    private IItemFactory itemFactory;

    private static final class InstanceHolder {
        static final OrderService INSTANCE = new OrderService();
    }

    private OrderService() {

    }

    public static OrderService getInstance() {
        return InstanceHolder.INSTANCE;
    }

    public void setItemFactory(IItemFactory itemFactory){
        this.itemFactory = itemFactory;
    }

    public void newOrder() {
        this.order = new Order();
        System.out.println("New Order");
    }

    public void addProduct(String name, int price, int quantity) {
        System.out.println("Adding product " + name + " with price " + price);
        order.addProduct(itemFactory.createProduct(name, price, quantity));
        if (eventHandler != null) {
            eventHandler.handle(new OrderEvent());
        }
    }

    public void addService(String name, int persons, int hours) {
        order.addService(itemFactory.createService(name, persons, hours));
        if (eventHandler != null) {
            eventHandler.handle(new OrderEvent());
        }
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

    public void setHandler(EventHandler<OrderEvent> handler){
        this.eventHandler = handler;
    }
}