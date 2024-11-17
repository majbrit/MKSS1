package application.usecases;

import application.boundaries.IGetAllOrdersInput;
import application.boundaries.IGetOrderSummaryInput;
import application.boundaries.IGetOrderSummaryOutput;
import domain.repositoryInterfaces.IOrderRepository;

public class GetOrderSummaryUseCase implements IGetOrderSummaryInput {
    private IOrderRepository orderRepository;
    private IGetOrderSummaryOutput getOrderSummaryOutput;

    public GetOrderSummaryUseCase(IGetOrderSummaryOutput getOrderSummaryOutput, IOrderRepository orderRepository) {
        this.getOrderSummaryOutput = getOrderSummaryOutput;
        this.orderRepository = orderRepository;
    }
}
