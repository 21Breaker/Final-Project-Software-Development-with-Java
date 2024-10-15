import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

// Abstract class representing a sales transaction
public abstract class SalesTransaction {
    protected String transactionID;
    protected LocalDate date;
    protected double amount;
    protected String customerName;
    protected String productID;

    // Constructor to initialize SalesTransaction object
    public SalesTransaction(String transactionID, LocalDate date, double amount, String customerName, String productID) {
        this.transactionID = transactionID;
        this.date = date;
        this.amount = amount;
        this.customerName = customerName;
        this.productID = productID;
    }

    // Abstract method to calculate the total amount
    public abstract void calculateTotal();

    // Abstract method to validate the transaction
    public abstract void validateTransaction();

    // Method to save the transaction details to the database
    public void saveToDatabase() {
        // Establish a connection to the database
        Connection connection = JDBC.getConnection();
        if (connection != null) {
            try {
                // SQL query to insert transaction details into the sales_transactions table
                String query = "INSERT INTO sales_transactions (transactionID, date, amount, customerName, productID) VALUES (?, ?, ?, ?, ?)";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                // Set the values for the query parameters
                preparedStatement.setString(1, this.transactionID);
                preparedStatement.setDate(2, java.sql.Date.valueOf(this.date));
                preparedStatement.setDouble(3, this.amount);
                preparedStatement.setString(4, this.customerName);
                preparedStatement.setString(5, this.productID);
                // Execute the query
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                // Print the stack trace if an SQL exception occurs
                e.printStackTrace();
            }
        }
    }
}
