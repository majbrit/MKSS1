package de.hs_bremen.mkss.application.usecases;

import de.hs_bremen.mkss.application.boundaries.ICreateOrderOutput;
import de.hs_bremen.mkss.application.boundaries.ICreateOrderInput;
import de.hs_bremen.mkss.domain.item.Item;
import de.hs_bremen.mkss.domain.order.Order;
import de.hs_bremen.mkss.domain.repositoryInterfaces.IOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service("createOrderUseCase")
public class CreateOrderUseCase implements ICreateOrderInput {
    private IOrderRepository orderRepository;
    private ICreateOrderOutput createOrderOutput;

    @Autowired
    public CreateOrderUseCase(ICreateOrderOutput createOrderOutput, IOrderRepository orderRepository) {
        this.createOrderOutput = createOrderOutput;
        this.orderRepository = orderRepository;
    }


    @Override
    public void createOrder() {
        try {
            Order order = new Order();
            for (Item item : order.getItems()) {
                System.out.println("order items: " +item);
            }
            Order orderRepo = orderRepository.save(order);

            for (Item item : order.getItems()) {
                System.out.println("order items repo: " +item);
            }
            createOrderOutput.onCreateOrderResult(orderRepo);
        } catch (Exception e) {
            System.err.println("Error creating order: " + e.getMessage());
            createOrderOutput.onCreateOrderResult(null);
        }
    }
}
