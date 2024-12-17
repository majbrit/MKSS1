package de.hs_bremen.mkss.application.usecases;

import de.hs_bremen.mkss.application.boundaries.IGetAllOrdersInput;
import de.hs_bremen.mkss.domain.order.Order;
import de.hs_bremen.mkss.domain.repositoryInterfaces.IOrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("getAllOrdersUseCase")
@Transactional
public class GetAllOrdersUseCase implements IGetAllOrdersInput {
    private IOrderRepository orderRepository;

    @Autowired
    public GetAllOrdersUseCase(IOrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }
    @Override
    public List getAllOrders() {
        List<Order> orders = orderRepository.findAll();

        if (orders.isEmpty()) {
            return new ArrayList<>();
        } else {
            return orders;
        }
    }

}
