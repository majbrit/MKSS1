package de.hs_bremen.mkss.application.boundaries;

import de.hs_bremen.mkss.domain.order.Order;
import org.springframework.stereotype.Component;

@Component
public interface ICreateOrderInput {
    public Order createOrder();
}
