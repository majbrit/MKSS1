package Services;

import Entities.Item;
import Entities.Order;
import Entities.Product;
import Entities.Service;

import java.util.Date;
import java.util.List;


public class OrderService {

	private Order order;
	private ItemFactory itemFactory;

	public void setItemFactory(ItemFactory factory) {
		this.itemFactory = factory;
	}


	public void neworder() {
		this.order = new Order();
	}

	public void addProduct(String name, int unitPrice, int quantity) {
		if (itemFactory != null) {
			order.addProduct(itemFactory.createProduct(name, unitPrice, quantity));
		}
	}

	public void addService(String name, int persons, int hours) {
		if (itemFactory != null) {
			order.addService(itemFactory.createService(name, persons, hours));
		}
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
