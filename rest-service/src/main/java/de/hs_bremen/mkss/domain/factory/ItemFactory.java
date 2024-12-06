package de.hs_bremen.mkss.domain.factory;

import de.hs_bremen.mkss.domain.item.LineItem;

public interface ItemFactory {
    LineItem createProduct(String name, int unitPrice, int quantity);
}
