import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/finaljavaproject";
        String user = "root";
        String password = "your_password";

        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connection successful!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
