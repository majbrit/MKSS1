package interfaceAdapters.controllersAndPresenters.gui;

import application.boundaries.*;
import application.usecases.*;
import domain.factory.ItemFactory;
import domain.item.Item;
import domain.order.Order;
import domain.repositoryInterfaces.IOrderRepository;
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

public class GuiMenu implements ICreadeOrderOutput, IAddProductOutput, IGetAllItemsOutput, IFinishOrderOutput {
    private Scene scene;

    // product
    @FXML
    private TextField productName;
    @FXML
    private TextField productPrice;
    @FXML
    private TextField productQuantity;
    @FXML
    private Label productError;


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


    // use case input boundaries
    private ICreateOrderInput createOrderInput;
    private IAddProductInput addProductinput;
    private IGetAllItemsInput getAllItemsInput;
    private IFinishOrderInput finishOrderInput;

    public GuiMenu(Stage stage, IOrderRepository orderRepository, ItemFactory itemFactory) {
        this.createOrderInput = new CreateOrderUseCase(this, orderRepository);
        this.addProductinput = new AddProductUseCase(this, orderRepository, itemFactory);
        this.getAllItemsInput = new GetAllItemsUseCase(this, orderRepository);
        this.finishOrderInput = new FinishOrderUseCase(this, orderRepository);

        createOrderInput.createOrder();

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
            addProductinput.addProduct(orderID, name, price, quantity);

            productName.clear();
            productPrice.clear();
            productQuantity.clear();
        } catch (NumberFormatException e) {
            productError.setText("Invalid price or quantity");
        }
    }

    private void updateList() {
        getAllItemsInput.getAllItems(orderID);
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
    private void buyItems() {
        finishOrderInput.finishOrder(orderID);
    }

    private void showSummary(List<Item> items, String sumString, String checkOut) {
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

        createOrderInput.createOrder();

        popupWindow.close();
    }

    @Override
    public void onAddProductResult(boolean updated) {
        if (!updated) {
            productError.setText("Product could not be added");
        } else {
            updateList();
        }
    }

    @Override
    public void onCreateOrderResult(UUID orderId) {
        if(orderId == null) {
            //TODO show text in gui
            System.out.println("Order could not be created");
        }
        this.orderID = orderId;
    }


    @Override
    public void onGetAllItemsResult(List<Item> items) {
        ArrayList<Item> arrayItems = new ArrayList<>(items);
        basketList.setItems(FXCollections.<Item>observableArrayList(arrayItems));
    }

    @Override
    public void onFinishOrderResult(boolean success, Order finishedOrder) {
        if (success && finishedOrder != null) {

            showSummary(finishedOrder.getItems(), finishedOrder.getSumString(), finishedOrder.checkoutDateTime());
        } else {
            System.out.println("Failed to finish the order.");
        }
    }
}