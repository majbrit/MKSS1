package de.hs_bremen.mkss.domain.factory;

import de.hs_bremen.mkss.domain.item.LineItem;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class SimpleItemFactory implements ItemFactory {
    public LineItem createProduct(String name, int unitPrice, int quantity) {
        return new LineItem(name, unitPrice, quantity);
    }
}