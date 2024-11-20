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
            // Set the checkout date/time
            order.setCheckoutDateTime();
            // Optionally, update the order in the repository
            orderRepository.update(orderId, order);

            // Notify the output boundary with the finished order
            finishOrderOutput.onFinishOrderResult(true, order);
        } else {
            finishOrderOutput.onFinishOrderResult(false, null); // If the order is not found
        }
    }
}
