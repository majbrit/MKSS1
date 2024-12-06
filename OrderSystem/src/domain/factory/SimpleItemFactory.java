package domain.factory;

import domain.item.LineItem;

public class SimpleItemFactory implements ItemFactory {
    public LineItem createProduct(String name, int unitPrice, int quantity) {
        return new LineItem(name, unitPrice, quantity);
    }
}