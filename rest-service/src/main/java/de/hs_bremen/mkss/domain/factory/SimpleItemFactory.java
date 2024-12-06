package de.hs_bremen.mkss.domain.factory;

import de.hs_bremen.mkss.domain.item.LineItem;
import de.hs_bremen.mkss.domain.order.Order;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class SimpleItemFactory implements ItemFactory {
    public LineItem createProduct(String name, int unitPrice, int quantity, Order order) {
        return new LineItem(name, unitPrice, quantity, order);
    }
}