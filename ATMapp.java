package com.pack1;
//Main class to run the program
public class ATMapp {
 public static void main(String[] args) {
     // Create a BankAccount instance with an initial balance of $500
     BankAccount myAccount = new BankAccount(500);

     // Create an ATM instance
     ATM myATM = new ATM(myAccount);

     // Prompt user to set up their PIN before proceeding
     myATM.setupPin();

     // Handle user choices in a loop
     myATM.handleUserChoice();
 }
}
