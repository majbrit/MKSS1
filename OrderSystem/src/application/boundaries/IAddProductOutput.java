package application.boundaries;

import domain.item.Product;

public interface IAddProductOutput {
    public void onAddProductResult(boolean success);
}
