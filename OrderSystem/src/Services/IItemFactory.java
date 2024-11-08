package Services;

import Entities.Item;

public interface IItemFactory {
    Item createProduct(String name, int unitPrice, int quantity);

    Item createService(String name, int persons, int hours);
}
