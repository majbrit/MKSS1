package application.usecases;

import application.boundaries.IAddServiceInput;
import application.boundaries.IAddServiceOutput;
import domain.factory.ItemFactory;
import domain.item.Service;
import domain.order.Order;
import domain.repositoryInterfaces.IOrderRepository;

import java.util.UUID;

public class AddServiceUseCase implements IAddServiceInput {
    private IOrderRepository orderRepository;
    private ItemFactory itemFactory;
    private IAddServiceOutput addServiceOutput;

    public AddServiceUseCase(IAddServiceOutput addServiceOutput, IOrderRepository orderRepository, ItemFactory itemFactory) {
        this.addServiceOutput = addServiceOutput;
        this.orderRepository = orderRepository;
        this.itemFactory = itemFactory;
    }


    @Override
    public void addService(UUID id, String name, int persons, int hours) {
        Service service = itemFactory.createService(name, persons, hours);
        Order order = orderRepository.findById(id);
        order.addProduct(service);
        boolean updated = orderRepository.update(id, order);
        addServiceOutput.onAddServiceResult(updated);
    }
}
