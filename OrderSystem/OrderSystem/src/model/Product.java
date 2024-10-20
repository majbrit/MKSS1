package model;

public class Product extends Item {


	private int unitPrice, quantity;

	public Product(String name, int unitPrice, int quantity) {
		super(name, unitPrice * quantity);
		this.unitPrice = unitPrice;
		this.quantity = quantity;
	}

	@Override
	public int getPrice() {
		return unitPrice * quantity;
	}
	public String getDescription() {
		return quantity + " * " + getName();
	}
}