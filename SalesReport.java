import java.time.LocalDate;
import java.util.List;

/**
 * Class representing a sales report.
 */
public class SalesReport {
    private String reportID;
    private LocalDate startDate;
    private LocalDate endDate;
    private double totalSales;
    private double averageSales;
    private String topSellingProduct;

    /**
     * Constructor for SalesReport.
     * @param reportID Unique ID for the report.
     * @param startDate Start date of the report period.
     * @param endDate End date of the report period.
     */
    public SalesReport(String reportID, LocalDate startDate, LocalDate endDate) {
        this.reportID = reportID;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Generates the sales report based on a list of product sales.
     * @param sales List of ProductSales objects.
     */
    public void generateReport(List<ProductSales> sales) {
        this.totalSales = sales.stream().mapToDouble(s -> s.amount).sum();
        this.averageSales = this.totalSales / sales.size();
        this.topSellingProduct = sales.stream().max((s1, s2) -> Double.compare(s1.amount, s2.amount)).get().productName;
    }

    /**
     * Displays the sales report.
     */
    public void displayReport() {
        System.out.println("Report ID: " + this.reportID);
        System.out.println("Start Date: " + this.startDate);
        System.out.println("End Date: " + this.endDate);
        System.out.println("Total Sales: " + this.totalSales);
        System.out.println("Average Sales: " + this.averageSales);
        System.out.println("Top Selling Product: " + this.topSellingProduct);
    }

    // Getters and setters for the fields can be added here
}
