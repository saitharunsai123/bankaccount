import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Account.java
 * Basic bank account class with encapsulation and transaction history.
 */
public class Account {
    private String accountHolderName;
    private String accountNumber;
    private double balance;
    private ArrayList<String> transactions;

    private static final DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public Account(String accountHolderName, String accountNumber, double initialBalance) {
        this.accountHolderName = accountHolderName;
        this.accountNumber = accountNumber;
        this.balance = Math.max(0, initialBalance);
        this.transactions = new ArrayList<>();
        transactions.add(time() + " | Account opened | Balance: " + format(balance));
    }

    // Encapsulated accessors
    public String getAccountHolderName() {
        return accountHolderName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    // Deposit method
    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Deposit amount must be positive.");
            return;
        }
        balance += amount;
        String record = time() + " | Deposit: +" + format(amount) + " | Balance: " + format(balance);
        transactions.add(record);
        System.out.println("Deposited: " + format(amount));
    }

    // Withdraw method
    public boolean withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Withdrawal amount must be positive.");
            return false;
        }
        if (amount > balance) {
            System.out.println("Insufficient funds. Withdrawal cancelled.");
            return false;
        }
        balance -= amount;
        String record = time() + " | Withdraw: -" + format(amount) + " | Balance: " + format(balance);
        transactions.add(record);
        System.out.println("Withdrew: " + format(amount));
        return true;
    }

    // Display balance
    public void displayBalance() {
        System.out.println("Account: " + accountNumber + " | Holder: " + accountHolderName);
        System.out.println("Current balance: " + format(balance));
    }

    // Show transaction history
    public void showTransactionHistory() {
        System.out.println("\nTransaction History for " + accountNumber + " (" + accountHolderName + "):");
        if (transactions.isEmpty()) {
            System.out.println("No transactions yet.");
            return;
        }
        for (String t : transactions) {
            System.out.println(t);
        }
        System.out.println();
    }

    // Helper for formatted time
    private String time() {
        return LocalDateTime.now().format(fmt);
    }

    // Helper for formatting money (simple)
    private String format(double v) {
        return String.format("$%.2f", v);
    }

    // Protected helper for subclasses if needed (keeps field private but allows controlled mutation)
    protected void addTransaction(String note) {
        transactions.add(time() + " | " + note + " | Balance: " + format(balance));
    }

    // Allow subclasses to update balance in a controlled way
    protected void changeBalanceBy(double delta) {
        this.balance += delta;
    }
}
