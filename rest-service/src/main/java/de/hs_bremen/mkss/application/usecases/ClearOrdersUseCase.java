package de.hs_bremen.mkss.application.usecases;

import de.hs_bremen.mkss.application.boundaries.IClearOrdersInput;
import de.hs_bremen.mkss.application.boundaries.IClearOrdersOutput;
import de.hs_bremen.mkss.domain.order.Order;
import de.hs_bremen.mkss.domain.repositoryInterfaces.IOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;



@Service("clearOrdersUseCase")
public class ClearOrdersUseCase implements IClearOrdersInput {
    private IOrderRepository orderRepository;
    private IClearOrdersOutput clearOrdersOutput;

    @Autowired
    public ClearOrdersUseCase(IClearOrdersOutput clearOrdersOutput, IOrderRepository orderRepository) {
        this.clearOrdersOutput = clearOrdersOutput;
        this.orderRepository = orderRepository;
    }


    @Override
    public void clearOrders() {
        try {
            orderRepository.deleteAll();
            clearOrdersOutput.onClearOrdersResult(true);
        } catch (Exception e) {
            clearOrdersOutput.onClearOrdersResult(false);
        }
    }

}
