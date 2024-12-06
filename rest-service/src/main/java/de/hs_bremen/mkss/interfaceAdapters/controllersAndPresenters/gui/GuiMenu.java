package de.hs_bremen.mkss.interfaceAdapters.controllersAndPresenters.gui;

import de.hs_bremen.mkss.application.boundaries.*;
import de.hs_bremen.mkss.domain.item.Item;
import de.hs_bremen.mkss.domain.order.Order;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Component("guiMenu")
@Profile("gui")
public class GuiMenu implements ICreateOrderOutput, IAddProductOutput, IGetAllItemsOutput, IFinishOrderOutput, IClearOrdersOutput, IGetAllOrdersOutput {
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

    private Order order;


    // use case input boundaries
    private ICreateOrderInput createOrderInput;
    private IAddProductInput addProductInput;
    private IGetAllItemsInput getAllItemsInput;
    private IFinishOrderInput finishOrderInput;
    private IGetAllOrdersInput getAllOrdersInput;
    private IClearOrdersInput clearOrdersInput;


    @Autowired
    public GuiMenu(@Lazy @Qualifier("createOrderUseCase")ICreateOrderInput createOrderInput,
                   @Lazy @Qualifier("addProductUseCase")IAddProductInput addProductInput,
                   @Lazy @Qualifier("clearOrdersUseCase")IClearOrdersInput clearOrdersInput,
                   @Lazy @Qualifier("finishOrderUseCase")IFinishOrderInput finishOrderInput,
                   @Lazy @Qualifier("getAllOrdersUseCase")IGetAllOrdersInput getAllOrdersInput,
                   @Lazy @Qualifier("getAllItemsUseCase")IGetAllItemsInput getAllItemsInput) {
        this.createOrderInput = createOrderInput;
        this.addProductInput = addProductInput;
        this.clearOrdersInput = clearOrdersInput;
        this.finishOrderInput = finishOrderInput;
        this.getAllOrdersInput = getAllOrdersInput;
        this.getAllItemsInput = getAllItemsInput;


    }


    public void setAll(Stage stage) {


        this.stage = stage;
        menuFxmlLoader = new FXMLLoader(getClass().getResource("/templates/GuiMenu.fxml"));
        menuFxmlLoader.setController(this);
        try {
            menuScene = new Scene(menuFxmlLoader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
        init();
        createOrderInput.createOrder();
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
            addProductInput.addProduct(order, name, price, quantity);

            productName.clear();
            productPrice.clear();
            productQuantity.clear();
        } catch (NumberFormatException e) {
            productError.setText("Invalid price or quantity");
        }
    }

    private void updateList() {
        getAllItemsInput.getAllItems(order);
    }

    private void createPopupWindow() {
        Platform.runLater(() -> {
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
        });
    }

    @FXML
    private void buyItems() {
        finishOrderInput.finishOrder(order);
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

        this.order = null;
        createOrderInput.createOrder();

        popupWindow.close();
    }

    @Override
    public void onAddProductResult(Order order) {
        if (order == null) {
            productError.setText("Product could not be added");
        } else {
            updateList();
        }
        this.order = order;
    }

    @Override
    public void onCreateOrderResult(Order order) {
        if(order == null) {
            //TODO show text in gui
            System.out.println("Order could not be created");
        }
        this.order = order;
        updateList();
    }


    @Override
    public void onGetAllItemsResult(List<Item> items) {
        System.out.println(items);
        ArrayList<Item> arrayItems = new ArrayList<>(items);
        basketList.setItems(FXCollections.<Item>observableArrayList(arrayItems));
    }

    @Override
    public void onFinishOrderResult(boolean success, Order finishedOrder) {
        if (success && finishedOrder != null) {
            this.order = finishedOrder;
            showSummary(finishedOrder.getItems(), finishedOrder.getSumString(), finishedOrder.checkoutDateTime());
        } else {
            System.out.println("Failed to finish the order.");
        }

    }

    @Override
    public void onClearOrdersResult(boolean success) {

    }

    @Override
    public void onGetAllOrdersResult(List<Order> orders) {
    }
}