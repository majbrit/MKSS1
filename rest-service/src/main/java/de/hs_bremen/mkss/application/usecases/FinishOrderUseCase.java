package de.hs_bremen.mkss.application.usecases;

import de.hs_bremen.mkss.application.boundaries.IFinishOrderInput;
import de.hs_bremen.mkss.application.boundaries.IFinishOrderOutput;
import de.hs_bremen.mkss.domain.order.Order;
import de.hs_bremen.mkss.domain.repositoryInterfaces.IOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class FinishOrderUseCase implements IFinishOrderInput {
    private IOrderRepository orderRepository;
    private IFinishOrderOutput finishOrderOutput;

    @Autowired
    public FinishOrderUseCase(@Qualifier("guiMenu")IFinishOrderOutput finishOrderOutput, IOrderRepository orderRepository) {
        this.finishOrderOutput = finishOrderOutput;
        this.orderRepository = orderRepository;
    }

    @Override
    public void finishOrder(UUID orderId) {
        Order order = orderRepository.findById(orderId);
        if (order != null) {

            order.setCheckoutDateTime();

            orderRepository.update(orderId, order);


            finishOrderOutput.onFinishOrderResult(true, order);
        } else {
            finishOrderOutput.onFinishOrderResult(false, null);
        }
    }
}
