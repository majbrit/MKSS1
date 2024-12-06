package interfaceAdapters.controllersAndPresenters.cui;

import application.boundaries.*;
import application.usecases.*;
import common.Input;
import domain.factory.ItemFactory;
import domain.order.Order;
import domain.item.Item;
import domain.repositoryInterfaces.IOrderRepository;

import java.util.List;
import java.util.UUID;

public class CUI implements ICreadeOrderOutput, IAddProductOutput, IClearOrdersOutput, IFinishOrderOutput, IGetAllOrdersOutput, IGetAllItemsOutput {

    public UUID orderID;
    private boolean newOrder;

    // use case input boundaries
    private ICreateOrderInput createOrderInput;
    private IAddProductInput addProductInput;
    private IClearOrdersInput clearOrdersInput;
    private IFinishOrderInput finishOrderInput;
    private IGetAllOrdersInput getAllOrdersInput;
    private IGetAllItemsInput getAllItemsInput;

    public CUI(IOrderRepository orderRepository, ItemFactory itemFactory) {
        this.createOrderInput = new CreateOrderUseCase(this, orderRepository);
        this.addProductInput = new AddProductUseCase(this, orderRepository, itemFactory);
        this.clearOrdersInput = new ClearOrdersUseCase(this, orderRepository);
        this.finishOrderInput = new FinishOrderUseCase(this, orderRepository);
        this.getAllOrdersInput = new GetAllOrdersUseCase(this, orderRepository);
         this.getAllItemsInput = new GetAllItemsUseCase(this, orderRepository);
    }

    public void menuLoop() {

        do {
            createOrderInput.createOrder();
        } while (newOrder);

        System.out.println("Thank you for using the order system!");
    }

    private void controlMenu() {
        int input;
        do {
            printMenu();
            input = Input.readInt();
            switch (input) {
                case 0:
                    break;
                case 1:
                    orderProduct();
                    break;
                case 3:
                    viewAllOrders();
                    break;
                case 4:
                    deleteAllOrders();
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
    }

    private void printMenu() {
        System.out.println("Your choice?");
        System.out.println("(0) Finish order");
        System.out.println("(1) Order product");
        System.out.println("(2) Order service");
        System.out.println("(3) View all orders");
        System.out.println("(4) Delete all orders");
    }

    private void orderProduct() {
        System.out.println("Name: ");
        String name = Input.readString();
        System.out.println("Unit price (in cents): ");
        int price = Input.readInt();
        System.out.println("Quantity: ");
        int quantity = Input.readInt();

        addProductInput.addProduct(orderID, name, price, quantity);
    }


    private void printItems() {
        getAllItemsInput.getAllItems(orderID);
    }
    @Override
    public void onGetAllItemsResult(List<Item> items) {
        if (items.isEmpty()) {
            System.out.println("No items available.");
        } else {
            for (Item item : items) {
                System.out.println(item); // Or format the item data as needed
            }
        }
    }

    private void viewAllOrders() {
        getAllOrdersInput.getAllOrders();
    }

    @Override
    public void onGetAllOrdersResult(List<Order> orders) {
        if (orders.isEmpty()) {
            System.out.println("No orders available.");
        } else {
            for (Order order : orders) {
                // Print the order details
                String orderDate = order.getCheckoutDateTime() != null ? String.valueOf(order.getCheckoutDateTime()) : "Not yet checked out";
                System.out.println("Order placed at: " + orderDate);

                for (Item item : order.getItems()) {
                    System.out.println(item.toString());  // Print each item in the order
                }

                // Print the total sum of the order
                System.out.println("Total: " + order.getSumString());
                System.out.println("------------------------------------------------");
            }
        }
    }

    private void deleteAllOrders() {
        clearOrdersInput.clearOrders();

        System.out.println("All orders have been deleted.");
    }
    @Override
    public void onClearOrdersResult(boolean success) {
        if (success) {
            System.out.println("All orders have been cleared successfully.");
        } else {
            System.out.println("Failed to clear orders.");
        }
    }

    private void finishOrder() {

        finishOrderInput.finishOrder(orderID);


    }

    @Override
    public void onFinishOrderResult(boolean success, Order finishedOrder) {
        if (success && finishedOrder != null) {
            System.out.println("Order finished successfully.");
            System.out.println("Checkout Date: " + finishedOrder.getCheckoutDateTime());



            for (Item item : finishedOrder.getItems()) {
                System.out.println(item);
            }

            System.out.println("Total: " + finishedOrder.getSumString());
        } else {
            System.out.println("Failed to finish the order.");
        }
    }


    @Override
    public void onAddProductResult(boolean updated) {
        if (!updated) {
            System.out.println("Product could not be added.");
        }
    }

    @Override
    public void onCreateOrderResult(UUID orderId) {
        this.orderID = orderId;
        if(orderId == null) {
            System.out.println("Order could not be created.");
        }
        controlMenu();
    }


}