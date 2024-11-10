package Repositories;

import Entities.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class OrderInMemoryRepository implements OrderRepository {
    private final List<Order> orders;

    public OrderInMemoryRepository() {
        this.orders = new ArrayList<>();
    }

    @Override
    public Order create() {
        var order = new Order();
        order.id = getId();

        orders.add(order);

        return order;
    }

    @Override
    public void update(Order order) {
        var o = get(order.id);
        if (o == null) return;

        this.orders.set(this.orders.indexOf(o), order);
    }

    @Override
    public void delete(int id) {
        var o = get(id);
        if (o == null) return;

        this.orders.remove(o);
    }

    @Override
    public Order get(int id) {
        var order = this.orders.stream().filter(o -> o.id == id).findFirst().orElse(null);

        return order;
    }

    @Override
    public List<Order> getList() {
        return this.orders;
    }

    private int getId() {
        //return UUID.randomUUID();
        return this.orders.size() + 1;
    }
}
