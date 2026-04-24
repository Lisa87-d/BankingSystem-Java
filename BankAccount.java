package com.mycompany.bankingsystem;

import java.util.ArrayList;

/**
 * @author air
 */
public class BankAccount {
    private String name;
    private String accNum;
    private String pin; // Added this variable to store the PIN
    private double balance;
    private ArrayList<String> transactions;

    public BankAccount(String name, String accNum, String pin) {
        this.name = name;
        this.accNum = accNum;
        this.pin = pin; // Now correctly saving the PIN
        this.balance = 0.0;
        this.transactions = new ArrayList<>();
        this.transactions.add("Account created with R0.00");
    }

    public void deposit(double amount) {
        if (amount > 0) {
            this.balance += amount;
            transactions.add("Deposited: R" + amount);
        }
    }

    // CHANGED: From 'void' to 'boolean' to fix the compilation error
    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            this.balance -= amount;
            transactions.add("Withdrew: R" + amount);
            return true; // Withdrawal worked!
        } else if (amount > balance) {
            System.out.println("❌ Error: Insufficient funds.");
            return false; // Withdrawal failed
        } else {
            System.out.println("❌ Error: Invalid amount.");
            return false; // Withdrawal failed
        }
    }

    public void showTransactions() {
        System.out.println("\nTransaction History for " + name + " (" + accNum + "):");
        if (transactions.isEmpty()) {
            System.out.println("No transactions found.");
        } else {
            for (String t : transactions) {
                System.out.println("- " + t);
            }
        }
    }

    // Getters
    public String getAccountHolder() { return name; }
    public String getAccountNumber() { return accNum; }
    public double getBalance() { return balance; }

    // FIXED: Implemented actual PIN validation logic
    public boolean validatePin(String enteredPin) {
        return this.pin.equals(enteredPin);
    }
}