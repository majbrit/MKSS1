package Ui.Gui;

import Entities.Item;
import Services.OrderService;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GuiMenu{
    private Scene scene;
    private List<Item> items;

    // product
    @FXML
    private TextField productName;
    @FXML
    private TextField productPrice;
    @FXML
    private TextField productQuantity;
    @FXML
    private Label productError;

    // service
    @FXML
    private TextField serviceType;
    @FXML
    private TextField numberOfPersons;
    @FXML
    private TextField hours;
    @FXML
    private Label serviceError;


    // shopping list
    @FXML
    private TableView<Item> basketList;


    private Stage stage;
    private SceneController sceneController;

    public GuiMenu(SceneController sceneController){
        this.sceneController = sceneController;
    }

    public void init() {
        TableColumn<Item, String> descriptionColumn = new TableColumn<>("Description");
        descriptionColumn.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().toString()));

        descriptionColumn.setPrefWidth(300);
        descriptionColumn.setResizable(true);
        basketList.getColumns().add(descriptionColumn);
    }

    @FXML
    private void addProduct() {
        try {
            productError.setText("");
            String name = productName.getText();
            int price = Integer.parseInt(productPrice.getText());
            int quantity = Integer.parseInt(productQuantity.getText());
            OrderService.getInstance().addProduct(name, price, quantity);
            updateList();
            productName.clear();
            productPrice.clear();
            productQuantity.clear();
        } catch (NumberFormatException e) {
            productError.setText("Invalid price or quantity");
        }
    }

    @FXML
    private void addService() {
        try {
            serviceError.setText("");
            String type = serviceType.getText();
            int personNumber = Integer.parseInt(numberOfPersons.getText());
            int hoursInt = Integer.parseInt(hours.getText());
            OrderService.getInstance().addService(type, personNumber, hoursInt);
            updateList();
            serviceType.clear();
            numberOfPersons.clear();
            hours.clear();
        } catch (NumberFormatException e) {
            serviceError.setText("Invalid number of persons or hours");
        }

    }

    private void updateList() {
        items = OrderService.getInstance().getItems();
        ArrayList<Item> arrayItems = new ArrayList<>(items);
        basketList.setItems(FXCollections.<Item>observableArrayList(arrayItems));
    }

    @FXML
    private void buyItems() throws IOException {
        basketList.getItems().clear();
        sceneController.startGuiOrderSummary();
    }




}
