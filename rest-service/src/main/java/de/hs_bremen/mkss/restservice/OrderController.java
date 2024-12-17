package de.hs_bremen.mkss.restservice;

import java.util.List;

import de.hs_bremen.mkss.application.boundaries.*;
import de.hs_bremen.mkss.domain.factory.ItemFactory;
import de.hs_bremen.mkss.domain.item.Item;
import de.hs_bremen.mkss.domain.order.Order;
import de.hs_bremen.mkss.domain.repositoryInterfaces.IOrderRepository;
import de.hs_bremen.mkss.restservice.models.OrderItem;
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
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);

            return null;
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
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);

            return false;
        }

        return clearOrdersInput.deleteOrder(orderId);
    }

    /*http://localhost:2222/orders/3/orderitems*/
    @GetMapping("/orders/{orderId}/orderitems")
    public List<Item> getorderItems(@PathVariable Long orderId, HttpServletResponse response) {
        var order = getAllOrdersInput.getOrder(orderId);

        if(order == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);

            return null;
        }

        return getAllItemsInput.getAllItems(order);
    }

    /*http://localhost:2222/orders/3/addorderitem*/
    @PutMapping("/orders/{orderId}/addorderitem")
    public Item addorderItem(@RequestBody OrderItem orderItem, @PathVariable Long orderId, HttpServletResponse response) {
        var order = getAllOrdersInput.getOrder(orderId);

        if(order == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);

            return null;
        }

        return addProductInput.addProduct(order, orderItem.name, orderItem.price, orderItem.quantity);
    }

    /*http://localhost:2222/orders/3/deleteorderitem/6*/
    @DeleteMapping("/orders/{orderId}/deleteorderitem/{itemId}")
    public boolean deleteorderItem(@PathVariable Long orderId, @PathVariable Long itemId, HttpServletResponse response) {
        var order = getAllOrdersInput.getOrder(orderId);

        if(order == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);

            return false;
        }

        return getAllItemsInput.deleteItem(order, itemId);
    }
}