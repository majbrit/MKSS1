package de.hs_bremen.mkss.application.usecases;

import de.hs_bremen.mkss.application.boundaries.IGetAllOrdersInput;
import de.hs_bremen.mkss.domain.order.Order;
import de.hs_bremen.mkss.domain.repositoryInterfaces.IOrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

        return orders;
    }


}
