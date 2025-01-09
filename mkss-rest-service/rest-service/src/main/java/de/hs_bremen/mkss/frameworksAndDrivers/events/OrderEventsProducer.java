package de.hs_bremen.mkss.frameworksAndDrivers.events;

import de.hs_bremen.mkss.domain.item.Item;
import de.hs_bremen.mkss.domain.item.LineItem;
import de.hsbremen.mkss.events.CrudEventProducer;
import de.hsbremen.mkss.events.Event;
import de.hsbremen.mkss.events.EventWithPayload;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import de.hs_bremen.mkss.domain.order.Order;
import de.hsbremen.mkss.events.dto.OrderDTO;
import de.hsbremen.mkss.events.dto.ItemDTO;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


// XYZ replaced with Order
@Component
public class OrderEventsProducer implements CrudEventProducer<Order> {

	private final AmqpTemplate amqpTemplate;

    @Value("${my.rabbitmq.an.exchange}")
    String orderExchange;

    /*@Value("${my.rabbitmq.a.routing.key}")
    String orderRoutingKey;*/


	public OrderEventsProducer(AmqpTemplate amqpTemplate) {
		this.amqpTemplate = amqpTemplate;
	}

	private OrderDTO convertToDTO(Order order) {
		OrderDTO orderDTO = new OrderDTO();
		orderDTO.setId(order.getId());
		orderDTO.setCustomerName(order.getCustomerName());
		orderDTO.setCheckoutDateTime(order.getCheckoutDateTime() != null
				? new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(order.getCheckoutDateTime())
				: null);
		orderDTO.setStatus(order.getStatus() != null ? order.getStatus().name() : null);
		orderDTO.setTotalSum(order.getSum());
		List<ItemDTO> itemDTOs = new ArrayList<>();
		for (Item item : order.getItems()) {
			if(item instanceof LineItem) {
				LineItem lineItem = (LineItem) item;
				ItemDTO itemDTO = new ItemDTO();
				itemDTO.setId(lineItem.getId());
				itemDTO.setName(lineItem.getName());
				itemDTO.setUnitPrice(lineItem.getUnitPrice());
				itemDTO.setTotalPrice(lineItem.getTotalPrice());
				itemDTO.setQuantity(lineItem.getQuantity());
				itemDTOs.add(itemDTO);
			}
		}
		orderDTO.setItems(itemDTOs);
		return orderDTO;
	}

	private EventWithPayload<OrderDTO> buildEvent(Event.EventType type, Order order) {
		OrderDTO orderDTO = convertToDTO(order);
		EventWithPayload<OrderDTO> event = new EventWithPayload.Builder<OrderDTO>()
				.setType(type)
				.setPayload(orderDTO)
				.build();
		return event;
	}

	@Override
	public void emitCreateEvent(Order order) {
		EventWithPayload<OrderDTO> event = buildEvent(Event.EventType.CREATED, order);
	
		//  send event to RabbitMQ exchange
		sendEventToRabbitMQ(event);
	}

	@Override
	public void emitUpdateEvent(Order order) {
		// Implementation for update events (e.g. changed order)
		EventWithPayload<OrderDTO> event = buildEvent(Event.EventType.CHANGED, order);
		sendEventToRabbitMQ(event);
	}

	@Override
	public void emitDeleteEvent(Order order) {
		// Implementation for delete events (e.g. deleted order)
		EventWithPayload<OrderDTO> event = buildEvent(Event.EventType.DELETED, order);
		sendEventToRabbitMQ(event);
	}

	private void sendEventToRabbitMQ(EventWithPayload<OrderDTO> event) {
		amqpTemplate.convertAndSend(orderExchange, "", event);
		// routing key is empty string because of fanout
		System.out.println("Sent event = " + event + " using exchange " + orderExchange);
	}
}
