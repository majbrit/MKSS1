package Services;

import Entities.Item;
import Entities.Order;
import Entities.Product;
import Entities.Service;
import java.util.List;


public class OrderService {

	private Order order;


	public void neworder() {
		this.order = new Order();
	}

	public void addProduct(String name, int price, int quantity) {
		order.addProduct(new Product(name, price, quantity));
	}
	public void addService(String name, int persons, int hours) {
		order.addService(new Service(name, persons, hours));
	}

	public List<Item> getitems() {
		order.sortItems();
		return order.getItems();
	}

	public void printOrder() {
		order.setCheckoutDateTime();
		order.printItems();
		order.printSum();
		order.printCheckoutDateTime();
	}
}
