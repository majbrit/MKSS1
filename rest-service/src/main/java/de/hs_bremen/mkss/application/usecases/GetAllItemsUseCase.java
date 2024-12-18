package de.hs_bremen.mkss.application.usecases;

import de.hs_bremen.mkss.application.boundaries.IGetAllItemsInput;
import de.hs_bremen.mkss.application.exceptions.OrderNotFoundException;
import de.hs_bremen.mkss.domain.order.Order;
import de.hs_bremen.mkss.domain.repositoryInterfaces.IOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("getAllItemsUseCase")
public class GetAllItemsUseCase implements IGetAllItemsInput {
    private final IOrderRepository orderRepository;

    @Autowired
    public GetAllItemsUseCase(IOrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List getAllItems(Order order) {
        if (order != null) {
            if (!orderRepository.existsById(order.getId())) {
                throw new OrderNotFoundException("Order with does not exist.");
            }
            order.sortItems();
            return order.getItems();
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public void deleteItem(Long orderId, Long itemId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order with id " + orderId + " does not exist."));
        order.deleteItem(itemId);
        orderRepository.save(order);
    }

}
