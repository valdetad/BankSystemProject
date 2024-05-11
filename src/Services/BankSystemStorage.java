package Services;
import java.util.ArrayList;
import Interfaces.IBankSystemStorage;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BankSystemStorage implements IBankSystemStorage {

    // Region: Fields
    private final Map<String, Account> accounts = new HashMap<>();
    private final Map<String, Bank> banks = new HashMap<>();
    private final Map<String, Transaction> transactions = new HashMap<>();
    // End Region: Fields

    // Region: IBankSystemStorage Interface Implementation
    @Override
    public void addAccount(Account account) {
        accounts.put(account.getAccountId(), account);
    }

    @Override
    public Account getAccount(String accountId) {
        return accounts.get(accountId);
    }

    @Override
    public void addBank(Bank bank) {
        banks.put(bank.getBankName(), bank);
    }

    @Override
    public Bank getBank() {
        if (banks.isEmpty()) {
            return null;
        }
        return banks.values().iterator().next();
    }

    @Override
    public void depositMoney(String accountId, double amount) {
        Account account = accounts.get(accountId);
        if (account == null) {
            throw new IllegalArgumentException("Account not found.");
        }
        account.deposit(amount, "Deposit");
    }

    @Override
    public void withdrawMoney(String accountId, double amount) {
        Account account = accounts.get(accountId);
        if (account == null) {
            throw new IllegalArgumentException("Account not found.");
        }
        account.withdraw(amount, "Withdrawal");
    }

    @Override
    public void addTransaction(Transaction transaction) {
        transactions.put(transaction.getTransactionId(), transaction);
    }

    @Override
    public Transaction getTransaction(String transactionId) {
        return transactions.get(transactionId);
    }

    @Override
    public List<Account> getAllAccounts() {
        return new ArrayList<>(accounts.values());
    }
    // End Region: IBankSystemStorage Interface Implementation
}
