package Services;

import Entities.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderRepository implements IorderRepository {
    private final List<Order> orders;

    public OrderRepository() {
        this.orders = new ArrayList<>();
    }

    @Override
    public void createOrder(Order order) {
        orders.add(order);
    }

    @Override
    public void updateOrder(Order order) {
        int index = orders.indexOf(order);
        if (index != -1) {

            orders.set(index, order);
        }
    }

    @Override
    public List<Order> getAllOrders() {
        return new ArrayList<>(orders);
    }

    @Override
    public void deleteAllOrders() {
        orders.clear();
    }
}
