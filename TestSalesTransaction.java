import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for SalesTransaction and its subclasses.
 */
public class TestSalesTransaction {
    @Test
    public void testCalculateTotal() {
        ProductSales sale = new ProductSales("T001", LocalDate.now(), 0, "John Doe", "P001", "Product A", 10, 15.0);
        sale.calculateTotal();
        assertEquals(150.0, sale.amount);
    }

    @Test
    public void testValidateTransaction() {
        ProductSales sale = new ProductSales("T002", LocalDate.now(), 0, "Jane Doe", "P002", "Product B", 5, 20.0);
        assertDoesNotThrow(() -> sale.validateTransaction());

        ProductSales invalidSale = new ProductSales("T003", LocalDate.now(), 0, "Jim Doe", "P003", "Product C", 0, 20.0);
        assertThrows(IllegalArgumentException.class, () -> invalidSale.validateTransaction());
    }
}
