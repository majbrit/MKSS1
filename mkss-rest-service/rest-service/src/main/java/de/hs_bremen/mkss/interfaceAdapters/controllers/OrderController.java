package de.hs_bremen.mkss.interfaceAdapters.controllers;

import java.util.List;

import de.hs_bremen.mkss.application.boundaries.*;
import de.hs_bremen.mkss.domain.factory.ItemFactory;
import de.hs_bremen.mkss.domain.item.Item;
import de.hs_bremen.mkss.domain.order.Order;
import de.hs_bremen.mkss.domain.repositoryInterfaces.IOrderRepository;
import de.hs_bremen.mkss.frameworksAndDrivers.events.OrderEventsProducer;
import de.hs_bremen.mkss.interfaceAdapters.dtos.CreateOrderRequest;
import de.hs_bremen.mkss.interfaceAdapters.dtos.OrderItem;
import de.hs_bremen.mkss.application.exceptions.EmptyOrderException;
import de.hs_bremen.mkss.application.exceptions.ItemNotFoundException;
import de.hs_bremen.mkss.application.exceptions.OrderAlreadyBoughtException;
import de.hs_bremen.mkss.application.exceptions.OrderNotFoundException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
@RestController
@RequestMapping("/orders")
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
    private IGetOrderByIdInput getOrderByIdInput;
    private IPurchaseOrderInput purchaseOrderInput;

    private final OrderEventsProducer orderEventsProducer;


    @Autowired
    public OrderController(IOrderRepository orderRepository, ItemFactory itemFactory,
                   @Lazy @Qualifier("createOrderUseCase")ICreateOrderInput createOrderInput,
                   @Lazy @Qualifier("addProductUseCase")IAddProductInput addProductInput,
                   @Lazy @Qualifier("clearOrdersUseCase")IClearOrdersInput clearOrdersInput,
                           @Lazy @Qualifier("getOrderByIdUseCase")IGetOrderByIdInput getOrderByIdInput,
                           @Lazy @Qualifier("PurchaseOrderUseCase")IPurchaseOrderInput purchaseOrderInput,
                   @Lazy @Qualifier("finishOrderUseCase")IFinishOrderInput finishOrderInput,
                   @Lazy @Qualifier("getAllOrdersUseCase")IGetAllOrdersInput getAllOrdersInput,
                   @Lazy @Qualifier("getAllItemsUseCase")IGetAllItemsInput getAllItemsInput,
                           OrderEventsProducer orderEventsProducer) {
        this.createOrderInput = createOrderInput;
        this.addProductInput = addProductInput;
        this.clearOrdersInput = clearOrdersInput;
        this.finishOrderInput = finishOrderInput;
        this.getAllOrdersInput = getAllOrdersInput;
        this.getAllItemsInput = getAllItemsInput;
        this.orderRepository = orderRepository;
        this.itemFactory = itemFactory;
        this.getOrderByIdInput = getOrderByIdInput;
        this.purchaseOrderInput = purchaseOrderInput;
        this.orderEventsProducer = orderEventsProducer;
    }


    /*get http://localhost:2222/orders*/
    @GetMapping
    public List<Order> getOrders() {
        return getAllOrdersInput.getAllOrders();
    }

    /*get http://localhost:2222/orders/1*/
    @GetMapping("/{orderId}")
    public Order getOrderById(@PathVariable Long orderId) {
        return getOrderByIdInput.getOrderById(orderId);
    }

    /*post http://localhost:2222/orders*/
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Order addOrder(@RequestBody CreateOrderRequest request) {
        Order order = createOrderInput.createOrder(request.getCustomerName());
        orderEventsProducer.emitCreateEvent(order);
        return order;
    }


    /*delete http://localhost:2222/orders/3*/
    @DeleteMapping("/{orderId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<String> deleteOrder(@PathVariable Long orderId) {
        Order order = getOrderById(orderId);
        clearOrdersInput.deleteOrder(orderId);
        orderEventsProducer.emitDeleteEvent(order);
        return ResponseEntity.ok("Order with ID " + orderId + " has been successfully deleted");
    }

    /*get http://localhost:2222/orders/3/items*/
    @GetMapping("/{orderId}/items")
    public List<Item> getOrderItems(@PathVariable Long orderId) {
        Order order = getOrderByIdInput.getOrderById(orderId);
        return getAllItemsInput.getAllItems(order);
    }


    /*put http://localhost:2222/orders/3/items*/
    @PutMapping("/{orderId}/items")
    @ResponseStatus(HttpStatus.CREATED)
    public Item addOrderItem(@RequestBody OrderItem orderItem, @PathVariable Long orderId) {
        Order order = getOrderByIdInput.getOrderById(orderId);
        Item item = addProductInput.addProduct(order, orderItem.name, orderItem.price, orderItem.quantity);
        orderEventsProducer.emitUpdateEvent(order);
        return item;
    }

    /*delete http://localhost:2222/orders/3/items/6*/
    @DeleteMapping("/{orderId}/items/{itemId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<String> deleteOrderItem(@PathVariable Long orderId, @PathVariable Long itemId) {
        getAllItemsInput.deleteItem(orderId, itemId);
        Order order = getOrderById(orderId);
        orderEventsProducer.emitUpdateEvent(order);
        return ResponseEntity.ok("Item with ID " + itemId + " has been successfully deleted from order " + orderId);
    }

    /*put http://localhost:2222/orders/3/status*/
    @PutMapping("/{orderId}/status")
    @ResponseStatus(HttpStatus.OK)
    public Order purchaseOrder(@PathVariable Long orderId) {
        Order order = purchaseOrderInput.purchaseOrder(orderId);
        orderEventsProducer.emitUpdateEvent(order);
        return order;
    }
}