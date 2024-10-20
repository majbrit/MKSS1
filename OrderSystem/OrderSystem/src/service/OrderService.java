package service;

import frontend.Input;
import model.Item;
import model.Order;
import model.Product;
import model.Service;


public class OrderService {

	private Order order;


	public void menuloop() {
		int input;
		do {
			this.order = new Order();
			do {
				printMenu();
				input = Input.readInt();
				switch ( input ) {
					case 0: break ;
					case 1: orderItem("product"); break ;
					case 2: orderItem("service"); break ;
					default: System.out.println("invalid" ); break ;
				}
			} while( input != 0);
			sortItems();
			finishOrder() ;
		} while(moreOrdersRequest());// more orders
	}

	private boolean moreOrdersRequest() {
		printAnotherOrderSelection();
		char input = Input.readChar();
        return input == 'y' || input == 'Y';
    }

	private void printAnotherOrderSelection() {
		System.out.println("\nAnother order?");
		System.out.println("(y) yes");
		System.out.println("(n) no");
	}

	private void printMenu() {
		System.out.println("Your choice?");
		System.out.println("(0) Finish order");
		System.out.println("(1) Order product");
		System.out.println("(2) Order service");
	}

	private void sortItems() {
		this.order.sortItemsByPrice();
	}

	private void orderItem(String itemType) {
		Item item = null;
		switch (itemType) {
			case "product":
				item = readProductDetails();
				break;
			case "service":
				item = readServiceDetails();
				break;
			default:
				break;
		}
		this.order.addItem(item);
	}

	private Product readProductDetails() {
		System.out.println("Name: ");
		String name = Input.readString();
		System.out.println("Unit price (in cents): ");
		int price = Input.readInt();
		System.out.println("Quantity: ");
		int quantity = Input.readInt();
		return new Product(name, price, quantity);
	}

	private Service readServiceDetails() {
		System.out.println("Service type: ");
		String serviceType = Input.readString();
		System.out.println("Number of persons: ");
		int persons = Input.readInt();
		System.out.println("Hours: ");
		int hours = Input.readInt();
		return new Service(serviceType, persons, hours);
	}

	private void finishOrder() {
		// Single Responsibility Principle
		this.order.printItems();
		this.order.printSum();
		this.order.setCheckoutDateTime();
		this.order.printCheckoutDateTime();

	}

	private String formatPrice(int priceInCent) {
		return String.format("%.2f EUR", priceInCent / 100.0);
	}
}
