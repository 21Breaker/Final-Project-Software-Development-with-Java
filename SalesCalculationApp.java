import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class SalesCalculationApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Sales Calculation Application");

        // Menu Bar
        MenuBar menuBar = new MenuBar();
        Menu menuFile = new Menu("File");
        MenuItem menuItemExit = new MenuItem("Exit");
        menuItemExit.setOnAction(e -> System.exit(0));
        menuFile.getItems().add(menuItemExit);
        menuBar.getMenus().add(menuFile);

        // Dashboard
        Label lblTotalSales = new Label("Total Sales: $0.00");
        Label lblAverageSales = new Label("Average Sales per Day: $0.00");
        Label lblTopProduct = new Label("Top-Selling Product: None");

        VBox dashboard = new VBox(10, lblTotalSales, lblAverageSales, lblTopProduct);
        dashboard.setPadding(new Insets(10));

        // Sales Data Form
        TextField txtTransactionID = new TextField();
        txtTransactionID.setPromptText("Transaction ID");
        DatePicker datePicker = new DatePicker();
        TextField txtAmount = new TextField();
        txtAmount.setPromptText("Amount");
        TextField txtCustomerName = new TextField();
        txtCustomerName.setPromptText("Customer Name");
        TextField txtProductID = new TextField();
        txtProductID.setPromptText("Product ID");
        TextField txtProductName = new TextField();
        txtProductName.setPromptText("Product Name");
        TextField txtQuantitySold = new TextField();
        txtQuantitySold.setPromptText("Quantity Sold");
        TextField txtUnitPrice = new TextField();
        txtUnitPrice.setPromptText("Unit Price");

        Button btnSubmit = new Button("Submit");
        btnSubmit.setOnAction(e -> {
            // Handle form submission
            // Validate and process the input data
        });

        VBox form = new VBox(10, txtTransactionID, datePicker, txtAmount, txtCustomerName, txtProductID, txtProductName, txtQuantitySold, txtUnitPrice, btnSubmit);
        form.setPadding(new Insets(10));

        // Main Layout
        BorderPane mainLayout = new BorderPane();
        mainLayout.setTop(menuBar);
        mainLayout.setCenter(dashboard);
        mainLayout.setBottom(form);

        Scene scene = new Scene(mainLayout, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
