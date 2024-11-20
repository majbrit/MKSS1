package application.usecases;

import application.boundaries.IGetAllOrdersInput;
import application.boundaries.IGetOrderSummaryInput;
import application.boundaries.IGetOrderSummaryOutput;
import domain.order.Order;
import domain.repositoryInterfaces.IOrderRepository;

import java.util.UUID;

public class GetOrderSummaryUseCase implements IGetOrderSummaryInput {
    private IOrderRepository orderRepository;
    private IGetOrderSummaryOutput getOrderSummaryOutput;

    public GetOrderSummaryUseCase(IGetOrderSummaryOutput getOrderSummaryOutput, IOrderRepository orderRepository) {
        this.getOrderSummaryOutput = getOrderSummaryOutput;
        this.orderRepository = orderRepository;
    }
    @Override
    public void getOrderSummary(UUID orderId) {
        Order order = orderRepository.findById(orderId);

        if (order == null) {
            getOrderSummaryOutput.onGetOrderSummaryResult("Order not found.");
            return;
        }

        String orderSummary = order.getSumString();
        getOrderSummaryOutput.onGetOrderSummaryResult(orderSummary);
    }
}
