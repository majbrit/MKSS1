package de.hs_bremen.mkss.application.usecases;

import de.hs_bremen.mkss.application.boundaries.IGetAllItemsInput;
import de.hs_bremen.mkss.application.boundaries.IGetAllItemsOutput;
import de.hs_bremen.mkss.application.boundaries.IGetAllItemsInput;
import de.hs_bremen.mkss.application.boundaries.IGetAllOrdersInput;
import de.hs_bremen.mkss.domain.order.Order;
import de.hs_bremen.mkss.domain.repositoryInterfaces.IOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;

@Service
public class GetAllItemsUseCase implements IGetAllItemsInput {
    private final IOrderRepository orderRepository;
    private final IGetAllItemsOutput getAllItemsOutput;

    @Autowired
    public GetAllItemsUseCase(@Qualifier("guiMenu")IGetAllItemsOutput getAllItemsOutput, IOrderRepository orderRepository) {
        this.getAllItemsOutput = getAllItemsOutput;
        this.orderRepository = orderRepository;
    }

    @Override
    public void getAllItems(UUID orderId) {
        Order order = orderRepository.findById(orderId);
        if (order != null) {
            order.sortItems();
            getAllItemsOutput.onGetAllItemsResult(order.getItems());
        } else {
            getAllItemsOutput.onGetAllItemsResult(new ArrayList<>());
        }
    }
}
