package Services;
import Entities.Item;
import java.util.List;

public class OrderSystem {
    private final OrderService OrderService;

    public OrderSystem() {
        this.OrderService =  new OrderService();
    }

    public void SetItemFactory(IItemFactory itemFactory){
        OrderService.SetItemFactory(itemFactory);
    }

    public void neworder() {
        OrderService.neworder();
    }

    public void addProduct(String name, int price, int quantity) {
        OrderService.addProduct(name,price,quantity);
    }

    public void addService(String name, int persons, int hours) {
        OrderService.addService(name,persons,hours);
    }

    public List<Item> getitems() {
        return OrderService.getitems();
    }

    public String CheckoutDateTime (){
        return OrderService.CheckoutDateTime();
    }

    public int getsum (){
        return OrderService.getsum();
    }
}