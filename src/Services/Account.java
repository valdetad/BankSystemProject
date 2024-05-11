package Services;
import Interfaces.IAccount;
import java.util.ArrayList;
import java.util.List;
import java.text.DecimalFormat;

public class Account implements IAccount {
    // Region: Fields
    private String accountId;
    private String userName;
    private double balance;
    private List<Transaction> transactions;
    // End Region: Fields

    // Region: Constructors
    public Account(String accountId, String userName, double initialBalance) {
        this.accountId = accountId;
        this.userName = userName;
        this.balance = initialBalance;
        this.transactions = new ArrayList<>();
    }
    // End Region: Constructors

    // Region: Public Methods
    public String getAccountId() {
        return accountId;
    }

    public String getUserName() {
        return userName;
    }

    public double getBalance() {
        DecimalFormat df = new DecimalFormat("#0.00");
        return Double.parseDouble(df.format(balance));
    }

    public void deposit(double amount, String reason) {
        validateAmount(amount);
        balance += amount;
        addTransaction(amount, reason);
    }

    public void withdraw(double amount, String reason) {
        validateAmount(amount);
        checkSufficientFunds(amount);
        balance -= amount;
        addTransaction(amount, reason);
    }
    // End Region: Public Methods

    // Region: Private Methods
    private void validateAmount(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero.");
        }
    }

    private void checkSufficientFunds(double amount) {
        if (balance < amount) {
            throw new IllegalStateException("Insufficient funds.");
        }
    }

    private void addTransaction(double amount, String reason) {
        transactions.add(new Transaction(amount, accountId, accountId, reason));
    }
    // End Region: Private Methods
}
