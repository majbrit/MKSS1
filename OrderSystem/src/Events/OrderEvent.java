package Events;

import javafx.event.Event;
import javafx.event.EventType;

public class OrderEvent extends Event {
    public static final EventType<OrderEvent> ORDER_UPDATED = new EventType<>(Event.ANY, "ORDER_UPDATED");

    public OrderEvent() {
        super(ORDER_UPDATED);
    }
}