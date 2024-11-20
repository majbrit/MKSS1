package application.usecases;

import application.boundaries.IGetAllOrdersInput;
import application.boundaries.IGetOrderSummaryInput;
import application.boundaries.IGetOrderSummaryOutput;
import domain.item.Item;
import domain.order.Order;
import domain.repositoryInterfaces.IOrderRepository;

import java.util.ArrayList;
import java.util.List;
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
        //order.setCheckoutDateTime();

        if (order == null) {
            getOrderSummaryOutput.onGetOrderSummaryResult(new ArrayList<>(),"Order not found.","");
            return;
        }

        String sum = order.getSumString();
        String checkOut = order.checkoutDateTime();
        List<Item> items = order.getItems();
        getOrderSummaryOutput.onGetOrderSummaryResult(items ,sum, checkOut);
    }
}
