package Main;

import Services.IItemFactory;
import Services.OrderService;
import Services.SimpleItemFactory;
import Ui.Cui.CUI;
import Ui.Gui.Gui;
import Ui.Gui.GuiMenu;
import Ui.Gui.GuiStart;
import javafx.application.Application;
import javafx.stage.Stage;


public class OrderSystemMain extends Application {
    private static IItemFactory itemFactory = new SimpleItemFactory();
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
        cui.setItemFactory(itemFactory);
        cui.menuLoop();
    }



    @Override
    public void start(Stage stage) throws Exception {
        System.out.println("Gui is starting...");

        OrderService.getInstance().setItemFactory(itemFactory);
        //  GuiStart.launch(args);

        OrderService.getInstance().newOrder();
        GuiMenu guiMenu = new GuiMenu();
        guiMenu.show();
    }
}