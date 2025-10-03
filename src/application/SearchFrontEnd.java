package application;

import java.util.Scanner;
import application.AccountDatabase;
import application.Account;

/**
 * Simple Search Front End for Accounts.
 * This class provides a basic console-based search
 * to find accounts by ID.
 */
public class SearchFrontEnd {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Account Search ===");
        System.out.print("Enter Account ID to search: ");
        String accountId = scanner.nextLine();
      
      // Connect to the AccountDatabase
        AccountDatabase accountDB = new AccountDatabase();

// Search for the account by ID
        Account account = accountDB.getAccountById(accountId);

        if (account != null) {
          System.out.println("Account found:");
          System.out.println("ID: " + account.getId());
          System.out.println("Name: " + account.getName());
          System.out.println("Email: " + account.getEmail());
        } else {
          System.out.println("Account with ID " + accountId + " not found.");
        }

         scanner.close();
    }
}
