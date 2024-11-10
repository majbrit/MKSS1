package Ui.Cui;

import Common.Input;
import Services.IItemFactory;
import Entities.Item;
import Services.OrderService;

import java.util.List;

public class CUI {
    private final OrderService orderService;

    public CUI() {
        this.orderService = OrderService.getInstance();
    }

    public void setItemFactory(IItemFactory itemFactory){
        orderService.setItemFactory(itemFactory);
    }

    public void menuLoop() {
        int input;
        boolean newOrder;
        do {
            orderService.newOrder();
            do {
                printMenu();
                input = Input.readInt();
                switch (input) {
                    case 0:
                        break;
                    case 1:
                        orderProduct();
                        break;
                    case 2:
                        orderService();
                        break;
                    default:
                        System.out.println("invalid");
                        break;
                }
            } while (input != 0);

            finishOrder();

            System.out.println("Would you like to place another order? (yes/no): ");
            String response = Input.readString().toLowerCase();
            newOrder = response.equals("yes");

        } while (newOrder);

        System.out.println("Thank you for using the order system!");
    }

    private void printMenu() {
        System.out.println("Your choice?");
        System.out.println("(0) Finish order");
        System.out.println("(1) Order product");
        System.out.println("(2) Order service");
    }

    private void orderProduct() {
        System.out.println("Name: ");
        String name = Input.readString();
        System.out.println("Unit price (in cents): ");
        int price = Input.readInt();
        System.out.println("Quantity: ");
        int quantity = Input.readInt();

        orderService.addProduct(name, price, quantity);
    }

    private void orderService() {
        System.out.println("Service type: ");
        String name = Input.readString();
        System.out.println("Number of persons: ");
        int persons = Input.readInt();
        System.out.println("Hours: ");
        int hours = Input.readInt();

        orderService.addService(name, persons, hours);
    }

    private void finishOrder() {
        printItems();
        printSum();
        printCheckoutDateTime();
    }

    private void printSum() {
        System.out.println(orderService.getSumString());
    }

    private void printCheckoutDateTime() {
        String date = orderService.checkoutDateTime();
        System.out.println("Checkout at " + date);
    }

    private void printItems() {
        List<Item> items = orderService.getItems();
        for (Item item : items) {
            System.out.println(item);
        }
    }


}