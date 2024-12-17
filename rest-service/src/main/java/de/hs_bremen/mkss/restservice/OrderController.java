package de.hs_bremen.mkss.restservice;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import de.hs_bremen.mkss.application.usecases.ClearOrdersUseCase;
import de.hs_bremen.mkss.application.usecases.CreateOrderUseCase;
import de.hs_bremen.mkss.application.usecases.GetAllOrdersUseCase;
import de.hs_bremen.mkss.domain.factory.ItemFactory;
import de.hs_bremen.mkss.domain.order.Order;
import de.hs_bremen.mkss.domain.repositoryInterfaces.IOrderRepository;
import de.hs_bremen.mkss.restservice.outputs.ClearOrdersOutput;
import de.hs_bremen.mkss.restservice.outputs.CreateOrderOutput;
import de.hs_bremen.mkss.restservice.outputs.GetAllOrdersOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
    // use case input boundaries
    private IOrderRepository orderRepository;
    private ItemFactory itemFactory;

    @Autowired
    public OrderController(IOrderRepository orderRepository, ItemFactory itemFactory) {
        this.orderRepository = orderRepository;
        this.itemFactory = itemFactory;
    }

    /*http://localhost:2222/orders*/
    @GetMapping("/orders")
    public List<Order> getorders() {
        var getAllOrdersOutput = new GetAllOrdersOutput();
        var getAllOrdersUseCase = new GetAllOrdersUseCase(getAllOrdersOutput, orderRepository);

        getAllOrdersUseCase.getAllOrders();

        return getAllOrdersOutput.orders;
    }

    /*http://localhost:2222/order?id=100*/
    @GetMapping("/order")
    public Optional<Order> getorder(@RequestParam(value = "id", defaultValue = "0") Long id) {
        var getAllOrdersOutput = new GetAllOrdersOutput();
        var getAllOrdersUseCase = new GetAllOrdersUseCase(getAllOrdersOutput, orderRepository);

        getAllOrdersUseCase.getAllOrders();

        return getAllOrdersOutput.orders.stream()
                .filter(order -> order.getId().equals(id))
                .findFirst();
    }

    @GetMapping("/addorder")
    public Order addorder() {
        var createOrderOutput = new CreateOrderOutput();
        var clearOrdersUseCase = new CreateOrderUseCase(createOrderOutput, orderRepository);

        clearOrdersUseCase.createOrder();

        return createOrderOutput.order;
    }

    @GetMapping("/deleteorder")
    public boolean deleteorder(@RequestParam(value = "id", defaultValue = "0") Long id) {
        var clearOrdersOutput = new ClearOrdersOutput();
        var clearOrdersUseCase = new ClearOrdersUseCase(clearOrdersOutput, orderRepository);

        //ZK: need to implemented delete one Order
        clearOrdersUseCase.clearOrders();

        return clearOrdersOutput.success;
    }

    @GetMapping("/orderitems")
    public Order getorderitems(@RequestParam(value = "orderId", defaultValue = "0") Long orderId) {
        return null;
    }

    @GetMapping("/addorderitem")
    public Order addorderitem() {
        return null;
    }

    @GetMapping("/deleteorderitem")
    public Optional<Order> deleteorderitem(@RequestParam(value = "itemId", defaultValue = "0") Long itemId) {
        return null;
    }
}