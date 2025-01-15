package de.hs_bremen.mkss.service;

import de.hs_bremen.mkss.application.exceptions.OrderNotFoundException;
import de.hs_bremen.mkss.domain.order.Order;
import de.hs_bremen.mkss.domain.repositoryInterfaces.IOrderRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
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


    @RabbitListener(queues = "${my.rabbitmq.reply.queue}")
    public void receiveReplyEvent(EventWithPayload<OrderDTO> event) {
        System.out.println("Received response Event: " + event);
        OrderDTO updatedOrderDTO = event.getPayload();
        System.out.println(updatedOrderDTO.getStatus());
        Optional<Order> optionalOrder = orderRepository.findById(updatedOrderDTO.getId());
        if (optionalOrder.isPresent()) {
            Order existingOrder = optionalOrder.get();
            existingOrder.setStatus(Order.OrderStatus.valueOf(updatedOrderDTO.getStatus()));
            orderRepository.save(existingOrder);
            System.out.println("Updated order saved: " + existingOrder);
        } else {
            System.out.println("Order not found with ID: " + updatedOrderDTO.getId());
        }


    }

}
