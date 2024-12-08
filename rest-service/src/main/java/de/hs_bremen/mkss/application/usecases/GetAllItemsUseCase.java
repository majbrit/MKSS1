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


@Service("getAllItemsUseCase")
public class GetAllItemsUseCase implements IGetAllItemsInput {
    private final IOrderRepository orderRepository;
    private final IGetAllItemsOutput getAllItemsOutput;

    @Autowired
    public GetAllItemsUseCase(IGetAllItemsOutput getAllItemsOutput, IOrderRepository orderRepository) {
        this.getAllItemsOutput = getAllItemsOutput;
        this.orderRepository = orderRepository;
    }

    @Override
    public void getAllItems(Order order) {
        if (order != null) {
            order.sortItems();
            getAllItemsOutput.onGetAllItemsResult(order.getItems());
        } else {
            getAllItemsOutput.onGetAllItemsResult(new ArrayList<>());
        }
    }
}
