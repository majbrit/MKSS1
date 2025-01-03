package de.hs_bremen.mkss.application.usecases;

import de.hs_bremen.mkss.application.boundaries.IPurchaseOrderInput;
import de.hs_bremen.mkss.application.exceptions.OrderNotFoundException;
import de.hs_bremen.mkss.domain.order.Order;
import de.hs_bremen.mkss.domain.repositoryInterfaces.IOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service("PurchaseOrderUseCase")
public class PurchaseOrderUseCase implements IPurchaseOrderInput {
    private final IOrderRepository orderRepository;

    @Autowired
    public PurchaseOrderUseCase(IOrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order purchaseOrder(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order with id " + orderId + " does not exist."));


        if (order.getStatus() != Order.OrderStatus.IN_PREPARATION) {
            throw new IllegalStateException("Order must be in IN_PREPARATION status to be purchased.");
        }


        order.setStatus(Order.OrderStatus.COMMITTED);
        order.setCheckoutDateTime();
        return orderRepository.save(order);
    }
}
