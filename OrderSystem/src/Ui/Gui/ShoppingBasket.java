package Ui.Gui;

import Entities.Item;
import Services.OrderService;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.awt.*;
import java.util.List;

public class ShoppingBasket extends VBox {
    private OrderService orderService;
    private List<Item> items;
    private ObservableList<Item> itemObservableList;
    public ShoppingBasket(OrderService orderService) {
        this.orderService = orderService;
        this.setPadding(new Insets(20));
        this.setSpacing(20);
        this.setStyle("-fx-background-color: #fff");

        Text heading = new Text("Shopping Basket");
        items = Gui.orderService.getItems();
        itemObservableList = FXCollections.observableList(items);
        ListView<Item> listView = new ListView(itemObservableList);





        this.getChildren().addAll(heading, listView);


        orderService.setHandler(event -> updateBasket());
    }

    private void updateBasket() {
        itemObservableList.setAll(Gui.orderService.getItems());
        System.out.println(Gui.orderService);
        System.out.println(this.orderService);
        System.out.println(Gui.orderService.getItems());
    }

}
