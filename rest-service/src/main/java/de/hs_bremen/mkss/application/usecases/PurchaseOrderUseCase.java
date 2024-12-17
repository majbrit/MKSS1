package de.hs_bremen.mkss.application.usecases;

import de.hs_bremen.mkss.application.boundaries.IPurchaseOrderInput;
import de.hs_bremen.mkss.domain.order.Order;
import de.hs_bremen.mkss.domain.repositoryInterfaces.IOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
                .orElseThrow(() -> new IllegalStateException("Order not found with id: " + orderId));


        if (order.getStatus() != Order.OrderStatus.IN_PREPARATION) {
            throw new IllegalStateException("Order must be in IN_PREPARATION status to be purchased.");
        }


        order.setStatus(Order.OrderStatus.COMMITTED);

        return orderRepository.save(order);
    }
}
