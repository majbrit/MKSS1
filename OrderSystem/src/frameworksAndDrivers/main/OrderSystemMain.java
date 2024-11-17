package frameworksAndDrivers.main;

import domain.factory.ItemFactory;
import domain.factory.SimpleItemFactory;
import domain.repositoryInterfaces.IOrderRepository;
import interfaceAdapters.controllersAndPresenters.cui.CUI;
import interfaceAdapters.controllersAndPresenters.gui.GuiMenu;

import interfaceAdapters.gateway.OrderRepository;
import javafx.application.Application;
import javafx.stage.Stage;

public class OrderSystemMain extends Application {
    private static final ItemFactory itemFactory = new SimpleItemFactory();
    private static final IOrderRepository orderRepository = new OrderRepository();

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Please use ‘cli’ or ‘gui’ as an argument.");
            return;
        }

        switch (args[0].toLowerCase()) {
            case "cli":
                startCLI();
                break;
            case "gui":
                launch(args);
                break;
            default:
                System.out.println("Invalid argument: Please use ‘cli’ or ‘gui’ as an argument.");
                break;
        }
    }

    private static void startCLI() {
        System.out.println("Cui is starting...");
        CUI cui = new CUI(orderRepository, itemFactory);
        cui.menuLoop();
    }

    @Override
    public void start(Stage stage) throws Exception {
        System.out.println("Gui is starting...");

        //SceneController sceneController = new SceneController(stage);
        GuiMenu guiMenu = new GuiMenu(stage, orderRepository, itemFactory);


    }
}