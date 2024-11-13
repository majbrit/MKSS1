package Ui.Gui;

import Entities.Item;
import Services.OrderService;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class GuiOrderSummary {
    Stage stage;

    @FXML
    private TableView basketList;
    @FXML
    private Label sum;
    @FXML
    private Label checkoutDateTime;

    private SceneController sceneController;

    private Scene scene;


    public GuiOrderSummary(SceneController sceneController) {
        this.sceneController = sceneController;
    }

    public void startOrderSummary() {
        createBill();
    }

    public void init() {
        TableColumn<Item, String> descriptionColumn = new TableColumn<>("Description");
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        descriptionColumn.setPrefWidth(300);
        descriptionColumn.setResizable(true);
        basketList.getColumns().addAll(descriptionColumn);
    }

    private void createBill() {
        OrderService.getInstance().finishOrder();
        List<Item> items = OrderService.getInstance().getItems();
        String checkOut = OrderService.getInstance().getCheckoutDateTime();
        String sumString = OrderService.getInstance().getSumString();

        ArrayList<Item> arrayItems = new ArrayList<>(items);
        basketList.setItems(FXCollections.<Item>observableArrayList(arrayItems));

        sum.setText("Sum: " + sumString);
        checkoutDateTime.setText("Checkout at " + checkOut);
    }

    @FXML
    private void newOrder(){
        basketList.getItems().clear();
        OrderService.getInstance().newOrder();
        sceneController.startGuiMenu();
    }
}
