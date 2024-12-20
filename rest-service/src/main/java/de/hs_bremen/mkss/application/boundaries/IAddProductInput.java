package de.hs_bremen.mkss.application.boundaries;

import de.hs_bremen.mkss.domain.item.Item;
import de.hs_bremen.mkss.domain.order.Order;
import org.springframework.stereotype.Component;



@Component
public interface IAddProductInput {
    public Item addProduct(Order order, String name, int price, int quantity);
}
