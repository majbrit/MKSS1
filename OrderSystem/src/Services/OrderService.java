package Services;

import Entities.Item;
import Entities.Order;

import java.util.List;

public class OrderService {
    private Order order;
    private ItemFactory itemFactory;
    private IorderRepository orderRepository;

    private static final class InstanceHolder {
        static final OrderService INSTANCE = new OrderService();
    }



    public static OrderService getInstance() {
        return InstanceHolder.INSTANCE;
    }

    public void setItemFactory(ItemFactory itemFactory){
        this.itemFactory = itemFactory;
    }
    public void setOrderRepository(IorderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void newOrder() {
        this.order = new Order();
        orderRepository.createOrder(order);
    }

    public void addProduct(String name, int price, int quantity) {
        order.addProduct(itemFactory.createProduct(name, price, quantity));
        updateOrder();

    }

    public void addService(String name, int persons, int hours) {
        order.addService(itemFactory.createService(name, persons, hours));
        updateOrder();
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
        updateOrder();
    }

    public List<Order> getAllOrders() {
        return orderRepository.getAllOrders();
    }

    public void clearAllOrders() {
        orderRepository.deleteAllOrders();
    }

    private void updateOrder() {
        orderRepository.updateOrder(order);
    }

    public int getSum(){
        return order.getSum();
    }

    public String getSumString() {
        return order.getSumString();
    }


}