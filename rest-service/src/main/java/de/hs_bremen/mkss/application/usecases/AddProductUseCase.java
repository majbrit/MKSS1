package de.hs_bremen.mkss.application.usecases;

import de.hs_bremen.mkss.application.boundaries.IAddProductInput;
import de.hs_bremen.mkss.application.boundaries.IAddProductOutput;
import de.hs_bremen.mkss.domain.factory.ItemFactory;
import de.hs_bremen.mkss.domain.item.LineItem;
import de.hs_bremen.mkss.domain.order.Order;
import de.hs_bremen.mkss.domain.repositoryInterfaces.IOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AddProductUseCase implements IAddProductInput {
    private IOrderRepository orderRepository;
    private ItemFactory itemFactory;
    private IAddProductOutput addProductOutput;

    @Autowired
    public AddProductUseCase(@Qualifier("guiMenu") IAddProductOutput addProductOutput, IOrderRepository orderRepository, ItemFactory itemFactory) {
        this.addProductOutput = addProductOutput;
        this.orderRepository = orderRepository;
        this.itemFactory = itemFactory;
    }


    @Override
    public void addProduct(UUID id, String name, int price, int quantity) {
        LineItem lineItem = itemFactory.createProduct(name, price, quantity);
        Order order = orderRepository.findById(id);
        order.addProduct(lineItem);
        boolean updated = orderRepository.update(id, order);
        addProductOutput.onAddProductResult(updated);
    }
}
