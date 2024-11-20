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
    public void finishOrder(UUID orderId) {
        Order order = orderRepository.findById(orderId);
        if (order != null) {

            order.setCheckoutDateTime();

            orderRepository.update(orderId, order);


            finishOrderOutput.onFinishOrderResult(true, order);
        } else {
            finishOrderOutput.onFinishOrderResult(false, null);
        }
    }
}
