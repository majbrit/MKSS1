package Repositories;

import Entities.Order;

import java.util.List;
import java.util.UUID;

public class OrderDatabaseRepository implements OrderRepository {
    @Override
    public Order create() {
        return null;
    }

    @Override
    public void update(Order order) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public Order get(int id) {
        return null;
    }

    @Override
    public List<Order> getList() {
        return List.of();
    }
}
