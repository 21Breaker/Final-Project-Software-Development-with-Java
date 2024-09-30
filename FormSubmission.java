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

    // Process the data (e.g., save to file, update dashboard)
    lblTotalSales.setText("Total Sales: $" + amount);
    lblAverageSales.setText("Average Sales per Day: $" + (amount / 1)); // Simplified for example
    lblTopProduct.setText("Top-Selling Product: " + productName);
});
