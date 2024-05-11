package Interfaces;

public interface IAccount {
    String getAccountId();

    double getBalance();

    void deposit(double amount, String reason);

    void withdraw(double amount, String reason);
}
