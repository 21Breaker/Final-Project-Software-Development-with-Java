btnGenerateReport.setOnAction(e -> {
    // Generate a sales report (dummy data for example)
    double totalSales = 1000.00;
    double averageSales = 100.00;
    String topProduct = "Product A";

    // Update dashboard labels
    lblTotalSales.setText("Total Sales: $" + totalSales);
    lblAverageSales.setText("Average Sales per Day: $" + averageSales);
    lblTopProduct.setText("Top-Selling Product: " + topProduct);

    showAlert("Sales Report", "Report generated successfully!");
});
