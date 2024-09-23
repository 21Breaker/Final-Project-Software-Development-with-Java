import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for SalesReport.
 */
public class TestSalesReport {
    @Test
    public void testGenerateReport() {
        ProductSales sale1 = new ProductSales("T001", LocalDate.now(), 0, "John Doe", "P001", "Product A", 10, 15.0);
        ProductSales sale2 = new ProductSales("T002", LocalDate.now(), 0, "Jane Doe", "P002", "Product B", 5, 20.0);
        sale1.calculateTotal();
        sale2.calculateTotal();

        SalesReport report = new SalesReport("R001", LocalDate.now().minusDays(1), LocalDate.now());
        report.generateReport(Arrays.asList(sale1, sale2));

        assertEquals(250.0, report.totalSales);
        assertEquals(125.0, report.averageSales);
        assertEquals("Product A", report.topSellingProduct);
    }
}
