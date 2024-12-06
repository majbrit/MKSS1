package domain.factory;

import domain.item.LineItem;

public interface ItemFactory {
    LineItem createProduct(String name, int unitPrice, int quantity);
}
