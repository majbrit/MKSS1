package application.boundaries;

import domain.order.Order;

import java.util.UUID;

public interface ICreadeOrderOutput {
    public void onCreateOrderResult(UUID orderId);
}
