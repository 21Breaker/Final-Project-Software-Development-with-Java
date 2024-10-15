import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class SalesReport {
    private String reportID;
    private LocalDate startDate;
    private LocalDate endDate;
    private double totalSales;
    private double averageSales;
    private String topSellingProduct;

    // Constructor to initialize SalesReport object
    public SalesReport(String reportID, LocalDate startDate, LocalDate endDate) {
        this.reportID = reportID;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    // Method to generate the sales report
    public void generateReport(List<ProductSales> sales) {
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
                    this.totalSales = resultSet.getDouble("totalSales");
                    this.averageSales = resultSet.getDouble("averageSales");
                    this.topSellingProduct = resultSet.getString("productName");
                }
            } catch (SQLException e) {
                // Print the stack trace if an SQL exception occurs
                e.printStackTrace();
            }
        }
    }

    // Method to display the sales report
    public void displayReport() {
        System.out.println("Report ID: " + this.reportID);
        System.out.println("Start Date: " + this.startDate);
        System.out.println("End Date: " + this.endDate);
        System.out.println("Total Sales: " + this.totalSales);
        System.out.println("Average Sales: " + this.averageSales);
        System.out.println("Top Selling Product: " + this.topSellingProduct);
    }
}
