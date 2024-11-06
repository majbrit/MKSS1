package Ui;

import Common.Input;
import Services.IItemFactory;
import Entities.Item;
import Services.OrderSystem;
import java.util.List;

public class CUI {
    private final OrderSystem OrderSystem;

    public CUI() {
        this.OrderSystem = new OrderSystem();
    }

    public void SetItemFactory(IItemFactory itemFactory){
        OrderSystem.SetItemFactory(itemFactory);
    }

    public void menuloop() {
        int input;
        boolean newOrder;
        do {
            OrderSystem.neworder();
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

        OrderSystem.addProduct(name, price, quantity);
    }

    private void orderService() {
        System.out.println("Service type: ");
        String name = Input.readString();
        System.out.println("Number of persons: ");
        int persons = Input.readInt();
        System.out.println("Hours: ");
        int hours = Input.readInt();

        OrderSystem.addService(name, persons, hours);
    }

    private void finishOrder() {
        printitems();
        printsum();
        printCheckoutDateTime();
    }

    private void printsum() {
        int sum = OrderSystem.getsum();
        System.out.println("Sum: " + formatPrice(sum));
    }

    private void printCheckoutDateTime() {
        String date = OrderSystem.CheckoutDateTime();
        System.out.println("Checkout at " + date);
    }

    private void printitems() {
        List<Item> items = OrderSystem.getitems();
        for (Item item : items) {
            System.out.println(item.getDescription() + " = " + formatPrice(item.getTotalPrice()));
        }
    }

    private String formatPrice(int priceInCent) {
        return String.format("%.2f EUR", priceInCent / 100.0);
    }
}