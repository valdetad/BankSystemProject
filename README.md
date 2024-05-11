# Bank System

# Description

The BankSystem console application is designed to create multiple user accounts and transactional operations. 
With a focus on dollar-based transactions rounded to two decimal places,The application includes important classes like Bank, Account, and Transaction, allowing smooth financial operations.

# Interfaces
The Interfaces layer defines various interfaces that are implemented in the Services layer. These interfaces provide the structure for the components of the banking system.

IAccount: Interface for representing bank accounts.
IBank: Interface for representing banks.
IBankSystemStorage: Interface for managing storage and retrieval of bank-related data.
ITransaction: Interface for representing transactions.

# Services Layer
The Services layer contains the core implementation of the banking system, including classes for accounts, banks, transactions, and storage management.

Account
Represents a bank account, providing methods for depositing, withdrawing, and retrieving account information.

Bank
Represents a bank entity, managing accounts and facilitating transactions. It calculates transaction fees and tracks transaction statistics.

BankSystemStorage
Manages storage and retrieval of account, bank, and transaction data. Implements the IBankSystemStorage interface.

Transaction
Represents a financial transaction between bank accounts, storing details such as amount, source, and destination accounts.

# Shared Layer
The Shared layer contains shared resources and utilities used across the project.
ApplicationVariables
Contains shared variables and constants used throughout the application.

# Usage
The project's main functionality is executed through the Main class. Upon running the program, users can create banks, accounts, perform transactions, and view account balances and bank details through a console-based interface.

# How to Run
To run the BankSystem project, follow these steps:
Compile all Java files in the project.
Execute the Main class.
