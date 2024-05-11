package Interfaces;
import Services.Account;
import Services.Bank;
import Services.Transaction;
import java.util.List;

public interface IBankSystemStorage {
    void addAccount(Account account);

    Account getAccount(String accountId);

    void addBank(Bank bank);

    Bank getBank();

    void depositMoney(String accountId, double amount);

    void withdrawMoney(String accountId, double amount);

    void addTransaction(Transaction transaction);

    Transaction getTransaction(String transactionId);

    List<Account> getAllAccounts();
}
