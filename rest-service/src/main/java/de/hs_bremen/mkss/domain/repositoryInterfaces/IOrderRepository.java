package de.hs_bremen.mkss.domain.repositoryInterfaces;

import de.hs_bremen.mkss.domain.order.Order;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

public interface IOrderRepository {
    public UUID save(Order order);
    public boolean update(UUID orderId, Order order);
    public Order findById(UUID orderId);
    public boolean delete(UUID orderId) ;
    public void deleteAll();
    public List<Order> findAll();
}
