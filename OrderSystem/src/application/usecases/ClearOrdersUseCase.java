package application.usecases;

import application.boundaries.IClearOrdersInput;
import application.boundaries.IClearOrdersOutput;
import domain.order.Order;
import domain.repositoryInterfaces.IOrderRepository;

import java.util.UUID;

public class ClearOrdersUseCase implements IClearOrdersInput {
    private IOrderRepository orderRepository;
    private IClearOrdersOutput clearOrdersOutput;

    public ClearOrdersUseCase(IClearOrdersOutput clearOrdersOutput, IOrderRepository orderRepository) {
        this.clearOrdersOutput = clearOrdersOutput;
        this.orderRepository = orderRepository;
    }


    @Override
    public void clearOrders() {
        try {
            orderRepository.deleteAll();
            clearOrdersOutput.onClearOrdersResult(true);
        } catch (Exception e) {
            clearOrdersOutput.onClearOrdersResult(false);
        }
    }

}
