package Ui;

import Common.Input;
import Entities.Item;
import Entities.Order;
import Entities.Product;
import Entities.Service;
import Services.OrderSystem;

import java.util.ArrayList;
import java.util.List;

public class CUI {

    private OrderSystem OrderSystem;
    private List<Item> items = new ArrayList<>();

    public CUI() {
        this.OrderSystem = new OrderSystem();

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
        String Name = Input.readString();
        System.out.println("Unit price (in cents): ");
        int price = Input.readInt();
        System.out.println("Quantity: ");
        int quantity = Input.readInt();

        OrderSystem.addProduct(Name, price, quantity);
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
        items = OrderSystem.getitems();
        int sum = 0;
        for (Item item : items) {
            item.print();
            sum += item.getPrice();
        }
        System.out.println("Total Sum: " + formatPrice(sum));
    }
    private String formatPrice(int priceInCent) {
        return (priceInCent / 100) + "." + (priceInCent % 100 < 10 ? "0" : "")
                + priceInCent % 100 + " EUR";
    }

}
