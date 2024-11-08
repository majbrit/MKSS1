package Ui.Gui;

import Services.OrderService;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class ProductSelection extends VBox {
    private TextField productName;
    private TextField productPrice;
    private TextField productQuantity;
    private Button addButton;
    private OrderService orderService;


    public ProductSelection(OrderService orderService){
        this.orderService = orderService;
        System.out.println(this.orderService);
        this.setPadding(new Insets(10));
        this.setSpacing(10);
        this.setStyle("-fx-background-color: #fff");

        productName = new TextField();
        productName.setPromptText("Product Name");
        productPrice = new TextField();
        productPrice.setPromptText("Product Price (in cent)");
        productQuantity = new TextField();
        productQuantity.setPromptText("Product Quantity");
        addButton = new Button("Add");
        addButton.setOnAction(event -> addProduct());
        getChildren().addAll(productName, productPrice, productQuantity, addButton);

    }

    private void addProduct() {
        try {
            String productName = this.productName.getText();
            int productPrice = Integer.parseInt(this.productPrice.getText());
            int productQuantity = Integer.parseInt(this.productQuantity.getText());
            Gui.orderService.addProduct(productName, productPrice, productQuantity);
            System.out.println("product"+ Gui.orderService.getItems());
        } catch (NumberFormatException e) {
            System.out.println("Invalid price or quantity");
        }
        this.productName.clear();
        this.productPrice.clear();
        this.productQuantity.clear();
    }

}
