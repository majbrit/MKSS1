package application.boundaries;

import domain.order.Order;

import java.util.List;

public interface IGetAllOrdersOutput {
    public void onGetAllOrdersResult(List<Order> orders);
}
