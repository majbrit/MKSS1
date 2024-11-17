package application.usecases;

import application.boundaries.IClearOrdersOutput;
import application.boundaries.ICreadeOrderOutput;
import application.boundaries.ICreateOrderInput;
import domain.order.Order;
import domain.repositoryInterfaces.IOrderRepository;

import java.util.UUID;

public class CreateOrderUseCase implements ICreateOrderInput {
    private IOrderRepository orderRepository;
    private ICreadeOrderOutput createOrderOutput;

    public CreateOrderUseCase(ICreadeOrderOutput createOrderOutput,IOrderRepository orderRepository) {
        this.createOrderOutput = createOrderOutput;
        this.orderRepository = orderRepository;
    }


    @Override
    public void createOrder() {
        UUID id = orderRepository.save(new Order());
        createOrderOutput.onCreateOrderResult(id);
    }
}
