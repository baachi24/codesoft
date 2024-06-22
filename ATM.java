import java.util.Scanner;

public class ATM {
    private BankAccount account;
    private Scanner scanner;

    public ATM(double initialBalance) {
        this.account = new BankAccount(initialBalance);
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        int choice;
        do {
            displayDashboard();
            choice = getIntInput("Enter your choice: ");

            switch (choice) {
                case 1:
                    checkBalance();
                    break;
                case 2:
                    deposit();
                    break;
                case 3:
                    withdraw();
                    break;
                case 4:
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 4);

        scanner.close();
    }

    private void displayDashboard() {
        System.out.println("\n=== ATM Dashboard ===");
        System.out.println("1. Check Balance");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Exit");
    }

    private void checkBalance() {
        System.out.println("Current Balance: $" + account.getBalance());
    }

    private void deposit() {
        double amount = getDoubleInput("Enter amount to deposit: ");
        account.deposit(amount);
    }

    private void withdraw() {
        double amount = getDoubleInput("Enter amount to withdraw: ");
        account.withdraw(amount);
    }

    private int getIntInput(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.out.print("Invalid input. Please enter an integer: ");
            scanner.next(); // consume non-integer input
        }
        return scanner.nextInt();
    }

    private double getDoubleInput(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextDouble()) {
            System.out.print("Invalid input. Please enter a valid amount: ");
            scanner.next(); // consume non-double input
        }
        return scanner.nextDouble();
    }

    public static void main(String[] args) {
        ATM atm = new ATM(1000); // Initialize with an initial balance of $1000
        atm.start();
    }

    // BankAccount class
    private static class BankAccount {
        private double balance;

        public BankAccount(double balance) {
            this.balance = balance;
        }

        public double getBalance() {
            return balance;
        }

        public void deposit(double amount) {
            if (amount > 0) {
                balance += amount;
                System.out.println("Deposit of $" + amount + " successful. New balance: $" + balance);
            } else {
                System.out.println("Deposit amount must be greater than zero.");
            }
        }

        public void withdraw(double amount) {
            if (amount > 0) {
                if (balance >= amount) {
                    balance -= amount;
                    System.out.println("Withdrawal of $" + amount + " successful. New balance: $" + balance);
                } else {
                    System.out.println("Insufficient funds. Withdrawal failed.");
                }
            } else {
                System.out.println("Withdrawal amount must be greater than zero.");
            }
        }
    }
}
