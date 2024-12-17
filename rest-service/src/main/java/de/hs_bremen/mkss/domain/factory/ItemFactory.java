package de.hs_bremen.mkss.domain.factory;

import de.hs_bremen.mkss.domain.item.LineItem;
import de.hs_bremen.mkss.domain.order.Order;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;


@Component
public interface ItemFactory {
    LineItem createProduct(String name, int unitPrice, int quantity, Order order);
}
