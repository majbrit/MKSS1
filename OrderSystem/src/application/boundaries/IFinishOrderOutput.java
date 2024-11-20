package application.boundaries;

import domain.order.Order;

public interface IFinishOrderOutput {
    void onFinishOrderResult(boolean success, Order finishedOrder);
}
