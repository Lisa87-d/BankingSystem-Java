
 package com.mycompany.bankingsystem;

import java.util.HashMap; 

class Bank {
    // This is the "vault" where all accounts are stored
    private HashMap<String, BankAccount> accounts = new HashMap<>();

    // 1. Logic to create an account
    void createAccount(String name, String accNum, String pin) {
        // FIXED: Now passing the 'pin' to the BankAccount constructor
        BankAccount newAcc = new BankAccount(name, accNum, pin); 
        accounts.put(accNum, newAcc);
    }

    // 2. Logic to find an account
    BankAccount getAccount(String accNum) {
        return accounts.get(accNum); // Returns null if not found
    }

    // 3. Logic to show everything in the bank
    void showAllAccounts() {
        if (accounts.isEmpty()) {
            System.out.println("No accounts in the system.");
            return;
        }
        System.out.println("\n--- LIST OF ALL ACCOUNTS ---");
        for (BankAccount acc : accounts.values()) {
            System.out.println("Holder: " + acc.getAccountHolder() + 
                               " | No: " + acc.getAccountNumber() + 
                               " | Balance: R" + acc.getBalance());
        }
    }
}