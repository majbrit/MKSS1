package Services;

import Entities.Item;
import Entities.Order;
import java.util.List;

public class OrderService {
    private Order order;

    private IItemFactory itemFactory;

    public void SetItemFactory(IItemFactory itemFactory){
        this.itemFactory = itemFactory;
    }

    public void neworder() {
        this.order = new Order();
    }

    public void addProduct(String name, int price, int quantity) {
        order.addProduct(itemFactory.CreateProduct(name, price, quantity));
    }

    public void addService(String name, int persons, int hours) {
        order.addService(itemFactory.CreateService(name, persons, hours));
    }

    public List<Item> getitems() {
        order.sortItems();
        return order.getItems();
    }

    public String CheckoutDateTime (){
        order.setCheckoutDateTime();
        return order.CheckoutDateTime();
    }

    public int getsum (){
        return order.getSum();
    }
}