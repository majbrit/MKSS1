package domain.factory;

import domain.item.Product;
import domain.item.Service;

public class SimpleItemFactory implements ItemFactory {
    public Product createProduct(String name, int unitPrice, int quantity) {
        return new Product(name, unitPrice, quantity);
    }

    public Service createService(String name, int persons, int hours){
        return new Service(name, persons, hours);
    }
}