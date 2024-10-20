package model;

public class Service extends Item {

	private int hours, persons;

	public Service(String name, int persons, int hours) {
		super(name, 1242 * hours * persons);
		this.hours = hours;
		this.persons = persons;
	}

	@Override
	public int getPrice() {
		return 1242 * hours * persons;
	}
	public String getDescription() {
		return persons + " persons for " + hours + "h of " + getName();
	}
}