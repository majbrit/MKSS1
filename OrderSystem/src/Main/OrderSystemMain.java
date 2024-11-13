package Main;

import Services.ItemFactory;
import Services.OrderService;
import Services.SimpleItemFactory;
import Ui.Cui.CUI;
import Ui.Gui.GuiMenu;

import Ui.Gui.SceneController;

import javafx.application.Application;
import javafx.stage.Stage;

public class OrderSystemMain extends Application {
    private static final ItemFactory itemFactory = new SimpleItemFactory();

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
        CUI cui = new CUI();
        cui.menuLoop();
    }

    @Override
    public void start(Stage stage) throws Exception {
        System.out.println("Gui is starting...");
        OrderService.getInstance().setItemFactory(itemFactory);
        OrderService.getInstance().newOrder();
        SceneController sceneController = new SceneController(stage);
        //GuiMenu guiMenu = new GuiMenu(stage);
        //guiMenu.show();
        //Gui gui = new Gui(stage);
        //gui.show();

    }
}