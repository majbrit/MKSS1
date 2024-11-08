package Ui.Gui;

import Entities.Item;
import Services.OrderService;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GuiMenu extends Stage {
    private Scene scene;
    private List<Item> items;

    @FXML
    private TextField productName;
    @FXML
    private TextField productPrice;
    @FXML
    private TextField productQuantity;
    @FXML
    private TableView<Item> basketList;

    public GuiMenu() {
        init();
    }

    private void init() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Gui.fxml"));
        loader.setController(this);

        try {
            scene = new Scene((Parent) loader.load());
            setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }

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
        } catch (NumberFormatException e) {
            System.out.println("Invalid price or quantity");
        }
    }

    private void updateList() {
        items = OrderService.getInstance().getItems();
        ArrayList<Item> items2 = new ArrayList<>();
        for (Item item : items) {
            items2.add(item);
        }
        basketList.setItems(FXCollections.<Item>observableArrayList(items2));
    }


}
