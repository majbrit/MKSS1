package application.usecases;

import application.boundaries.IGetCheckoutDateTimeInput;
import application.boundaries.IGetCheckoutDateTimeOutput;
import domain.order.Order;
import domain.repositoryInterfaces.IOrderRepository;

import java.util.UUID;

public class GetCheckoutDateTimeUseCase implements IGetCheckoutDateTimeInput {
    private final IOrderRepository orderRepository;
    private final IGetCheckoutDateTimeOutput getCheckoutDateTimeOutput;

    public GetCheckoutDateTimeUseCase(IGetCheckoutDateTimeOutput getCheckoutDateTimeOutput, IOrderRepository orderRepository) {
        this.getCheckoutDateTimeOutput = getCheckoutDateTimeOutput;
        this.orderRepository = orderRepository;
    }

    @Override
    public void getCheckoutDateTime(UUID orderId) {
        Order order = orderRepository.findById(orderId);
        if (order != null) {
            String checkoutDateTime = order.checkoutDateTime();
            getCheckoutDateTimeOutput.onGetCheckoutDateTimeResult(checkoutDateTime);
        } else {
            getCheckoutDateTimeOutput.onGetCheckoutDateTimeResult("Order not found.");
        }
    }
}