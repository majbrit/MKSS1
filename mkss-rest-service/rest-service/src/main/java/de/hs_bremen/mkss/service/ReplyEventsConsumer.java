package de.hs_bremen.mkss.service;

import de.hs_bremen.mkss.domain.order.Order;
import de.hs_bremen.mkss.domain.repositoryInterfaces.IOrderRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;
import de.hsbremen.mkss.events.EventWithPayload;
import de.hsbremen.mkss.events.dto.OrderDTO;

import java.util.Optional;

@Component
public class ReplyEventsConsumer {
    private final IOrderRepository orderRepository;

    @Autowired
    public ReplyEventsConsumer(IOrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }


    @RabbitListener(queues = "replyQueue")
    public void receiveReplyEvent(EventWithPayload<OrderDTO> event, @Header("amqp_receivedRoutingKey") String routingKey) {
        System.out.println("Received Order Event: " + event);
        OrderDTO updatedOrderDTO = event.getPayload();
        System.out.println(updatedOrderDTO.getStatus());
        switch (routingKey) {
            case "emit-order-created":
                System.out.println("Handling (emit) 'CREATE' event for Order ID: " + updatedOrderDTO.getId());
                break;
            case "emit-order-updated":
                System.out.println("Handling (emit) 'UPDATE' event for Order ID: " + updatedOrderDTO.getId());
                Optional<Order> optionalOrder = orderRepository.findById(updatedOrderDTO.getId());
                if (optionalOrder.isPresent()) {
                    Order existingOrder = optionalOrder.get();
                    existingOrder.setStatus(Order.OrderStatus.valueOf(updatedOrderDTO.getStatus()));
                    orderRepository.save(existingOrder);
                    System.out.println("Updated order saved: " + existingOrder);
                } else {
                    System.out.println("Order not found with ID: " + updatedOrderDTO.getId());
                }
                break;
            case "emit-order-deleted":
                System.out.println("Handling (emit) 'DELETE' event for Order ID: " + updatedOrderDTO.getId());
                break;
            default:
                System.out.println("Unknown routing key received: " + routingKey);
                break;
        }

    }
}
