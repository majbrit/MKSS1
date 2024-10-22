package Entities;

public class Service implements Item {

	private String name;
	private int hours, persons;

	public Service(String name, int persons, int hours) {
		this.name = name;
		this.hours = hours;
		this.persons = persons;
	}


	public String getName() {
		return name;
	}

	@Override
	public int getPrice() {
		return 1242 * hours * persons; // Example price calculation
	}

	@Override
	public void print() {
		System.out.println(persons + " persons for " + hours + "h of " + getName()+ " = " + formatPrice(getPrice()));
	}
	private String formatPrice(int priceInCent) {
		return (priceInCent / 100) + "." + (priceInCent % 100 < 10 ? "0" : "")
				+ priceInCent % 100 + " EUR";
	}
}
