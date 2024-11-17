package interfaceAdapters.controllersAndPresenters.gui;

import domain.item.Item;
import interfaceAdapters.gateway.OrderRepository;
import application.OrderService;
import domain.factory.SimpleItemFactory;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
    private FXMLLoader menuFxmlLoader;
    private Scene menuScene;

    private Stage popupWindow;
    private Label sumLabel;
    private Label dateLabel;
    private TableView<Item> basketListSummary;
    private Scene scenePopUp;

    private UUID orderID;


    public GuiMenu(Stage stage){
        OrderService.getInstance().setOrderRepository(new OrderRepository());
        OrderService.getInstance().setItemFactory(new SimpleItemFactory());
        orderID = OrderService.getInstance().newOrder();

        this.stage = stage;
        menuFxmlLoader = new FXMLLoader(getClass().getResource("GuiMenu.fxml"));
        menuFxmlLoader.setController(this);
        try {
            menuScene = new Scene((Parent) menuFxmlLoader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }

        init();

        stage.setScene(menuScene);
        stage.setMaximized(true);
        stage.setTitle("Order System");

        stage.show();
    }

    public void init() {
        TableColumn<Item, String> descriptionColumn = new TableColumn<>("Description");
        descriptionColumn.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().toString()));

        descriptionColumn.setPrefWidth(300);
        descriptionColumn.setResizable(true);
        basketList.getColumns().add(descriptionColumn);

        createPopupWindow();
    }

    @FXML
    private void addProduct() {
        try {
            productError.setText("");
            String name = productName.getText();
            int price = Integer.parseInt(productPrice.getText());
            int quantity = Integer.parseInt(productQuantity.getText());
            OrderService.getInstance().addProduct(orderID, name, price, quantity);
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
            OrderService.getInstance().addService(orderID, type, personNumber, hoursInt);
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

    private void createPopupWindow() {
        popupWindow=new Stage();

        popupWindow.initModality(Modality.APPLICATION_MODAL);
        popupWindow.setTitle("OrderSummary");

        BorderPane borderPane = new BorderPane();
        borderPane.setPrefSize(800, 400);
        borderPane.setStyle("-fx-background-color: #000022;");

        VBox topVBox = new VBox();
        topVBox.setPrefSize(800, 46);
        Label titleLabel = new Label("Bill");
        titleLabel.setPrefSize(800, 50);
        titleLabel.setTextFill(javafx.scene.paint.Color.WHITE);
        titleLabel.setStyle("-fx-alignment: center;");
        topVBox.getChildren().add(titleLabel);
        borderPane.setTop(topVBox);

        VBox centerVBox = new VBox();
        centerVBox.setPrefSize(100, 200);
        centerVBox.setStyle("-fx-background-color: #eeeeee;");
        centerVBox.setPadding(new Insets(30, 30, 30, 30));

        basketListSummary = new TableView<>();
        basketListSummary.setPrefSize(200, 200);
        sumLabel = new Label("sum");
        sumLabel.setPrefSize(557, 17);
        dateLabel = new Label("date");
        dateLabel.setPrefSize(558, 17);

        centerVBox.getChildren().addAll(basketListSummary, sumLabel, dateLabel);
        borderPane.setCenter(centerVBox);

        Button newOrderButton = new Button("Place new order");
        newOrderButton.setPadding(new Insets(10, 10, 10, 10));
        borderPane.setBottom(newOrderButton);
        BorderPane.setMargin(newOrderButton, new Insets(10, 10, 10, 10));
        newOrderButton.setOnAction(event -> makeNewOrder());

        TableColumn<Item, String> descriptionColumn = new TableColumn<>("Description");
        descriptionColumn.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().toString()));

        descriptionColumn.setPrefWidth(300);
        descriptionColumn.setResizable(true);
        basketListSummary.getColumns().add(descriptionColumn);

        scenePopUp= new Scene(borderPane);

    }

    @FXML
    private void buyItems() throws IOException {

        OrderService.getInstance().finishOrder(orderID);
        List<Item> items = OrderService.getInstance().getItems();
        String checkOut = OrderService.getInstance().getCheckoutDateTime();
        String sumString = OrderService.getInstance().getSumString();

        ArrayList<Item> arrayItems = new ArrayList<>(items);
        basketListSummary.setItems(FXCollections.<Item>observableArrayList(arrayItems));

        sumLabel.setText("Sum: " + sumString);
        dateLabel.setText("Checkout at " + checkOut);

        popupWindow.setScene(scenePopUp);
        popupWindow.setOnCloseRequest(event -> makeNewOrder());
        popupWindow.showAndWait();
    }

    private void makeNewOrder() {
        basketList.getItems().clear();
        basketListSummary.getItems().clear();
        orderID = OrderService.getInstance().newOrder();
        popupWindow.close();
    }

}
