package Ui.Gui;

import Entities.Item;
import Services.OrderService;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

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

    // service
    @FXML
    private TextField serviceType;
    @FXML
    private TextField numberOfPersons;
    @FXML
    private TextField hours;


    // shopping list
    @FXML
    private TableView<Item> basketList;


    private Stage stage;
    private SceneController sceneController;

    /*
    public GuiMenu(Stage stage) {
       this.stage = stage;
       init();
    }*/

    public GuiMenu(SceneController sceneController){
        this.sceneController = sceneController;
    }

    public void startMenu(){
        //init();
        basketList.getItems().clear();
    }

    public void init() {
       /* FXMLLoader loader = new FXMLLoader(getClass().getResource("GuiMenu.fxml"));
        loader.setController(this);
        try {
            scene = new Scene((Parent) loader.load());
            stage.setScene(scene);
            stage.setMaximized(true);
            stage.setTitle("Order System");
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.show();*/

        TableColumn<Item, String> descriptionColumn = new TableColumn<>("Description");
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        descriptionColumn.setPrefWidth(300);
        descriptionColumn.setResizable(true);
        basketList.getColumns().addAll(descriptionColumn);

    }

    @FXML
    private void addProduct() {
        try {
            String name = productName.getText();
            int price = Integer.parseInt(productPrice.getText());
            int quantity = Integer.parseInt(productQuantity.getText());
            OrderService.getInstance().addProduct(name, price, quantity);
            updateList();
            productName.clear();
            productPrice.clear();
            productQuantity.clear();
        } catch (NumberFormatException e) {
            System.out.println("Invalid price or quantity");
        }
    }

    @FXML
    private void addService() {
        try {
            String type = serviceType.getText();
            int personNumber = Integer.parseInt(numberOfPersons.getText());
            int hoursInt = Integer.parseInt(hours.getText());
            OrderService.getInstance().addService(type, personNumber, hoursInt);
            updateList();
            serviceType.clear();
            numberOfPersons.clear();
            hours.clear();
        } catch (NumberFormatException e) {
            System.out.println("Invalid price or quantity");
        }

    }

    private void updateList() {
        items = OrderService.getInstance().getItems();
        ArrayList<Item> arrayItems = new ArrayList<>(items);
        basketList.setItems(FXCollections.<Item>observableArrayList(arrayItems));
    }

    @FXML
    private void buyItems() throws IOException {
        //GuiOrderSummary guiOrderSummary = new GuiOrderSummary(stage);
        sceneController.startGuiOrderSummary();
    }




}
