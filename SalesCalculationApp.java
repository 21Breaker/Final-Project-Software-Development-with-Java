import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

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

        // Button to submit the sales data
        Button btnSubmit = new Button("Submit");
        btnSubmit.setOnAction(e -> {
            // Retrieve input values from the form
            String transactionID = txtTransactionID.getText();
            LocalDate date = datePicker.getValue();
            double amount = Double.parseDouble(txtAmount.getText());
            String customerName = txtCustomerName.getText();
            String productID = txtProductID.getText();
            String productName = txtProductName.getText();
            int quantitySold = Integer.parseInt(txtQuantitySold.getText());
            double unitPrice = Double.parseDouble(txtUnitPrice.getText());

            // Validate the input data
            if (amount <= 0 || quantitySold <= 0 || unitPrice <= 0) {
                // Show an alert if any of the values are invalid
                showAlert("Invalid input", "Amount, Quantity Sold, and Unit Price must be positive numbers.");
                return;
            }

            // Save data to the database
            Connection connection = JDBC.getConnection();
            if (connection != null) {
                try {
                    // Prepare the SQL query for inserting data into the sales table
                    String query = "INSERT INTO sales (transactionID, date, amount, customerName, productID, productName, quantitySold, unitPrice) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                    PreparedStatement preparedStatement = connection.prepareStatement(query);
                    // Set the values for the query parameters
                    preparedStatement.setString(1, transactionID);
                    preparedStatement.setDate(2, java.sql.Date.valueOf(date));
                    preparedStatement.setDouble(3, amount);
                    preparedStatement.setString(4, customerName);
                    preparedStatement.setString(5, productID);
                    preparedStatement.setString(6, productName);
                    preparedStatement.setInt(7, quantitySold);
                    preparedStatement.setDouble(8, unitPrice);
                    // Execute the query
                    preparedStatement.executeUpdate();
                } catch (SQLException ex) {
                    // Print the stack trace if an SQL exception occurs
                    ex.printStackTrace();
                }
            }

            // Update dashboard labels with the new sales data
            lblTotalSales.setText("Total Sales: $" + amount);
            lblAverageSales.setText("Average Sales per Day: $" + (amount / 1)); // Simplified for example
            lblTopProduct.setText("Top-Selling Product: " + productName);
        });

        // Form layout for sales data input
        VBox form = new VBox(10, txtTransactionID, datePicker, txtAmount, txtCustomerName, txtProductID, txtProductName, txtQuantitySold, txtUnitPrice, btnSubmit);
        form.setPadding(new Insets(10));

        // Main layout of the application
        BorderPane mainLayout = new BorderPane();
        mainLayout.setTop(menuBar);
        mainLayout.setCenter(dashboard);
        mainLayout.setBottom(form);

        // Set the scene and show the primary stage
        Scene scene = new Scene(mainLayout, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
