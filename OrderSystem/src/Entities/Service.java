package Entities;

public class Service extends Item {
    private int hours, persons;

    public Service(String name, int persons, int hours) {
        super(name, 1242);
        this.hours = hours;
        this.persons = persons;
    }

    public int getHours() {
        return hours;
    }

    public int getPersons() {
        return persons;
    }

    @Override
    public int getTotalPrice() {
        return getUnitPrice() * getHours() * getPersons();
    }

    //TODO find other solution for gui


    @Override
    public String toString() {
        return getPersons() + " persons for " + getHours() + "h of " + getName()+ " = " + formatPrice(getTotalPrice());
    }
}