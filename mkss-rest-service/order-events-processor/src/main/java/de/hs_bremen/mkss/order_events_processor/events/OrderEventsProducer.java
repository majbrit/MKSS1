package de.hs_bremen.mkss.order_events_processor.events;

import de.hsbremen.mkss.events.CrudEventProducer;
import de.hsbremen.mkss.events.Event;
import de.hsbremen.mkss.events.EventWithPayload;
import de.hsbremen.mkss.events.dto.ItemDTO;
import de.hsbremen.mkss.events.dto.OrderDTO;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


// XYZ replaced with Order
@Component
public class OrderEventsProducer implements CrudEventProducer<OrderDTO> {

	private final AmqpTemplate amqpTemplate;

	// Reply exchange and queue
	@Value("${my.rabbitmq.reply.exchange}")
	private String replyExchange;

	@Autowired
	public OrderEventsProducer(AmqpTemplate amqpTemplate) {
		this.amqpTemplate = amqpTemplate;
	}

	private EventWithPayload<OrderDTO> buildEvent(Event.EventType type, OrderDTO orderDTO) {
        return new EventWithPayload.Builder<OrderDTO>()
                .setType(type)
                .setPayload(orderDTO)
                .build();
	}

	@Override
	public void emitCreateEvent(OrderDTO orderDTO) {
		EventWithPayload<OrderDTO> event = buildEvent(Event.EventType.CREATED, orderDTO);

		//  send event to RabbitMQ exchange
		sendEventToRabbitMQ(event);
	}

	@Override
	public void emitUpdateEvent(OrderDTO orderDTO) {
		// Implementation for update events (e.g. changed order)
		EventWithPayload<OrderDTO> event = buildEvent(Event.EventType.CHANGED, orderDTO);

		try {
			sendEventToRabbitMQ(event);
			System.out.println("sent update Event: " + event);
		} catch (Exception e) {
			System.out.println("Error sending event to RabbitMQ: " + e.getMessage());
			e.printStackTrace();
		}
	}

	@Override
	public void emitDeleteEvent(OrderDTO orderDTO) {
		// Implementation for delete events (e.g. deleted order)
		EventWithPayload<OrderDTO> event = buildEvent(Event.EventType.DELETED, orderDTO);
		sendEventToRabbitMQ(event);
	}

	private void sendEventToRabbitMQ(EventWithPayload<OrderDTO> event) {

		String routingKey = switch (event.getType()) {
            case CREATED -> "emit-order-created";
            case CHANGED -> "emit-order-updated";
            case DELETED -> "emit-order-deleted";
        };

		try {
			amqpTemplate.convertAndSend(replyExchange, routingKey, event);
			System.out.println("Sent Here: " + event);
		} catch (Exception e) {
			System.out.println("Error sending event to RabbitMQ: " + e.getMessage());
			e.printStackTrace();
		}
        amqpTemplate.convertAndSend(replyExchange, routingKey, event);
		System.out.println("Sent event = " + event + " using exchange " + replyExchange + ", routing key " + routingKey);
	}
}
