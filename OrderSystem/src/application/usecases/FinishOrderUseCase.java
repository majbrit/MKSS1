package application.usecases;

import application.boundaries.IFinishOrderInput;
import application.boundaries.IFinishOrderOutput;
import domain.order.Order;
import domain.repositoryInterfaces.IOrderRepository;

import java.util.UUID;

public class FinishOrderUseCase implements IFinishOrderInput {
    private IOrderRepository orderRepository;
    private IFinishOrderOutput finishOrderOutput;

    public FinishOrderUseCase(IFinishOrderOutput finishOrderOutput, IOrderRepository orderRepository) {
        this.finishOrderOutput = finishOrderOutput;
        this.orderRepository = orderRepository;
    }
    @Override
    public void finishOrder(UUID id) {
        Order order = orderRepository.findById(id);

        if (order == null) {
            finishOrderOutput.onFinishOrderResult(false);
            return;
        }

        order.setCheckoutDateTime();
        boolean updated = orderRepository.update(id, order);

        finishOrderOutput.onFinishOrderResult(updated);
    }
}
