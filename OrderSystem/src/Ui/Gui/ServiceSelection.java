package Ui.Gui;

import Services.OrderService;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class ServiceSelection extends VBox {
    private TextField serviceType;
    private TextField numberOfPersons;
    private TextField hours;
    private Button addButton;
    private OrderService orderService;

    public ServiceSelection(OrderService orderService) {
        this.orderService = orderService;
        System.out.println(this.orderService);
        this.setPadding(new Insets(10));
        this.setSpacing(10);
        this.setStyle("-fx-background-color: #fff");

        serviceType = new TextField();
        serviceType.setPromptText("Service Type");
        numberOfPersons = new TextField();
        numberOfPersons.setPromptText("Number of Persons");
        hours = new TextField();
        hours.setPromptText("Hours");
        addButton = new Button("Add");
        addButton.setOnAction(event -> addService());
        getChildren().addAll(serviceType, numberOfPersons, hours, addButton);

    }
    private void addService() {
        try {
            String serviceType = this.serviceType.getText();
            int numberOfPersons = Integer.parseInt(this.numberOfPersons.getText());
            int hours = Integer.parseInt(this.hours.getText());
            orderService.addProduct(serviceType, numberOfPersons, hours);
        } catch (Exception e) {
            System.out.println("Invalid number of persons or hours");
        }

        this.serviceType.clear();
        this.numberOfPersons.clear();
        this.hours.clear();
    }
}