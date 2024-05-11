package Interfaces;
import Services.Account;

public interface IBank {
    String getBankName();

    double getTotalTransactionFeeAmount();

    double getTotalTransferAmount();

    double getTransactionFlatFeeAmount();

    double getTransactionPercentFeeValue();

    void addAccount(Account account);

    void transferMoney(Account sourceAccount, Account targetAccount, double amount);
}
