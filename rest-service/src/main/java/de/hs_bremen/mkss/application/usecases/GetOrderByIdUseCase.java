package de.hs_bremen.mkss.application.usecases;

import de.hs_bremen.mkss.application.boundaries.IGetOrderByIdInput;
import de.hs_bremen.mkss.domain.order.Order;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import de.hs_bremen.mkss.domain.repositoryInterfaces.IOrderRepository;

import java.util.Optional;


@Service("getOrderByIdUseCase")
@Transactional
public class GetOrderByIdUseCase implements IGetOrderByIdInput {
    private final IOrderRepository orderRepository;
    @Autowired
    public GetOrderByIdUseCase(IOrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }


    @Override
    public Order getOrderById(Long orderId) {
        Optional<Order> order = orderRepository.findById(orderId);
        return order.orElseThrow(() -> new IllegalStateException("Order not found with id: " + orderId));
    }
}
