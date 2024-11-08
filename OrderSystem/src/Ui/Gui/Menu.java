package Ui.Gui;

import Services.OrderService;
import javafx.geometry.Insets;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class Menu extends BorderPane {
    private OrderService orderService;

    public Menu(OrderService orderService) {
        this.orderService = orderService;

        VBox top = new VBox();
        top.setPadding(new Insets(10, 10, 10, 10));
        top.getChildren().add(new Text("Menu"));

        HBox left = new HBox();
        left.setPadding(new Insets(10, 10, 10, 10));
        left.setSpacing(10);
        ProductSelection productSelection = new ProductSelection(orderService);
        ServiceSelection serviceSelection = new ServiceSelection(orderService);
        left.getChildren().addAll(productSelection, serviceSelection);

        ShoppingBasket shoppingBasket = new ShoppingBasket(orderService);

        this.setTop(top);
        this.setLeft(left);
        this.setCenter(shoppingBasket);
    }
}
