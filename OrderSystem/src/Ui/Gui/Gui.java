package Ui.Gui;
import Services.IItemFactory;
import Services.OrderService;
import Services.SimpleItemFactory;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Gui extends Application {
    private Stage primaryStage;
    private Scene scene;
    private Menu menu;
    private static IItemFactory itemFactory;
    public static OrderService orderService;

    public static void setItemFactory(IItemFactory itemFactory) {
        Gui.itemFactory = itemFactory;
        Gui.orderService = OrderService.getInstance();
        Gui.orderService.setItemFactory(itemFactory);
        Gui.orderService.newOrder();
        System.out.println(orderService);
    }

    @Override
    public void start(Stage primaryStage) {


        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Order System GUI");

        menu = new Menu(orderService);
        scene = new Scene(menu, 400, 400);
        this.primaryStage.setScene(scene);
        this.primaryStage.setMaximized(true);

        this.primaryStage.setScene(scene);
        this.primaryStage.show();
    }


}