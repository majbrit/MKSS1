package Ui.Gui;

import Services.OrderService;
import javafx.application.Application;
import javafx.stage.Stage;

public class GuiStart extends Application {


    @Override
    public void start(Stage primaryStage) {
        OrderService.getInstance().newOrder();
        GuiMenu guiMenu = new GuiMenu();
        guiMenu.show();


    }

}
