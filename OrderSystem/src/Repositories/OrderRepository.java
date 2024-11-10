package Repositories;

import Entities.Order;

import java.util.List;
import java.util.UUID;

public interface OrderRepository {
    Order create();

    void update(Order order);

    void delete(int id);

    Order get(int id);

    List<Order> getList();
}
