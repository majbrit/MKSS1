package de.hs_bremen.mkss.application.usecases;

import de.hs_bremen.mkss.application.boundaries.IClearOrdersInput;
import de.hs_bremen.mkss.domain.repositoryInterfaces.IOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("clearOrdersUseCase")
public class ClearOrdersUseCase implements IClearOrdersInput {
    private IOrderRepository orderRepository;

    @Autowired
    public ClearOrdersUseCase(IOrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public boolean clearOrders() {
        try {
            orderRepository.deleteAll();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean deleteOrder(Long id) {
        try {
            orderRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
