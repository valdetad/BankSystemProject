import Services.Account;
import Services.Bank;
import Services.BankSystemStorage;
import Shared.ApplicationVariables;

import java.util.Scanner;

public class Main {
    private final Scanner scanner;
    private final BankSystemStorage bankSystem;

    public Main() {
        this.scanner = new Scanner(System.in);
        this.bankSystem = new BankSystemStorage();


    }

    public void start() {
        try {
            createBankAndAccounts();
            performTransactions();
            displayAccountBalances();
            displayBankDetails();
        } catch (Exception e) {
            handleException(e);
        } finally {
            scanner.close();
        }
    }

    private void createBankAndAccounts() {
        createBank();
        createAccount("1");
        createAccount("2");
    }

    private void createBank() {
        System.out.println("Enter bank name:");
        String bankName = scanner.nextLine();
        System.out.println("Enter transaction flat fee amount in $ or %:");
        double transactionFlatFeeAmount = parseFeeInput();
        System.out.println("Enter transaction percent fee value in $ or %:");
        double transactionPercentFeeValue = parseFeeInput();
        bankSystem.addBank(new Bank(bankName, transactionFlatFeeAmount, transactionPercentFeeValue));
    }

    private double parseFeeInput() {
        String input = scanner.nextLine().trim();
        if (input.endsWith("%")) {
            try {
                double percentage = Double.parseDouble(input.substring(0, input.length() - 1));
                return percentage / ApplicationVariables.PERCENTAGE_DIVISOR;
            } catch (NumberFormatException e) {
                System.out.println("Invalid percentage format.");
                return 0;
            }
        } else {
            try {
                return Double.parseDouble(input.replace("$", ""));
            } catch (NumberFormatException e) {
                System.out.println("Invalid flat fee format.");
                return 0;
            }
        }
    }

    private void createAccount(String accountId) {
        System.out.println("Enter account holder's name for account " + accountId + ":");
        String userName = scanner.nextLine();
        System.out.println("Enter initial balance for account " + accountId + ":");
        double initialBalance = parseDoubleInput();
        bankSystem.addAccount(new Account(accountId, userName, initialBalance));
    }

    private void performTransactions() {
        boolean exit = false;
        while (!exit) {
            displayOptions();
            int choice = parseIntInput();
            switch (choice) {
                case 1:
                    transferMoney();
                    break;
                case 2:
                    depositMoney();
                    break;
                case 3:
                    withdrawMoney();
                    break;
                case 4:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid option. Please choose again.");
            }
        }
    }

    private void transferMoney() {
        System.out.println("Enter amount to transfer:");
        double transferAmount = parseDoubleInput();
        System.out.println("Enter source account ID:");
        String sourceAccountId = scanner.nextLine();
        System.out.println("Enter target account ID:");
        String targetAccountId = scanner.nextLine();
        try {
            Bank bank = bankSystem.getBank();
            Account sourceAccount = bankSystem.getAccount(sourceAccountId);
            Account targetAccount = bankSystem.getAccount(targetAccountId);
            if (bank != null && sourceAccount != null && targetAccount != null) {
                bank.transferMoney(sourceAccount, targetAccount, transferAmount);
                System.out.println("Transfer successful.");
            } else {
                System.out.println("Invalid account or bank not found.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Transfer failed: " + e.getMessage());
        }
    }

    private void depositMoney() {
        System.out.println("Enter amount to deposit:");
        double depositAmount = parseDoubleInput();
        System.out.println("Enter account ID:");
        String accountId = scanner.nextLine();
        try {
            bankSystem.depositMoney(accountId, depositAmount);
            System.out.println("Deposit successful.");
        } catch (IllegalArgumentException e) {
            System.out.println("Deposit failed: " + e.getMessage());
        }
    }

    private void withdrawMoney() {
        System.out.println("Enter amount to withdraw:");
        double withdrawAmount = parseDoubleInput();
        System.out.println("Enter account ID:");
        String accountId = scanner.nextLine();
        try {
            bankSystem.withdrawMoney(accountId, withdrawAmount);
            System.out.println("Withdrawal successful.");
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println("Withdrawal failed: " + e.getMessage());
        }
    }

    private double parseDoubleInput() {
        while (true) {
            try {
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number:");
            }
        }
    }
    private int parseIntInput() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number:");
            }
        }
    }

    private void displayOptions() {
        System.out.println("Choose an option:");
        System.out.println("1. Transfer money between accounts");
        System.out.println("2. Deposit money to an account");
        System.out.println("3. Withdraw money from an account");
        System.out.println("4. Exit");
    }

    private void displayAccountBalances() {
        System.out.println("\nAccount balances:");
        for (Account account : bankSystem.getAllAccounts()) {
            System.out.println("Account " + account.getAccountId() + " balance: $" + account.getBalance());
        }
    }

    private void displayBankDetails() {
        Bank bank = bankSystem.getBank();
        if (bank != null) {
            System.out.println("\nBank details:");
            System.out.println("Bank name: " + bank.getBankName());
            System.out.println("Total transaction fee amount: $" + bank.getTotalTransactionFeeAmount());
            System.out.println("Total transfer amount: $" + bank.getTotalTransferAmount());
            System.out.println("Transaction flat fee amount: $" + bank.getTransactionFlatFeeAmount());
            System.out.println("Transaction percent fee value: " + bank.getTransactionPercentFeeValue());
        } else {
            System.out.println("There is no bank found.");
        }
    }

    private void handleException(Exception e) {
        System.out.println("An unexpected error occurred: " + e.getMessage());
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.start();

    }

}
