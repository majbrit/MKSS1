package de.hs_bremen.mkss.interfaceAdapters.gateway;

import de.hs_bremen.mkss.domain.repositoryInterfaces.IOrderRepository;
import de.hs_bremen.mkss.domain.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.*;
/*

public class OrderRepository implements IOrderRepository {
    // persistence oriented repository
    private Map<Long, Order> orders = new HashMap<>();

    @Override
    publicLong save(Order order) {
       Long orderId =Long.randomLong();
        orders.put(orderId, order);
        return orderId;
    }

    @Override
    public boolean update(Long orderId, Order order) {
        if (orders.containsKey(orderId)) {
            orders.put(orderId, order);
            return true;
        }
        return false;
    }

    @Override
    public Order findById(Long orderId) {
        return Optional.ofNullable(orders.get(orderId)).orElse(null);
    }

    @Override
    public boolean delete(Long orderId) {
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
*/