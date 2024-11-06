package Services;

import Entities.Item;
import Entities.Product;
import Entities.Service;

public class SimpleItemFactory implements  IItemFactory {
    public Item CreateProduct(String name, int unitPrice, int quantity) {
        return new Product(name, unitPrice, quantity);
    }

    public Item CreateService(String name, int persons, int hours){

        return new Service(name, persons, hours);
    }
}
