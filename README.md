# Bank Account Simulation (Java)

This is a beginner-friendly Java project that simulates a simple bank account using OOP concepts: encapsulation, inheritance, method overriding, and polymorphism.

Files
- `Account.java` - Base class representing a bank account. Keeps private fields and a transaction history. Implements deposit, withdraw, displayBalance and showTransactionHistory.
- `SavingsAccount.java` - Extends `Account`. Adds an interest rate and a minimum balance rule, overrides `withdraw()` and provides `addInterest()`.
- `BankApp.java` - Menu-driven main application. Demonstrates runtime polymorphism by referencing a `SavingsAccount` via an `Account` variable and calling overridden methods.

How to compile and run (Windows, cmd.EXE)

Open a command prompt in this project folder (`c:\Users\om\Desktop\bankaccount`) and run:

```cmd
javac Account.java SavingsAccount.java BankApp.java
java BankApp
```

The program is interactive. It will ask you to enter account holder name, account number, initial deposit, and optionally an interest rate. Then use the menu to deposit, withdraw, add interest, view balance or transactions.

Notes
- The `SavingsAccount` enforces a minimum balance of $100 for withdrawals.
- Interest calculation implemented is a simple monthly interest based on annual rate provided.
- The code records transactions with timestamps for simple history tracking.

This project is intentionally simple to help learn Java OOP basics. Feel free to extend it (multiple accounts, persistence, CLI improvements, unit tests).
