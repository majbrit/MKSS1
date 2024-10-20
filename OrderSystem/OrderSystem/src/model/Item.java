package model;

// Interface or abstract class?
// Abstract class is useful here, as there are many common functions and properties that can be implemented here in summarised form
public abstract class Item implements Comparable<Item>{
    protected String name;
    protected int totalPrice;

    public Item(String name, int totalPrice) {
        this.name = name;
        this.totalPrice = totalPrice;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return totalPrice;
    }

    public String getDescription() {
        return name;
    }

    @Override
    public int compareTo(Item i) {
        return Double.compare(getPrice(), i.getPrice());
    }
}