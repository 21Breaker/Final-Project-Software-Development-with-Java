import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    public static void main(String[] args) {
        // Database URL, username, and password
        String url = "jdbc:mysql://localhost:3306/finaljavaproject";
        String user = "root";
        String password = "your_password";

        try {
            // Attempt to establish a connection to the database
            Connection connection = DriverManager.getConnection(url, user, password);
            // Print a success message if the connection is established
            System.out.println("Connection successful!");
        } catch (SQLException e) {
            // Print the stack trace if an SQL exception occurs
            e.printStackTrace();
        }
    }
}
