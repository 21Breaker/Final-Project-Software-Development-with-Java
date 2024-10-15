import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// Event handler for the generate report button
btnGenerateReport.setOnAction(e -> {
    // Establish a connection to the database
    Connection connection = JDBC.getConnection();
    if (connection != null) {
        try {
            // SQL query to calculate total sales, average sales, and top-selling product
            String query = "SELECT SUM(amount) AS totalSales, AVG(amount) AS averageSales, productName FROM sales GROUP BY productName ORDER BY SUM(amount) DESC LIMIT 1";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            // Check if the result set has data
            if (resultSet.next()) {
                // Retrieve the calculated values from the result set
                double totalSales = resultSet.getDouble("totalSales");
                double averageSales = resultSet.getDouble("averageSales");
                String topProduct = resultSet.getString("productName");

                // Update dashboard labels with the retrieved data
                lblTotalSales.setText("Total Sales: $" + totalSales);
                lblAverageSales.setText("Average Sales per Day: $" + averageSales);
                lblTopProduct.setText("Top-Selling Product: " + topProduct);

                // Show a success alert
                showAlert("Sales Report", "Report generated successfully!");
            }
        } catch (SQLException ex) {
            // Print the stack trace if an SQL exception occurs
            ex.printStackTrace();
        }
    }
});
