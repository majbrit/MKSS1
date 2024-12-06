package de.hs_bremen.mkss.application.usecases;

import de.hs_bremen.mkss.application.boundaries.IFinishOrderInput;
import de.hs_bremen.mkss.application.boundaries.IFinishOrderOutput;
import de.hs_bremen.mkss.domain.order.Order;
import de.hs_bremen.mkss.domain.repositoryInterfaces.IOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service("finishOrderUseCase")
public class FinishOrderUseCase implements IFinishOrderInput {
    private IOrderRepository orderRepository;
    private IFinishOrderOutput finishOrderOutput;

    @Autowired
    public FinishOrderUseCase(IFinishOrderOutput finishOrderOutput, IOrderRepository orderRepository) {
        this.finishOrderOutput = finishOrderOutput;
        this.orderRepository = orderRepository;
    }

    @Override
    public void finishOrder(Order order) {

        if (order != null) {

            order.setCheckoutDateTime();

            //JpaRepository has only save, the method can create and update
            orderRepository.save(order);


            finishOrderOutput.onFinishOrderResult(true, order);
        } else {
            finishOrderOutput.onFinishOrderResult(false, null);
        }
    }
}
