package Services;
import Interfaces.ITransaction;

public class Transaction implements ITransaction {

    // Region: Fields
    private static int idCounter = 0;
    private String transactionId;
    private double amount;
    private String originatingAccountId;
    private String resultingAccountId;
    private String transactionType;
    // End Region: Fields

    // Region: Constructors
    public Transaction(double amount, String originatingAccountId, String resultingAccountId, String transactionType) {

        try {
            if (amount <= 0) {
                throw new IllegalArgumentException("Transaction amount must be greater than zero.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }

        if (originatingAccountId == null || originatingAccountId.isEmpty()) {
            throw new IllegalArgumentException("Account ID cannot be null or empty.");
        }
        if (resultingAccountId == null || resultingAccountId.isEmpty()) {
            throw new IllegalArgumentException("Resulting account ID cannot be null or empty.");
        }

        this.transactionId = "T" + idCounter++;
        this.amount = amount;
        this.originatingAccountId = originatingAccountId;
        this.resultingAccountId = resultingAccountId;
        this.transactionType = transactionType;
    }
    // End Region: Constructors

    // Region: ITransaction Interface Implementation
    @Override
    public String getTransactionId() {
        return transactionId;
    }

    @Override
    public double getAmount() {
        return amount;
    }

    @Override
    public String getOriginatingAccountId() {
        return originatingAccountId;
    }

    @Override
    public String getResultingAccountId() {
        return resultingAccountId;
    }

    @Override
    public String getTransactionType() {
        return transactionType;
    }
    // End Region: ITransaction Interface Implementation
}
