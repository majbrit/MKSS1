package de.hs_bremen.mkss.application.usecases;

import de.hs_bremen.mkss.application.boundaries.IClearOrdersOutput;
import de.hs_bremen.mkss.application.boundaries.ICreadeOrderOutput;
import de.hs_bremen.mkss.application.boundaries.ICreateOrderInput;
import de.hs_bremen.mkss.domain.order.Order;
import de.hs_bremen.mkss.domain.repositoryInterfaces.IOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CreateOrderUseCase implements ICreateOrderInput {
    private IOrderRepository orderRepository;
    private ICreadeOrderOutput createOrderOutput;

    @Autowired
    public CreateOrderUseCase(@Qualifier("guiMenu")ICreadeOrderOutput createOrderOutput, IOrderRepository orderRepository) {
        this.createOrderOutput = createOrderOutput;
        this.orderRepository = orderRepository;
    }


    @Override
    public void createOrder() {
        UUID id = orderRepository.save(new Order());
        createOrderOutput.onCreateOrderResult(id);
    }
}
