package application;

import domain.repositoryInterfaces.IOrderRepository;
import domain.item.Item;
import domain.factory.ItemFactory;
import domain.order.Order;

import java.util.List;
import java.util.UUID;

public class OrderService {
    private Order order;
    private ItemFactory itemFactory;
    private IOrderRepository orderRepository;

    private static final class InstanceHolder {
        static final OrderService INSTANCE = new OrderService();
    }



    public static OrderService getInstance() {
        return InstanceHolder.INSTANCE;
    }

    public void setItemFactory(ItemFactory itemFactory){
        this.itemFactory = itemFactory;
    }
    public void setOrderRepository(IOrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public UUID newOrder() {
        this.order = new Order();
        return orderRepository.save(order);
    }

    public void addProduct(UUID id, String name, int price, int quantity) {
        order.addProduct(itemFactory.createProduct(name, price, quantity));
        updateOrder(id, order);

    }

    public void addService(UUID id, String name, int persons, int hours) {
        order.addService(itemFactory.createService(name, persons, hours));
        updateOrder(id, order);
    }

    public List<Item> getItems() {
        order.sortItems();
        return order.getItems();
    }


    public String getCheckoutDateTime(){
        return order.checkoutDateTime();
    }

    public void finishOrder(UUID id) {
        order.setCheckoutDateTime();
        updateOrder(id, order);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public void clearAllOrders() {
        orderRepository.deleteAll();
    }

    private void updateOrder(UUID id, Order order) {
        orderRepository.update(id, order);
    }

    public int getSum(){
        return order.getSum();
    }

    public String getSumString() {
        return order.getSumString();
    }


}