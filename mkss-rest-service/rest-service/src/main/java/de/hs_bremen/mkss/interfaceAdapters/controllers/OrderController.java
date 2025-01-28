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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.headers.Header;

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
@Tag(name = "Order API", description = "Management of orders and items")
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
    @Operation(summary = "List of all orders", description = "Get a list of all orders")
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    description = "successful return of a list of all orders",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Order.class)))),
            @ApiResponse(responseCode = "500",
                    description = "Internal Server Error. Something went wrong on the server.")
    })
    public List<Order> getOrders() {
        return getAllOrdersInput.getAllOrders();
    }

    /*get http://localhost:2222/orders/1*/
    @GetMapping("/{orderId}")
    @Operation(summary = "Returns order by ID", description = "Retrieve an order using an ID of the order")
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    description = "found order by ID successfully",
                    content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Order.class))),
            @ApiResponse(responseCode = "500",
                    description = "Internal Server Error. Order not found with id.")
    })
    public Order getOrderById(@PathVariable Long orderId) {
        return getOrderByIdInput.getOrderById(orderId);
    }

    /*post http://localhost:2222/orders*/
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
            summary = "Create a new order",
            description = "Create a new order for a customer."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Order created successfully",
                    content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Order.class))),
            @ApiResponse(responseCode = "400",
                    description = "Bad Request. JSON parse error: No content to map due to end-of-input")
    })
    public Order addOrder(@RequestBody CreateOrderRequest request) {
        Order order = createOrderInput.createOrder(request.getCustomerName());
        orderEventsProducer.emitCreateEvent(order);
        return order;
    }


    /*delete http://localhost:2222/orders/3*/
    @DeleteMapping("/{orderId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(
            summary = "Delete order by ID",
            description = "Delete an order by its ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Order deleted successfully",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500",
                    description = "Order not found with id")
    })
    public ResponseEntity<String> deleteOrder(@PathVariable Long orderId) {
        Order order = getOrderById(orderId);
        clearOrdersInput.deleteOrder(orderId);
        orderEventsProducer.emitDeleteEvent(order);
        return ResponseEntity.ok("Order with ID " + orderId + " has been successfully deleted");
    }

    /*get http://localhost:2222/orders/3/items*/
    @GetMapping("/{orderId}/items")
    @Operation(
            summary = "Get items in order",
            description = "Retrieve all items for an order with a specific ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Items of the order found successfully",
                    content = @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = Item.class)))),
            @ApiResponse(responseCode = "500",
                    description = "Order with items not found")
    })
    public List<Item> getOrderItems(@PathVariable Long orderId) {
        Order order = getOrderByIdInput.getOrderById(orderId);
        return getAllItemsInput.getAllItems(order);
    }


    /*put http://localhost:2222/orders/3/items*/
    @PutMapping("/{orderId}/items")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
            summary = "Add item to order",
            description = "Add a product item to an order of a specific ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Item added successfully",
                    content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Item.class))),
            @ApiResponse(responseCode = "500",
                    description = "Order not found"),
            @ApiResponse(responseCode = "400",
                    description = "Bad Request. Required request body is missing")
    })
    public Item addOrderItem(@RequestBody OrderItem orderItem, @PathVariable Long orderId) {
        Order order = getOrderByIdInput.getOrderById(orderId);
        Item item = addProductInput.addProduct(order, orderItem.name, orderItem.price, orderItem.quantity);
        orderEventsProducer.emitUpdateEvent(order);
        return item;
    }

    /*delete http://localhost:2222/orders/3/items/6*/
    @DeleteMapping("/{orderId}/items/{itemId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(
            summary = "Delete item from order",
            description = "Delete an item from an order of a specific ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Item deleted successfully"),
            @ApiResponse(responseCode = "500",
                    description = "Internal Server Error. Order with this ID does not exist")
    })
    public ResponseEntity<String> deleteOrderItem(@PathVariable Long orderId, @PathVariable Long itemId) {
        getAllItemsInput.deleteItem(orderId, itemId);
        Order order = getOrderById(orderId);
        orderEventsProducer.emitUpdateEvent(order);
        return ResponseEntity.ok("Item with ID " + itemId + " has been successfully deleted from order " + orderId);
    }

    /*put http://localhost:2222/orders/3/status*/
    @PutMapping("/{orderId}/status")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Purchase order",
            description = "Purchase of an order."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Order purchased successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Order.class))),
            @ApiResponse(responseCode = "500",
                    description = "Internal Server Error. Order must not be empty."),
            @ApiResponse(responseCode = "500",
                    description = "Internal Server Error. Order must not purchase more than once."),
            @ApiResponse(responseCode = "500",
                    description = "Internal Server Error. Order with this id does not exist")
    })
    public Order purchaseOrder(@PathVariable Long orderId) {
        Order order = purchaseOrderInput.purchaseOrder(orderId);
        orderEventsProducer.emitUpdateEvent(order);
        return order;
    }
}