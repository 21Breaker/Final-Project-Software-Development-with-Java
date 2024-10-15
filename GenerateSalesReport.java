import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

btnGenerateReport.setOnAction(e -> {
    Connection connection = JDBC.getConnection();
    if (connection != null) {
        try {
            String query = "SELECT SUM(amount) AS totalSales, AVG(amount) AS averageSales, productName FROM sales GROUP BY productName ORDER BY SUM(amount) DESC LIMIT 1";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                double totalSales = resultSet.getDouble("totalSales");
                double averageSales = resultSet.getDouble("averageSales");
                String topProduct = resultSet.getString("productName");

                // Update dashboard labels
                lblTotalSales.setText("Total Sales: $" + totalSales);
                lblAverageSales.setText("Average Sales per Day: $" + averageSales);
                lblTopProduct.setText("Top-Selling Product: " + topProduct);

                showAlert("Sales Report", "Report generated successfully!");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
});
