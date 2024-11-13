package Services;

import Entities.Order;

import java.util.List;

public interface IorderRepository {
    void createOrder(Order order);
    void updateOrder(Order order);
    List<Order> getAllOrders();
    void deleteAllOrders();
}
