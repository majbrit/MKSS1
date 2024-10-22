package Entities;

public class Product implements Item {

	private String name;
	private int unitPrice;
	private int quantity;

	public Product(String name, int unitPrice, int quantity) {
		this.name = name;
		this.unitPrice = unitPrice;
		this.quantity = quantity;
	}

	public String getName() {
		return name;
	}




	@Override
	public int getPrice() {
		return unitPrice * quantity; // Total price calculation
	}

	@Override
	public void print() {
		System.out.println(quantity + " * " + getName()+ " = " + formatPrice(getPrice()));

	}
	private String formatPrice(int priceInCent) {
		return (priceInCent / 100) + "." + (priceInCent % 100 < 10 ? "0" : "")
				+ priceInCent % 100 + " EUR";
	}
}
