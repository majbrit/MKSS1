package de.hs_bremen.mkss.interfaceAdapters.controllersAndPresenters.cui;

import de.hs_bremen.mkss.application.boundaries.*;
import de.hs_bremen.mkss.common.Input;
import de.hs_bremen.mkss.domain.order.Order;
import de.hs_bremen.mkss.domain.item.Item;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@Profile("cli")
public class CUI implements ICreateOrderOutput, IAddProductOutput, IClearOrdersOutput, IFinishOrderOutput, IGetAllOrdersOutput, IGetAllItemsOutput {

    private Order order;
    private boolean newOrder;

    // use case input boundaries
    private ICreateOrderInput createOrderInput;
    private IAddProductInput addProductInput;
    private IClearOrdersInput clearOrdersInput;
    private IFinishOrderInput finishOrderInput;
    private IGetAllOrdersInput getAllOrdersInput;
    private IGetAllItemsInput getAllItemsInput;

    @Autowired
    public CUI(@Lazy @Qualifier("createOrderUseCase")ICreateOrderInput createOrderInput,
               @Lazy @Qualifier("addProductUseCase")IAddProductInput addProductInput,
               @Lazy @Qualifier("clearOrdersUseCase")IClearOrdersInput clearOrdersInput,
               @Lazy @Qualifier("finishOrderUseCase")IFinishOrderInput finishOrderInput,
               @Lazy @Qualifier("getAllOrdersUseCase")IGetAllOrdersInput getAllOrdersInput,
               @Lazy @Qualifier("getAllItemsUseCase")IGetAllItemsInput getAllItemsInput) {
        this.createOrderInput = createOrderInput;
        this.addProductInput = addProductInput;
        this.clearOrdersInput = clearOrdersInput;
        this.finishOrderInput = finishOrderInput;
        this.getAllOrdersInput = getAllOrdersInput;
        this.getAllItemsInput = getAllItemsInput;
    }

    /*
    @Autowired
    public CUI(IOrderRepository orderRepository, ItemFactory itemFactory) {
        this.createOrderInput = new CreateOrderUseCase(this, orderRepository);
        this.addProductInput = new AddProductUseCase(this, orderRepository, itemFactory);
        this.clearOrdersInput = new ClearOrdersUseCase(this, orderRepository);
        this.finishOrderInput = new FinishOrderUseCase(this, orderRepository);
        this.getAllOrdersInput = new GetAllOrdersUseCase(this, orderRepository);
         this.getAllItemsInput = new GetAllItemsUseCase(this, orderRepository);
    }*/

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

        addProductInput.addProduct(order, name, price, quantity);
    }


    private void printItems() {
        getAllItemsInput.getAllItems(order);
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
    @Transactional
    public void onGetAllOrdersResult(List<Order> orders) {
        if (orders.isEmpty()) {
            System.out.println("No orders available.");
        } else {
            for (Order order : orders) {
                // Force initialization of the 'items' collection if it's lazily loaded
                if (order.getItems() != null) {
                    order.getItems().size();  // This will initialize the collection
                }

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

        finishOrderInput.finishOrder(order);


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
    public void onAddProductResult(Order order) {
        if (order == null) {
            System.out.println("Product could not be added.");
        }
    }

    @Override
    public void onCreateOrderResult(Order order) {

        if(order == null) {
            System.out.println("Order could not be created.");
        } else {
            this.order = order;
        }
        controlMenu();
    }


}