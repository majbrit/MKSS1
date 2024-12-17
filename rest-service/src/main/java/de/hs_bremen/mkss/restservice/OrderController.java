package de.hs_bremen.mkss.restservice;

import java.util.List;
import java.util.Optional;

import de.hs_bremen.mkss.application.boundaries.*;
import de.hs_bremen.mkss.domain.factory.ItemFactory;
import de.hs_bremen.mkss.domain.order.Order;
import de.hs_bremen.mkss.domain.repositoryInterfaces.IOrderRepository;
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




    @Autowired
    public OrderController(IOrderRepository orderRepository, ItemFactory itemFactory,
                   @Lazy @Qualifier("createOrderUseCase")ICreateOrderInput createOrderInput,
                   @Lazy @Qualifier("addProductUseCase")IAddProductInput addProductInput,
                   @Lazy @Qualifier("clearOrdersUseCase")IClearOrdersInput clearOrdersInput,
                   @Lazy @Qualifier("finishOrderUseCase")IFinishOrderInput finishOrderInput,
                   @Lazy @Qualifier("getAllOrdersUseCase")IGetAllOrdersInput getAllOrdersInput,
                           @Lazy @Qualifier("getOrderByIdUseCase")IGetOrderByIdInput getOrderByIdInput,
                           @Lazy @Qualifier("PurchaseOrderUseCase")IPurchaseOrderInput purchaseOrderInput,
                   @Lazy @Qualifier("getAllItemsUseCase")IGetAllItemsInput getAllItemsInput) {
        this.createOrderInput = createOrderInput;
        this.addProductInput = addProductInput;
        this.clearOrdersInput = clearOrdersInput;
        this.finishOrderInput = finishOrderInput;
        this.getAllOrdersInput = getAllOrdersInput;
        this.getOrderByIdInput = getOrderByIdInput;
        this.getAllItemsInput = getAllItemsInput;
        this.orderRepository = orderRepository;
        this.itemFactory = itemFactory;
        this.purchaseOrderInput =purchaseOrderInput;
    }

    /*
    *
    * */
    @GetMapping
    public List<Order> getOrders() {
        List<Order> orders = getAllOrdersInput.getAllOrders();
        return orders;
    }
    @GetMapping("/{orderId}")
    public Order getOrderById(@PathVariable Long orderId) {
        return getOrderByIdInput.getOrderById(orderId);
    }
    /*http://localhost:2222/orders/1/*/
    @GetMapping("orders/{orderid}")
    public Optional<Order> getOrder(@RequestParam(value = "orderid", defaultValue = "0") Long id) {
        /*var getAllOrdersOutput = new GetAllOrdersOutput();
        var getAllOrdersUseCase = new GetAllOrdersUseCase(getAllOrdersOutput, orderRepository);

        getAllOrdersUseCase.getAllOrders();

        return getAllOrdersOutput.orders.stream()
                .filter(order -> order.getId().equals(id))
                .findFirst();*/

        return null;
    }

    @GetMapping("orders/addorder")
    public Order addOrder() {
        /*var createOrderOutput = new CreateOrderOutput();
        var clearOrdersUseCase = new CreateOrderUseCase(createOrderOutput, orderRepository);

        clearOrdersUseCase.createOrder();

        return createOrderOutput.order;*/
        return null;
    }

    @GetMapping("orders/{orderid}/deleteorder")
    public boolean deleteOrder(@RequestParam(value = "id", defaultValue = "0") Long id) {
       /* var clearOrdersOutput = new ClearOrdersOutput();
        var clearOrdersUseCase = new ClearOrdersUseCase(clearOrdersOutput, orderRepository);

        //ZK: need to implemented delete one Order
        clearOrdersUseCase.clearOrders();

        return clearOrdersOutput.success;*/
        return false;
    }

    @GetMapping("orders/{orderid}/orderitems")
    public Order getorderItems(@RequestParam(value = "orderId", defaultValue = "0") Long orderId) {
        return null;
    }

    @GetMapping("orders/{orderid}/addorderitem")
    public Order addorderItem() {
        return null;
    }

    @GetMapping("{orderid}/deleteorderitem/{itemid}")
    public Optional<Order> deleteorderItem(@RequestParam(value = "itemId", defaultValue = "0") Long itemId) {
        return null;
    }

    @PutMapping("/{orderId}/purchase")
    public Order purchaseOrder(@PathVariable Long orderId) {
        try {
            return purchaseOrderInput.purchaseOrder(orderId);
        } catch (IllegalStateException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }
}