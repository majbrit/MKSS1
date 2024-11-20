package application.usecases;

import application.boundaries.IGetAllOrdersInput;
import application.boundaries.IGetAllOrdersOutput;
import domain.order.Order;
import domain.repositoryInterfaces.IOrderRepository;

import java.util.ArrayList;
import java.util.List;

public class GetAllOrdersUseCase implements IGetAllOrdersInput {
    private IOrderRepository orderRepository;
    private IGetAllOrdersOutput getAllOrdersOutput;

    public GetAllOrdersUseCase(IGetAllOrdersOutput getAllOrdersOutput, IOrderRepository orderRepository) {
        this.getAllOrdersOutput = getAllOrdersOutput;
        this.orderRepository = orderRepository;
    }
    @Override
    public void getAllOrders() {
        List<Order> orders = orderRepository.findAll();

        if (orders.isEmpty()) {
            getAllOrdersOutput.onGetAllOrdersResult(new ArrayList<>());
        } else {
            getAllOrdersOutput.onGetAllOrdersResult(orders);
        }
    }

}
