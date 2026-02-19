/**
 * SavingsAccount.java
 * Extends Account and provides interest calculation and a minimum balance rule.
 */

public class SavingsAccount extends Account {
    private double annualInterestRate; // percentage, e.g., 2.5 for 2.5%
    private static final double MIN_BALANCE = 100.0; // minimum balance to maintain

    public SavingsAccount(String holder, String accNumber, double initialBalance, double annualInterestRate) {
        super(holder, accNumber, initialBalance);
        this.annualInterestRate = Math.max(0, annualInterestRate);
    }

    // Override withdraw to enforce minimum balance
    @Override
    public boolean withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Withdrawal amount must be positive.");
            return false;
        }
        double projected = getBalance() - amount;
        if (projected < MIN_BALANCE) {
            System.out.println("Cannot withdraw " + String.format("$%.2f", amount) + ". Account must maintain a minimum balance of " + String.format("$%.2f", MIN_BALANCE) + ".");
            return false;
        }
        // Call parent implementation to do the actual withdrawal and transaction recording
        boolean ok = super.withdraw(amount);
        if (ok) {
            addTransaction("Savings withdrawal processed");
        }
        return ok;
    }

    // Add interest to the account. This uses the public deposit method (keeps encapsulation).
    public void addInterest() {
        double monthlyRate = annualInterestRate / 12.0 / 100.0; // convert % to fraction
        double interest = getBalance() * monthlyRate;
        if (interest <= 0) {
            System.out.println("No interest to add.");
            return;
        }
        // Use deposit to add interest so it gets recorded consistently
        deposit(interest);
        // Also add a descriptive transaction note
        addTransaction("Monthly interest added at " + annualInterestRate + "% p.a.");
        System.out.println("Interest added: " + String.format("$%.2f", interest));
    }

    public double getAnnualInterestRate() {
        return annualInterestRate;
    }

    public void setAnnualInterestRate(double annualInterestRate) {
        if (annualInterestRate >= 0) {
            this.annualInterestRate = annualInterestRate;
        }
    }
}
