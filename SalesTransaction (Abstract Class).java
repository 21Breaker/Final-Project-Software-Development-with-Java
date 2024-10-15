import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public abstract class SalesTransaction {
    protected String transactionID;
    protected LocalDate date;
    protected double amount;
    protected String customerName;
    protected String productID;

    public SalesTransaction(String transactionID, LocalDate date, double amount, String customerName, String productID) {
        this.transactionID = transactionID;
        this.date = date;
        this.amount = amount;
        this.customerName = customerName;
        this.productID = productID;
    }

    public abstract void calculateTotal();

    public abstract void validateTransaction();

    public void saveToDatabase() {
        Connection connection = JDBC.getConnection();
        if (connection != null) {
            try {
                String query = "INSERT INTO sales_transactions (transactionID, date, amount, customerName, productID) VALUES (?, ?, ?, ?, ?)";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, this.transactionID);
                preparedStatement.setDate(2, java.sql.Date.valueOf(this.date));
                preparedStatement.setDouble(3, this.amount);
                preparedStatement.setString(4, this.customerName);
                preparedStatement.setString(5, this.productID);
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
