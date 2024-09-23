import java.time.LocalDate;

/**
 * Abstract class representing a sales transaction.
 */
public abstract class SalesTransaction {
    protected String transactionID;
    protected LocalDate date;
    protected double amount;
    protected String customerName;
    protected String productID;

    /**
     * Constructor for SalesTransaction.
     * @param transactionID Unique ID for the transaction.
     * @param date Date of the transaction.
     * @param amount Amount of the transaction.
     * @param customerName Name of the customer.
     * @param productID ID of the product.
     */
    public SalesTransaction(String transactionID, LocalDate date, double amount, String customerName, String productID) {
        this.transactionID = transactionID;
        this.date = date;
        this.amount = amount;
        this.customerName = customerName;
        this.productID = productID;
    }

    /**
     * Abstract method to calculate the total amount of the transaction.
     */
    public abstract void calculateTotal();

    /**
     * Abstract method to validate the transaction.
     */
    public abstract void validateTransaction();
}
