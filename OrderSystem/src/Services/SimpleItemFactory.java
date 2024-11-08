package Services;

import Entities.Item;
import Entities.Product;
import Entities.Service;

public class SimpleItemFactory implements  IItemFactory {
    public Item createProduct(String name, int unitPrice, int quantity) {
        return new Product(name, unitPrice, quantity);
    }

    public Item createService(String name, int persons, int hours){
        return new Service(name, persons, hours);
    }
}
