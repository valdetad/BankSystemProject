package Interfaces;

public interface ITransaction {
    String getTransactionId();

    double getAmount();

    String getOriginatingAccountId();

    String getResultingAccountId();

    String getTransactionType();
}
