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
    /*public GuiOrderSummary(Stage stage) {
        this.stage = stage;
        init();
    }*/

    public GuiOrderSummary(SceneController sceneController) {
        this.sceneController = sceneController;
    }

    public void startOrderSummary() {
        basketList.getItems().clear();
        createBill();
    }

    public void init() {
      /*  try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("GuiOrderSummary.fxml"));
            loader.setController(this);
            scene = new Scene((Parent) loader.load());
            stage.setScene(scene);
            stage.setMaximized(true);
            stage.setTitle("Order System");
            stage.show();

            createBill();
        } catch (Exception e) {
            e.printStackTrace();
        }
*/
        TableColumn<Item, String> descriptionColumn = new TableColumn<>("Description");
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        descriptionColumn.setPrefWidth(300);
        descriptionColumn.setResizable(true);
        basketList.getColumns().addAll(descriptionColumn);





    }

    private void createBill() {
        List<Item> items = OrderService.getInstance().getItems();
        String checkOut = OrderService.getInstance().checkoutDateTime();
        String sumString = OrderService.getInstance().getSumString();


        ArrayList<Item> arrayItems = new ArrayList<>(items);
        basketList.setItems(FXCollections.<Item>observableArrayList(arrayItems));

        sum.setText("Sum: " + sumString);
        checkoutDateTime.setText("Checkout at " + checkOut);



    }

    @FXML
    private void newOrder(){
        OrderService.getInstance().newOrder();
        sceneController.startGuiMenu();
    }
}
