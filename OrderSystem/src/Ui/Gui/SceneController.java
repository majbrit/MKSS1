package Ui.Gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.IOException;

public class SceneController {
    private GuiMenu guiMenu;
    private GuiOrderSummary guiOrderSummary;
    private Stage stage;
    private Scene menuScene;
    private Scene orderScene;
    private FXMLLoader menuFxmlLoader;
    private FXMLLoader orderFxmlLoader;

    public SceneController(Stage stage) {
        this.stage = stage;
        guiMenu = new GuiMenu(this);
        guiOrderSummary = new GuiOrderSummary(this);
        menuFxmlLoader = new FXMLLoader(getClass().getResource("GuiMenu.fxml"));
        orderFxmlLoader = new FXMLLoader(getClass().getResource("GuiOrderSummary.fxml"));
        menuFxmlLoader.setController(guiMenu);
        orderFxmlLoader.setController(guiOrderSummary);
        try {
            menuScene = new Scene((Parent) menuFxmlLoader.load());
            orderScene = new Scene((Parent) orderFxmlLoader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }




        guiMenu.init();
        guiOrderSummary.init();


        startGuiMenu();
    }

    public void startGuiMenu() {
        startStage(menuScene);
        guiMenu.startMenu();
    }
    public void startGuiOrderSummary() {
        startStage(orderScene);
        guiOrderSummary.startOrderSummary();
    }

    private void startStage(Scene scene) {

        stage.setScene(scene);
        stage.setMaximized(true);
        stage.setTitle("Order System");

        stage.show();
    }

}
