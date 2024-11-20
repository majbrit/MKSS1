package application.usecases;

import application.boundaries.IGetAllItemsOutput;
import application.boundaries.IGetAllItemsinput;
import application.boundaries.IGetAllOrdersInput;
import domain.order.Order;
import domain.repositoryInterfaces.IOrderRepository;

import java.util.ArrayList;
import java.util.UUID;

public class GetAllItemsUseCase implements IGetAllItemsinput {
    private final IOrderRepository orderRepository;
    private final IGetAllItemsOutput getAllItemsOutput;

    public GetAllItemsUseCase(IGetAllItemsOutput getAllItemsOutput, IOrderRepository orderRepository) {
        this.getAllItemsOutput = getAllItemsOutput;
        this.orderRepository = orderRepository;
    }

    @Override
    public void getAllItems(UUID orderId) {
        Order order = orderRepository.findById(orderId);
        if (order != null) {
            order.sortItems();
            getAllItemsOutput.onGetAllItemsResult(order.getItems());
        } else {
            getAllItemsOutput.onGetAllItemsResult(new ArrayList<>());
        }
    }
}
