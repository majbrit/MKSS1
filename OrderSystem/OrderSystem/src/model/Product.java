package model;

public class Product extends Item {


	private int quantity;

	public Product(String name, int pricePerUnit, int quantity) {
		super(name, pricePerUnit);
		this.pricePerUnit = pricePerUnit;
		this.quantity = quantity;
	}

	public int getQuantity() {
		return quantity;
	}

	@Override
	public int getTotalPrice() {
		return getPricePerUnit() * getQuantity();
	}
	public String getDescription() {
		return getQuantity() + " * " + getName();
	}
}