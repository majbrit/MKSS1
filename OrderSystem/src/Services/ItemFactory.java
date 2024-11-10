package Services;

import Entities.Product;
import Entities.Service;

public interface ItemFactory {
    Product createProduct(String name, int unitPrice, int quantity);

    Service createService(String name, int persons, int hours);
}
