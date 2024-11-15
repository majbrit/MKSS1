package domain.factory;

import domain.item.Product;
import domain.item.Service;

public interface ItemFactory {
    Product createProduct(String name, int unitPrice, int quantity);

    Service createService(String name, int persons, int hours);
}
