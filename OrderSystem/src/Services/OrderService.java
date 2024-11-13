package Services;

import Entities.Item;
import Entities.Order;

import java.util.List;

public class OrderService {
    private Order order;
    private ItemFactory itemFactory;
    private OrderRepository orderRepository;

    private static final class InstanceHolder {
        static final OrderService INSTANCE = new OrderService();
    }

    private OrderService() {
        this.orderRepository = new OrderRepository();
    }

    public static OrderService getInstance() {
        return InstanceHolder.INSTANCE;
    }

    public void setItemFactory(ItemFactory itemFactory){
        this.itemFactory = itemFactory;
    }

    public void newOrder() {
        this.order = new Order();
        orderRepository.createOrder(order);
    }

    public void addProduct(String name, int price, int quantity) {
        order.addProduct(itemFactory.createProduct(name, price, quantity));
        orderRepository.updateOrder(order);
    }

    public void addService(String name, int persons, int hours) {
        order.addService(itemFactory.createService(name, persons, hours));
        orderRepository.updateOrder(order);
    }

    public List<Item> getItems() {
        order.sortItems();
        return order.getItems();
    }


    public String getCheckoutDateTime(){
        return order.checkoutDateTime();
    }

    public void finishOrder() {
        order.setCheckoutDateTime();
        orderRepository.updateOrder(order);
    }

    public List<Order> getAllOrders() {
        return orderRepository.getAllOrders();
    }

    public void clearAllOrders() {
        orderRepository.deleteAllOrders();
    }

    public int getSum(){
        return order.getSum();
    }

    public String getSumString() {
        return order.getSumString();
    }


}