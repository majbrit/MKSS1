package application.boundaries;

import java.util.UUID;

public interface IAddProductInput {
    public void addProduct(UUID id, String name, int price, int quantity);
}
