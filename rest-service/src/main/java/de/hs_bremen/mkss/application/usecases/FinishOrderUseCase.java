package de.hs_bremen.mkss.application.usecases;

import de.hs_bremen.mkss.application.boundaries.IFinishOrderInput;
import de.hs_bremen.mkss.domain.order.Order;
import de.hs_bremen.mkss.domain.repositoryInterfaces.IOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("finishOrderUseCase")
public class FinishOrderUseCase implements IFinishOrderInput {
    private IOrderRepository orderRepository;

    @Autowired
    public FinishOrderUseCase( IOrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order finishOrder(Order order) {

        if (order != null) {

            order.setCheckoutDateTime();

            //JpaRepository has only save, the method can create and update
            orderRepository.save(order);


            return order;
        } else {
            return null;
        }
    }
}
