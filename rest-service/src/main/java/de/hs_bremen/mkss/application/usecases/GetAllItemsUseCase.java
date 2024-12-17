package de.hs_bremen.mkss.application.usecases;

import de.hs_bremen.mkss.application.boundaries.IGetAllItemsInput;
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
            order.sortItems();
            return order.getItems();
        } else {
            return new ArrayList<>();
        }
    }
}
