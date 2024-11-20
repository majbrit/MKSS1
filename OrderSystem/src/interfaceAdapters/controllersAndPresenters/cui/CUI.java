package interfaceAdapters.controllersAndPresenters.cui;

import application.boundaries.*;
import application.usecases.*;
import common.Input;
import domain.factory.ItemFactory;
import domain.item.Product;
import domain.item.Service;
import domain.order.Order;
import domain.item.Item;
import domain.repositoryInterfaces.IOrderRepository;
import interfaceAdapters.gateway.OrderRepository;
import application.OrderService;
import domain.factory.SimpleItemFactory;

import java.util.List;
import java.util.UUID;

public class CUI implements ICreadeOrderOutput, IAddProductOutput, IAddServiceOutput, IClearOrdersOutput, IFinishOrderOutput, IGetAllOrdersOutput, IGetOrderSummaryOutput, IGetAllItemsOutput,IGetCheckoutDateTimeOutput {

    //TODO handle everything over use case, so this can be deleted:
   // private final OrderService orderService;


    public UUID orderID;
    private boolean newOrder;

    // use case input boundaries
    private ICreateOrderInput createOrderInput;
    private IAddProductInput addProductInput;
    private IAddServiceInput addServiceInput;
    private IClearOrdersInput clearOrdersInput;
    private IFinishOrderInput finishOrderInput;
    private IGetAllOrdersInput getAllOrdersInput;
    private IGetOrderSummaryInput getOrderSummaryInput;
    private IGetAllItemsinput getAllItemsInput;
    private IGetCheckoutDateTimeInput getCheckoutDateTimeInput;

    public CUI(IOrderRepository orderRepository, ItemFactory itemFactory) {
        this.createOrderInput = new CreateOrderUseCase(this, orderRepository);
        this.addProductInput = new AddProductUseCase(this, orderRepository, itemFactory);
        this.addServiceInput = new AddServiceUseCase(this, orderRepository, itemFactory);
        this.clearOrdersInput = new ClearOrdersUseCase(this, orderRepository);
        this.finishOrderInput = new FinishOrderUseCase(this, orderRepository);
        this.getAllOrdersInput = new GetAllOrdersUseCase(this, orderRepository);
        this.getOrderSummaryInput = new GetOrderSummaryUseCase(this, orderRepository);
        this.getAllItemsInput = new GetAllItemsUseCase(this, orderRepository);
        this.getCheckoutDateTimeInput = new GetCheckoutDateTimeUseCase(this, orderRepository);

        //   this.orderService = OrderService.getInstance();
        //this.orderService.setOrderRepository(new OrderRepository());
        //this.orderService.setItemFactory(new SimpleItemFactory());
    }



    public void menuLoop() {

        do {

            //TODO handle everything over use case, so this can be deleted:
            //  orderID = orderService.newOrder();


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
                case 2:
                    orderService();
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

        //TODO handle everything over use case, so this can be deleted:
        // orderService.addProduct(orderID, name, price, quantity);

        addProductInput.addProduct(orderID, name, price, quantity);
    }

    private void orderService() {
        System.out.println("Service type: ");
        String name = Input.readString();
        System.out.println("Number of persons: ");
        int persons = Input.readInt();
        System.out.println("Hours: ");
        int hours = Input.readInt();

        //TODO handle everything over use case, so this can be deleted:
        // orderService.addService(orderID, name, persons, hours);

        addServiceInput.addService(orderID, name, persons, hours);
    }






    private void printCheckoutDateTime() {
        getCheckoutDateTimeInput.getCheckoutDateTime(orderID);
    }
    @Override
    public void onGetCheckoutDateTimeResult(String checkoutDateTime) {
        System.out.println("Checkout Date and Time: " + checkoutDateTime);
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
        //TODO handle everything over use case, so this can be deleted:
        //List<Order> allOrders = orderService.getAllOrders();
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
        //TODO handle everything over use case, so this can be deleted:
        // orderService.clearAllOrders();

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
        //TODO handle everything over use case, so this can be deleted:
        // orderService.finishOrder(orderID);
        finishOrderInput.finishOrder(orderID);

        printItems();
        printSum();
        printCheckoutDateTime();
    }
    @Override
    public void onFinishOrderResult(boolean success) {
        if (success) {
            System.out.println("Order finished successfully.");
        } else {
            System.out.println("Failed to finish order.");
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


    @Override
    public void onAddServiceResult(boolean updated) {
        if (!updated) {
            System.out.println("Service could not be added.");
        }

    }


    private void printSum() {
        //TODO handle everything over use case, so this can be deleted: orderService.getSumString()
        //System.out.println(orderService.getSumString());
        getOrderSummaryInput.getOrderSummary(orderID);
    }
    @Override
    public void onGetOrderSummaryResult(String orderSummary) {
        System.out.println("Order Summary: " + orderSummary);
    }
}