package de.hs_bremen.mkss.order_events_processor.service;

import de.hs_bremen.mkss.order_events_processor.events.OrderEventsProducer;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import de.hsbremen.mkss.events.EventWithPayload;
import de.hsbremen.mkss.events.dto.OrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

@Component
public class OrderEventsConsumer {
    private final OrderEventsProducer orderEventsProducer;

    @Autowired
    public OrderEventsConsumer(OrderEventsProducer orderEventsProducer) {
        this.orderEventsProducer = orderEventsProducer;
    }

    @RabbitListener(queues = "orderQueue")
    public void receiveMessage(EventWithPayload<OrderDTO> event, @Header("amqp_receivedRoutingKey") String routingKey) {
        System.out.println("Received Order Event: " + event + " with routing key: " + routingKey);

        var order = event.getPayload();

        switch (routingKey) {
            case "order-created" -> processOrderCreatedEvent(order);
            case "order-updated" -> processOrderUpdatedEvent(order);
            case "order-deleted" -> processOrderDeletedEvent(order);
        }
    }


    private void processOrderCreatedEvent(OrderDTO order) {
        orderEventsProducer.emitUpdateEvent(order);

        System.out.println("Processing CREATED event for order: " + order);
    }

    private void processOrderUpdatedEvent(OrderDTO order) {
        orderEventsProducer.emitUpdateEvent(order);

        System.out.println("Processing UPDATED event for order: " + order);
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
    }

    private void processOrderDeletedEvent(OrderDTO order) {
        orderEventsProducer.emitUpdateEvent(order);

        System.out.println("Processing DELETED event for order: " + order);
    }

    private static boolean getRandomBoolean() {
        return Math.random() < 0.5;
    }
}
