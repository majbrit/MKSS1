package application.usecases;

import application.boundaries.IGetAllOrdersInput;
import application.boundaries.IGetAllOrdersOutput;
import application.boundaries.IGetOrderSummaryOutput;
import domain.repositoryInterfaces.IOrderRepository;

public class GetAllOrdersUseCase implements IGetAllOrdersInput {
    private IOrderRepository orderRepository;
    private IGetAllOrdersOutput getAllOrdersOutput;

    public GetAllOrdersUseCase(IGetAllOrdersOutput getAllOrdersOutput, IOrderRepository orderRepository) {
        this.getAllOrdersOutput = getAllOrdersOutput;
        this.orderRepository = orderRepository;
    }

}
