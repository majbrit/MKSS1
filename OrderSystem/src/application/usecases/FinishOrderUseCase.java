package application.usecases;

import application.boundaries.IFinishOrderInput;
import application.boundaries.IFinishOrderOutput;
import domain.repositoryInterfaces.IOrderRepository;

public class FinishOrderUseCase implements IFinishOrderInput {
    private IOrderRepository orderRepository;
    private IFinishOrderOutput finishOrderOutput;

    public FinishOrderUseCase(IFinishOrderOutput finishOrderOutput, IOrderRepository orderRepository) {
        this.finishOrderOutput = finishOrderOutput;
        this.orderRepository = orderRepository;
    }
}
