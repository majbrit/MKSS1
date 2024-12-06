package application.usecases;

import application.boundaries.IAddProductInput;
import application.boundaries.IAddProductOutput;
import domain.factory.ItemFactory;
import domain.item.LineItem;
import domain.order.Order;
import domain.repositoryInterfaces.IOrderRepository;

import java.util.UUID;

public class AddProductUseCase implements IAddProductInput {
    private IOrderRepository orderRepository;
    private ItemFactory itemFactory;
    private IAddProductOutput addProductOutput;

    public AddProductUseCase(IAddProductOutput addProductOutput, IOrderRepository orderRepository, ItemFactory itemFactory) {
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
