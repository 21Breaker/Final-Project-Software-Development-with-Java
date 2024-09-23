/**
 * Class representing product sales, inheriting from SalesTransaction.
 */
public class ProductSales extends SalesTransaction {
    private String productName;
    private int quantitySold;
    private double unitPrice;

    /**
     * Constructor for ProductSales.
     * @param transactionID Unique ID for the transaction.
     * @param date Date of the transaction.
     * @param amount Amount of the transaction.
     * @param customerName Name of the customer.
     * @param productID ID of the product.
     * @param productName Name of the product.
     * @param quantitySold Quantity of the product sold.
     * @param unitPrice Unit price of the product.
     */
    public ProductSales(String transactionID, LocalDate date, double amount, String customerName, String productID, String productName, int quantitySold, double unitPrice) {
        super(transactionID, date, amount, customerName, productID);
        this.productName = productName;
        this.quantitySold = quantitySold;
        this.unitPrice = unitPrice;
    }

    /**
     * Calculates the total amount of the product sales.
     */
    @Override
    public void calculateTotal() {
        this.amount = this.quantitySold * this.unitPrice;
    }

    /**
     * Validates the product sales transaction.
     * Throws IllegalArgumentException if quantitySold or unitPrice is less than or equal to zero.
     */
    @Override
    public void validateTransaction() {
        if (this.quantitySold <= 0 || this.unitPrice <= 0) {
            throw new IllegalArgumentException("Quantity sold and unit price must be greater than zero.");
        }
    }

    // Getters and setters for the fields can be added here
}
