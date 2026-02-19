import java.util.Scanner;

/**
 * BankApp.java
 * Menu-driven application demonstrating Account and SavingsAccount.
 */
public class BankApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to the Simple Bank App (Savings Account)");

        System.out.print("Enter account holder name: ");
        String name = sc.nextLine().trim();
        System.out.print("Enter account number: ");
        String accNo = sc.nextLine().trim();

        double initial = 0.0;
        while (true) {
            System.out.print("Enter initial deposit (minimum $100 recommended): ");
            String in = sc.nextLine().trim();
            try {
                initial = Double.parseDouble(in);
                if (initial < 0) {
                    System.out.println("Initial deposit cannot be negative.");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }

        double interestRate = 2.5; // default interest rate
        System.out.print("Enter annual interest rate in % (press Enter for default 2.5%): ");
        String r = sc.nextLine().trim();
        if (!r.isEmpty()) {
            try {
                interestRate = Double.parseDouble(r);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input, using default rate 2.5%.");
            }
        }

        // Demonstrate polymorphism: use Account reference to hold a SavingsAccount
        Account account = new SavingsAccount(name, accNo, initial, interestRate);

        boolean exit = false;
        while (!exit) {
            System.out.println("\nSelect an option:");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Display Balance");
            System.out.println("4. Add Interest (monthly)");
            System.out.println("5. Show Transactions");
            System.out.println("6. Exit");
            System.out.print("Choice: ");

            String choice = sc.nextLine().trim();
            switch (choice) {
                case "1":
                    System.out.print("Enter amount to deposit: ");
                    try {
                        double amt = Double.parseDouble(sc.nextLine().trim());
                        account.deposit(amt);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid amount.");
                    }
                    break;
                case "2":
                    System.out.print("Enter amount to withdraw: ");
                    try {
                        double amt = Double.parseDouble(sc.nextLine().trim());
                        // This uses runtime polymorphism - the SavingsAccount.withdraw override will run.
                        account.withdraw(amt);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid amount.");
                    }
                    break;
                case "3":
                    account.displayBalance();
                    break;
                case "4":
                    // Need to call addInterest which exists on SavingsAccount, so check and cast.
                    if (account instanceof SavingsAccount) {
                        SavingsAccount sa = (SavingsAccount) account;
                        sa.addInterest();
                    } else {
                        System.out.println("This account type does not support interest.");
                    }
                    break;
                case "5":
                    account.showTransactionHistory();
                    break;
                case "6":
                    exit = true;
                    System.out.println("Thank you for using Simple Bank App. Goodbye!");
                    break;
                default:
                    System.out.println("Please choose a valid option (1-6).");
            }
        }

        sc.close();
    }
}
