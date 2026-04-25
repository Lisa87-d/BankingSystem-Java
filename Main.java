
 

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Bank bank = new Bank();
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("=== WELCOME TO NOVA BANK ===");

            boolean running = true;
            while (running) {
                System.out.println("\n--- MENU ---");
                System.out.println("1. Create Account");
                System.out.println("2. Deposit");
                System.out.println("3. Withdraw");
                System.out.println("4. Check Balance");
                System.out.println("5. View Transactions");
                System.out.println("6. Show All Accounts");
                System.out.println("7. Exit");
                System.out.print("Choose option: ");

                if (!scanner.hasNextInt()) {
                    System.out.println("Please enter a valid number (1-7).");
                    scanner.next();
                    continue;
                }

                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1 -> {
                        System.out.print("Enter account holder name: ");
                        String name = scanner.nextLine();
                        System.out.print("Enter account number: ");
                        String accNum = scanner.nextLine();
                        System.out.print("Create a 4-digit PIN: ");
                        String pin = scanner.nextLine();

                        bank.createAccount(name, accNum, pin);
                        System.out.println("✅ SUCCESS: Account for " + name + " created!");
                    }

                    case 2 -> {
                        System.out.print("Enter account number: ");
                        String depAccNum = scanner.nextLine();
                        BankAccount depositAccount = bank.getAccount(depAccNum);

                        if (depositAccount == null) {
                            System.out.println("❌ ERROR: Account not found.");
                        } else {
                            try {
                                System.out.print("Enter amount to deposit: R");
                                double dAmount = Double.parseDouble(scanner.nextLine());
                                depositAccount.deposit(dAmount);
                                System.out.println("✅ SUCCESS: R" + dAmount + " deposited.");
                            } catch (NumberFormatException e) {
                                System.out.println("❌ ERROR: Invalid amount format.");
                            }
                        }
                    }

                    case 3 -> {
                        System.out.print("Enter account number: ");
                        String witAccNum = scanner.nextLine();
                        BankAccount wAcc = bank.getAccount(witAccNum);
                        
                        if (wAcc != null) {
                            System.out.print("Enter PIN: ");
                            String enteredPin = scanner.nextLine();
                            
                            if (wAcc.validatePin(enteredPin)) {
                                System.out.print("Enter amount: R");
                                try {
                                    double wAmt = Double.parseDouble(scanner.nextLine());
                                    if (wAcc.withdraw(wAmt)) {
                                        System.out.println("✅ Withdrawal successful!");
                                    }
                                } catch (NumberFormatException e) {
                                    System.out.println("❌ ERROR: Invalid amount.");
                                }
                            } else {
                                System.out.println("❌ Incorrect PIN.");
                            }
                        } else {
                            System.out.println("❌ Account not found.");
                        }
                    }

                    case 4 -> {
                        System.out.print("Enter account number: ");
                        String cAccNum = scanner.nextLine();
                        BankAccount checkAcc = bank.getAccount(cAccNum);

                        if (checkAcc != null) {
                            System.out.print("Enter PIN: ");
                            String enteredPin = scanner.nextLine();
                            if (checkAcc.validatePin(enteredPin)) {
                                System.out.println("Balance: R" + checkAcc.getBalance());
                            } else {
                                System.out.println("❌ ERROR: Incorrect PIN.");
                            }
                        } else {
                            System.out.println("❌ Account not found.");
                        }
                    }

                    case 5 -> {
                        System.out.print("Enter account number: ");
                        String tAccNum = scanner.nextLine();
                        BankAccount transAcc = bank.getAccount(tAccNum);

                        if (transAcc != null) {
                            System.out.print("Enter PIN: ");
                            String enteredPin = scanner.nextLine();
                            if (transAcc.validatePin(enteredPin)) {
                                transAcc.showTransactions();
                            } else {
                                System.out.println("❌ ERROR: Incorrect PIN.");
                            }
                        } else {
                            System.out.println("❌ Account not found.");
                        }
                    }

                    case 6 -> bank.showAllAccounts();

                    case 7 -> {
                        System.out.println("Goodbye!");
                        running = false;
                    }

                    default -> System.out.println("Invalid choice.");
                }
            }
        }
    }
}
