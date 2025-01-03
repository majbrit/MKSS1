package de.hs_bremen.mkss.frameworksAndDrivers.events;

import de.hsbremen.mkss.events.CrudEventProducer;
import de.hsbremen.mkss.events.Event;
import de.hsbremen.mkss.events.EventWithPayload;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import de.hs_bremen.mkss.domain.order.Order;


// XYZ replaced with Order
@Component
public class OrderEventsProducer implements CrudEventProducer<Order> {

	private AmqpTemplate amqpTemplate;

    @Value("${my.rabbitmq.an.exchange}")
    String orderExchange;

    /*@Value("${my.rabbitmq.a.routing.key}")
    String orderRoutingKey;*/


	public OrderEventsProducer(AmqpTemplate amqpTemplate) {
		this.amqpTemplate = amqpTemplate;
	}


	private EventWithPayload<Order> buildEvent(Event.EventType type, Order order) {
		EventWithPayload<Order> event = new EventWithPayload.Builder<Order>()
				.setType(type)
				.setPayload(order)
				.build();
		return event;
	}

	@Override
	public void emitCreateEvent(Order order) {
		EventWithPayload<Order> event = buildEvent(Event.EventType.CREATED, order);
	
		//  send event to RabbitMQ exchange
		sendEventToRabbitMQ(event);
	}

	@Override
	public void emitUpdateEvent(Order order) {
		// Implementation for update events (e.g. changed order)
		EventWithPayload<Order> event = buildEvent(Event.EventType.CHANGED, order);
		sendEventToRabbitMQ(event);
	}

	@Override
	public void emitDeleteEvent(Order order) {
		// Implementation for delete events (e.g. deleted order)
		EventWithPayload<Order> event = buildEvent(Event.EventType.DELETED, order);
		sendEventToRabbitMQ(event);
	}

	private void sendEventToRabbitMQ(EventWithPayload<Order> event) {
		amqpTemplate.convertAndSend(orderExchange, "", event);
		// routing key is empty string because of fanout
		System.out.println("Sent event = " + event + " using exchange " + orderExchange);
	}
}
