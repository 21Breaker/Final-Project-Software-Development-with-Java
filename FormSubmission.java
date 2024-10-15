import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

btnSubmit.setOnAction(e -> {
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
        showAlert("Invalid input", "Amount, Quantity Sold, and Unit Price must be positive numbers.");
        return;
    }

    // Save data to the database
    Connection connection = JDBC.getConnection();
    if (connection != null) {
        try {
            String query = "INSERT INTO sales (transactionID, date, amount, customerName, productID, productName, quantitySold, unitPrice) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, transactionID);
            preparedStatement.setDate(2, java.sql.Date.valueOf(date));
            preparedStatement.setDouble(3, amount);
            preparedStatement.setString(4, customerName);
            preparedStatement.setString(5, productID);
            preparedStatement.setString(6, productName);
            preparedStatement.setInt(7, quantitySold);
            preparedStatement.setDouble(8, unitPrice);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    // Process the data (e.g., save to file, update dashboard)
    lblTotalSales.setText("Total Sales: $" + amount);
    lblAverageSales.setText("Average Sales per Day: $" + (amount / 1)); // Simplified for example
    lblTopProduct.setText("Top-Selling Product: " + productName);
});
