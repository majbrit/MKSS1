package Services;

import Entities.Item;
import Entities.Order;
import Events.OrderEvent;
import Repositories.OrderDatabaseRepository;
import Repositories.OrderInMemoryRepository;
import Repositories.OrderRepository;
import javafx.event.EventHandler;

import java.util.List;

public class OrderService {
    private Order order;
    private EventHandler<OrderEvent> eventHandler;
    private ItemFactory itemFactory;
    private OrderRepository orderRepository;

    private static final class InstanceHolder {
        static final OrderService INSTANCE = new OrderService();
    }

    private OrderService() {
        orderRepository = new OrderInMemoryRepository();
        //orderRepository = new OrderDatabaseRepository();
    }

    public static OrderService getInstance() {
        return InstanceHolder.INSTANCE;
    }

    public void setItemFactory(ItemFactory itemFactory) {
        this.itemFactory = itemFactory;
    }

    public void newOrder() {
        this.order = orderRepository.create();

        System.out.println("New Order (" + this.order.id + ")");
    }

    public void addProduct(String name, int price, int quantity) {
        System.out.println("Adding product " + name + " with price " + price);

        this.order.addProduct(itemFactory.createProduct(name, price, quantity));
        orderRepository.update(this.order);

        if (eventHandler != null) {
            eventHandler.handle(new OrderEvent());
        }
    }

    public void addService(String name, int persons, int hours) {
        order.addService(itemFactory.createService(name, persons, hours));
        orderRepository.update(this.order);

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

    public String checkoutDateTime() {
        order.setCheckoutDateTime();

        return order.checkoutDateTime();
    }

    public int getSum() {
        return order.getSum();
    }

    public void setHandler(EventHandler<OrderEvent> handler) {
        this.eventHandler = handler;
    }

    public void finishOrder() {
        orderRepository.update(this.order);

        System.out.println("Finish Order (" + this.order.id + ")");
    }
}