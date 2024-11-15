package application.boundaries;

import domain.item.Product;

public interface IAddProductOutput {
    public Product onAddProductResult(Product product);
}
