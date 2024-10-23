package model;

// Interface or abstract class?
// Abstract class is useful here, as there are many common functions and properties that can be implemented here in summarised form
public abstract class Item implements Comparable<Item>{
    protected String name;
    protected int totalPrice;
    protected int pricePerUnit;

    public Item(String name, int pricePerUnit) {
        this.name = name;
        this.pricePerUnit = pricePerUnit;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public String getName() {
        return name;
    }

    public int getPricePerUnit() {
        return pricePerUnit;
    }

    public String getDescription() {
        return name;
    }

    @Override
    public int compareTo(Item i) {
        return Double.compare(getTotalPrice(), i.getTotalPrice());
    }
}