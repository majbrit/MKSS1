package de.hs_bremen.mkss.order_events_processor.service;

import jakarta.annotation.PostConstruct;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

import de.hsbremen.mkss.events.EventWithPayload;
import de.hsbremen.mkss.events.dto.OrderDTO;
import org.springframework.stereotype.Component;

@Component
public class OrderEventsConsumer {

    @RabbitListener(queues = "orderQueue")
    public void receiveMessage(EventWithPayload<OrderDTO> event) {
        System.out.println("Received Order Event: " + event);
    }
}
