package Services;

import Entities.Product;
import Entities.Service;

public class SimpleItemFactory implements ItemFactory{
    @Override
    public Product createProduct(String name, int unitPrice, int quantity) {
        return new Product(name, unitPrice, quantity);
    }

    @Override
    public Service createService(String name, int persons, int hours) {
        return new Service(name, persons, hours);
    }
}
