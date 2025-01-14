package de.hs_bremen.mkss.order_events_processor.service;

import de.hs_bremen.mkss.order_events_processor.events.OrderEventsProducer;
import jakarta.annotation.PostConstruct;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

import de.hsbremen.mkss.events.EventWithPayload;
import de.hsbremen.mkss.events.dto.OrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class OrderEventsConsumer {
    private final OrderEventsProducer orderEventsProducer;

    @Autowired
    public OrderEventsConsumer(OrderEventsProducer orderEventsProducer) {
        this.orderEventsProducer = orderEventsProducer;
    }

    @RabbitListener(queues = "orderQueue")
    public void receiveMessage(EventWithPayload<OrderDTO> event) {
        System.out.println("Received Order Event: " + event);
        var order = event.getPayload();
        System.out.println(order.getStatus());
        if (!order.getStatus().equals("COMMITTED")) {
            return;
        }
        if (getRandomBoolean()) {
            order.setStatus("ACCEPTED");
            System.out.println("Order status changed to ACCEPTED.");
        } else {
            order.setStatus("REJECTED");
            System.out.println("Order status changed to REJECTED.");
        }

        orderEventsProducer.emitUpdateEvent(order);
        System.out.println("Event sent.");
    }

    private static boolean getRandomBoolean() {
        return Math.random() < 0.5;
    }
}
