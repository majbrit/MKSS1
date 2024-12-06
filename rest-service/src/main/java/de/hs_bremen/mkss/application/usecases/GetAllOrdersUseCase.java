package de.hs_bremen.mkss.application.usecases;

import de.hs_bremen.mkss.application.boundaries.IGetAllOrdersInput;
import de.hs_bremen.mkss.application.boundaries.IGetAllOrdersOutput;
import de.hs_bremen.mkss.domain.order.Order;
import de.hs_bremen.mkss.domain.repositoryInterfaces.IOrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("getAllOrdersUseCase")
@Transactional
public class GetAllOrdersUseCase implements IGetAllOrdersInput {
    private IOrderRepository orderRepository;
    private IGetAllOrdersOutput getAllOrdersOutput;

    @Autowired
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
