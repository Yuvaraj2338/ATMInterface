package com.pack1;// Class to represent the User's Bank Account
class BankAccount {
    private double balance;
    private int pin;

    // Constructor to initialize the account with a starting balance
    public BankAccount(double initialBalance) {
        if (initialBalance >= 0) {
            this.balance = initialBalance;
        } else {
            System.out.println("Initial balance cannot be negative. Setting balance to 0.");
            this.balance = 0;
        }
        this.pin = -1; // PIN not set initially
    }

    // Getter for balance
    public double getBalance() {
        return balance;
    }

    // Method to generate a PIN
    public void generatePin(int newPin) {
        if (newPin >= 1000 && newPin <= 9999) { // Validate PIN format (4 digits)
            this.pin = newPin;
            System.out.println("PIN successfully set!");
        } else {
            System.out.println("PIN must be a 4-digit number. Try again.");
        }
    }

    // Method to validate the PIN
    public boolean validatePin(int inputPin) {
        return this.pin == inputPin;
    }

    // Method to check if a PIN is set
    public boolean isPinSet() {
        return this.pin != -1;
    }

    // Method to deposit money
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Successfully deposited $" + amount);
        } else {
            System.out.println("Deposit amount must be positive.");
        }
    }

    // Method to withdraw money
    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Successfully withdrew $" + amount);
            return true;
        } else if (amount > balance) {
            System.out.println("Insufficient balance.");
            return false;
        } else {
            System.out.println("Withdrawal amount must be positive.");
            return false;
        }
    }
}

// Class to represent the ATM machine
class ATM {
    private BankAccount account;
    private boolean isAuthenticated = false;

    // Constructor to initialize the ATM with a linked bank account
    public ATM(BankAccount account) {
        this.account = account;
    }

    // Method to set up a PIN if not already set
    public void setupPin() {
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        if (!account.isPinSet()) {
            System.out.print("Set your 4-digit PIN: ");
            int newPin = scanner.nextInt();
            account.generatePin(newPin);
        } else {
            System.out.println("PIN is already set.");
        }
    }

    // Method to authenticate the user
    public void authenticate() {
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        if (!account.isPinSet()) {
            System.out.println("No PIN set. Please set up your PIN first.");
            setupPin();
        }
        System.out.print("Enter your PIN: ");
        int enteredPin = scanner.nextInt();
        if (account.validatePin(enteredPin)) {
            System.out.println("Authentication successful!");
            isAuthenticated = true;
        } else {
            System.out.println("Incorrect PIN. Access denied.");
        }
    }

    // Method to display the main menu
    public void displayMenu() {
        System.out.println("\nATM Menu:");
        System.out.println("1. Check Balance");
        System.out.println("2. Deposit Money");
        System.out.println("3. Withdraw Money");
        System.out.println("4. Exit");
    }

    // Method to handle user choices
    public void handleUserChoice() {
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        int choice;

        while (true) {
            if (!isAuthenticated) {
                System.out.println("\nPlease authenticate first.");
                authenticate();
                if (!isAuthenticated) {
                    continue; // Retry authentication if failed
                }
            }

            displayMenu();
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    checkBalance();
                    break;
                case 2:
                    System.out.print("Enter the amount to deposit: ");
                    double depositAmount = scanner.nextDouble();
                    deposit(depositAmount);
                    break;
                case 3:
                    System.out.print("Enter the amount to withdraw: ");
                    double withdrawAmount = scanner.nextDouble();
                    withdraw(withdrawAmount);
                    break;
                case 4:
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    return; // Exit the loop and end the program
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Method to check balance
    private void checkBalance() {
        System.out.println("Your current balance is $" + account.getBalance());
    }

    // Method to deposit money
    private void deposit(double amount) {
        account.deposit(amount);
    }

    // Method to withdraw money
    private void withdraw(double amount) {
        account.withdraw(amount);
    }
}
