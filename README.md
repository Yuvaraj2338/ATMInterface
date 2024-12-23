PIN Validation:

A PIN is required to authenticate before performing any ATM operations.
If the user enters an incorrect PIN, they cannot proceed.
BankAccount Class:

Added a pin attribute and validatePin(int inputPin) method to verify the PIN.
ATM Class:

Added isAuthenticated flag to track whether the user has successfully authenticated.
Modified displayMenu() and handleUserChoice() methods to check authentication.
Main Class:

The user is prompted to enter the PIN when accessing the ATM.
