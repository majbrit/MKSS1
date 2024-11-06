package Services;

import Entities.Item;

public interface IItemFactory {
    Item CreateProduct(String name, int unitPrice, int quantity);

    Item CreateService(String name, int persons, int hours);
}
