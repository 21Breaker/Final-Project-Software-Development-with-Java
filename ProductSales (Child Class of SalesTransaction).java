import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class ProductSales extends SalesTransaction {
    private String productName;
    private int quantitySold;
    private double unitPrice;

    public ProductSales(String transactionID, LocalDate date, double amount, String customerName, String productID, String productName, int quantitySold, double unitPrice) {
        super(transactionID, date, amount, customerName, productID);
        this.productName = productName;
        this.quantitySold = quantitySold;
        this.unitPrice = unitPrice;
    }

    @Override
    public void calculateTotal() {
        this.amount = this.quantitySold * this.unitPrice;
    }

    @Override
    public void validateTransaction() {
        if (this.quantitySold <= 0 || this.unitPrice <= 0) {
            throw new IllegalArgumentException("Quantity sold and unit price must be greater than zero.");
        }
    }

    public void saveToDatabase() {
        Connection connection = JDBC.getConnection();
        if (connection != null) {
            try {
                String query = "INSERT INTO product_sales (transactionID, date, amount, customerName, productID, productName, quantitySold, unitPrice) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, this.transactionID);
                preparedStatement.setDate(2, java.sql.Date.valueOf(this.date));
                preparedStatement.setDouble(3, this.amount);
                preparedStatement.setString(4, this.customerName);
                preparedStatement.setString(5, this.productID);
                preparedStatement.setString(6, this.productName);
                preparedStatement.setInt(7, this.quantitySold);
                preparedStatement.setDouble(8, this.unitPrice);
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
