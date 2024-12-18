package de.hs_bremen.mkss.interfaceAdapters.controllers;

import java.util.List;

import de.hs_bremen.mkss.application.boundaries.*;
import de.hs_bremen.mkss.domain.factory.ItemFactory;
import de.hs_bremen.mkss.domain.item.Item;
import de.hs_bremen.mkss.domain.order.Order;
import de.hs_bremen.mkss.domain.repositoryInterfaces.IOrderRepository;
import de.hs_bremen.mkss.interfaceAdapters.dtos.OrderItem;
import de.hs_bremen.mkss.application.exceptions.EmptyOrderException;
import de.hs_bremen.mkss.application.exceptions.ItemNotFoundException;
import de.hs_bremen.mkss.application.exceptions.OrderAlreadyBoughtException;
import de.hs_bremen.mkss.application.exceptions.OrderNotFoundException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrderController {
    // use case input boundaries
    private IOrderRepository orderRepository;
    private ItemFactory itemFactory;

    // use case input boundaries
    private ICreateOrderInput createOrderInput;
    private IAddProductInput addProductInput;
    private IGetAllItemsInput getAllItemsInput;
    private IFinishOrderInput finishOrderInput;
    private IGetAllOrdersInput getAllOrdersInput;
    private IClearOrdersInput clearOrdersInput;

    @Autowired
    public OrderController(IOrderRepository orderRepository, ItemFactory itemFactory,
                   @Lazy @Qualifier("createOrderUseCase")ICreateOrderInput createOrderInput,
                   @Lazy @Qualifier("addProductUseCase")IAddProductInput addProductInput,
                   @Lazy @Qualifier("clearOrdersUseCase")IClearOrdersInput clearOrdersInput,
                   @Lazy @Qualifier("finishOrderUseCase")IFinishOrderInput finishOrderInput,
                   @Lazy @Qualifier("getAllOrdersUseCase")IGetAllOrdersInput getAllOrdersInput,
                   @Lazy @Qualifier("getAllItemsUseCase")IGetAllItemsInput getAllItemsInput) {
        this.createOrderInput = createOrderInput;
        this.addProductInput = addProductInput;
        this.clearOrdersInput = clearOrdersInput;
        this.finishOrderInput = finishOrderInput;
        this.getAllOrdersInput = getAllOrdersInput;
        this.getAllItemsInput = getAllItemsInput;
        this.orderRepository = orderRepository;
        this.itemFactory = itemFactory;
    }

    /*http://localhost:2222/orders*/
    @GetMapping("/orders")
    public List<Order> getOrders() {
        return getAllOrdersInput.getAllOrders();
    }

    /*http://localhost:2222/orders/1*/
    @GetMapping("/orders/{orderId}")
    public Order getOrder(@PathVariable Long orderId, HttpServletResponse response) {
        var order = getAllOrdersInput.getOrder(orderId);

        if(order == null) {
            throw new OrderNotFoundException("Order with id " + orderId + " does not exist.");
        }

        return order;
    }

    /*http://localhost:2222/orders/addorder*/
    @GetMapping("/orders/addorder")
    public Order addOrder() {
        return createOrderInput.createOrder();
    }

    /*http://localhost:2222/orders/3/deleteorder*/
    @DeleteMapping("/orders/{orderId}/deleteorder")
    public boolean deleteOrder(@PathVariable Long orderId, HttpServletResponse response) {
        var order = getAllOrdersInput.getOrder(orderId);

        if(order == null) {
            throw new OrderNotFoundException("Order with id " + orderId + " does not exist.");
        }

        return clearOrdersInput.deleteOrder(orderId);
    }

    /*http://localhost:2222/orders/3/orderitems*/
    @GetMapping("/orders/{orderId}/orderitems")
    public List<Item> getorderItems(@PathVariable Long orderId, HttpServletResponse response) {
        var order = getAllOrdersInput.getOrder(orderId);

        if(order == null) {
            throw new OrderNotFoundException("Order with id " + orderId + " does not exist.");
        }

        return getAllItemsInput.getAllItems(order);
    }

    /*http://localhost:2222/orders/3/addorderitem*/
    @PutMapping("/orders/{orderId}/addorderitem")
    public Item addorderItem(@RequestBody OrderItem orderItem, @PathVariable Long orderId, HttpServletResponse response) {
        var order = getAllOrdersInput.getOrder(orderId);

        if(order == null) {
            throw new OrderNotFoundException("Order with id " + orderId + " does not exist.");
        }

        return addProductInput.addProduct(order, orderItem.name, orderItem.price, orderItem.quantity);
    }

    /*http://localhost:2222/orders/3/deleteorderitem/6*/
    @DeleteMapping("/orders/{orderId}/deleteorderitem/{itemId}")
    public boolean deleteorderItem(@PathVariable Long orderId, @PathVariable Long itemId, HttpServletResponse response) {
        var order = getAllOrdersInput.getOrder(orderId);

        if(order == null) {
            throw new OrderNotFoundException("Order with id " + orderId + " does not exist.");
        }

        order.getItems().stream()
                .filter(item -> item.getId().equals(itemId))
                .findFirst()
                .orElseThrow(() -> new ItemNotFoundException("Item with id " + itemId + " not found in order."));

        return getAllItemsInput.deleteItem(order, itemId);
    }

    @PostMapping("/orders/{orderId}/buy")
    public void buyOrder(@PathVariable Long orderId, HttpServletResponse response) {
        var order = getAllOrdersInput.getOrder(orderId);
        if(order == null) {
            throw new OrderNotFoundException("Order with id " + orderId + " does not exist and cannot be bought.");
        }
        if(order.getItems().isEmpty()) {
            throw new EmptyOrderException("Order with id " + orderId + " has no items and cannot be bought.");
        }

        //TODO If Order has already been bought
        if(false) {
            throw new OrderAlreadyBoughtException("Order with id " + orderId + " has already been bought.");
        }

        response.setStatus(HttpServletResponse.SC_OK);
    }
}