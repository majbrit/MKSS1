package interfaceAdapters.gateway;

import domain.repositoryInterfaces.IOrderRepository;
import domain.order.Order;

import java.util.*;

public class OrderRepository implements IOrderRepository {
    // persistence oriented repository
    private Map<UUID, Order> orders = new HashMap<>();

    @Override
    public UUID save(Order order) {
        UUID orderId = UUID.randomUUID();
        orders.put(orderId, order);
        return orderId;
    }

    @Override
    public boolean update(UUID orderId, Order order) {
        if (orders.containsKey(orderId)) {
            orders.put(orderId, order);
            return true;
        }
        return false;
    }

    @Override
    public Order findById(UUID orderId) {
        return Optional.ofNullable(orders.get(orderId)).orElse(null);
    }

    @Override
    public boolean delete(UUID orderId) {
        return orders.remove(orderId) != null;
    }

    @Override
    public void deleteAll() {
        orders.clear();
    }

    @Override
    public List<Order> findAll() {
        return new ArrayList<>(orders.values());
    }

}
