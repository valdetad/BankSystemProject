package Services;
import Interfaces.IBank;
import java.util.ArrayList;
import java.util.List;

public class Bank implements IBank {
    // Region: Fields
    private String bankName;
    private List<Account> accounts;
    private double totalTransactionFeeAmount;
    private double totalTransferAmount;
    private double transactionFlatFeeAmount;
    private double transactionPercentFeeValue;
    // End Region: Fields

    // Region: Constructor
    public Bank(String bankName, double transactionFlatFeeAmount, double transactionPercentFeeValue) {
        if (transactionFlatFeeAmount < 0 || transactionPercentFeeValue < 0) {
            throw new IllegalArgumentException("Transaction fees cannot be negative.");
        }
        this.bankName = bankName;
        this.accounts = new ArrayList<>();
        this.totalTransactionFeeAmount = 0;
        this.totalTransferAmount = 0;
        this.transactionFlatFeeAmount = transactionFlatFeeAmount;
        this.transactionPercentFeeValue = transactionPercentFeeValue;
    }
    // End Region: Constructor

    // Region: IBank Interface Implementation
    @Override
    public String getBankName() {
        return bankName;
    }

    @Override
    public double getTotalTransactionFeeAmount() {
        return totalTransactionFeeAmount;
    }

    @Override
    public double getTotalTransferAmount() {
        return totalTransferAmount;
    }

    @Override
    public double getTransactionFlatFeeAmount() {
        return transactionFlatFeeAmount;
    }

    @Override
    public double getTransactionPercentFeeValue() {
        return transactionPercentFeeValue;
    }

    @Override
    public void addAccount(Account account) {
        accounts.add(account);
    }

    @Override
    public void transferMoney(Account sourceAccount, Account targetAccount, double amount) {
        double fee = calculateTransactionFee(amount);
        if (sourceAccount.getBalance() < amount + fee) {
            throw new IllegalArgumentException("Insufficient funds for transfer.");
        }
        sourceAccount.withdraw(amount + fee, "Transfer to " + targetAccount.getAccountId());
        targetAccount.deposit(amount, "Transfer from " + sourceAccount.getAccountId());
        updateTotalTransferAmount(amount);
        updateTotalTransactionFeeAmount(fee);
    }
    // End Region: IBank Interface Implementation

    // Region: Helper Methods
    private double calculateTransactionFee(double amount) {
        return transactionFlatFeeAmount + (transactionPercentFeeValue / 100.0) * amount;
    }

    private void updateTotalTransactionFeeAmount(double fee) {
        totalTransactionFeeAmount += fee;
    }

    private void updateTotalTransferAmount(double amount) {
        totalTransferAmount += amount;
    }
    // End Region: Helper Methods
}
