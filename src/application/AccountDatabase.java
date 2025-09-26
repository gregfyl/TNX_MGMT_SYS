package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * AccountDatabase Class
 * This is an array-based container class with an initial capacity of 5.
 * It will automatically grow the capacity by 5 if the database is full.
 * The array shall hold different account instances in Checking, Savings or MoneyMarket.
 */

public class AccountDatabase {

	/** Array-based implementation of accounts. */
	private Account[] accounts;
	/** Number of accounts. */
	private int size;
	/** To avoid magic number. */
	private static final int INITIAL_SIZE = 0;
	/** To avoid magic number. */
	private static final int INITIAL_CAPACITY = 5;
	/** To avoid magic number. */
	private static final int GROWED_CAPACITY = 5;
	/** To avoid magic number. */
	private static final int LAST_NO = -1;
	/** To avoid magic number. */
	private static final int EMPTY = 0;

	/**
	 * Initialize accounts.
	 */
	public AccountDatabase() {
		accounts = new Account[INITIAL_CAPACITY];
		size = INITIAL_SIZE;
	}

	/**
	 * Find an account from accounts.
	 * @param account Target account to be found.
	 * @return Index of the target account in accounts. -1 if no target account.
	 */
	private int find(Account account) {
		for (int i = 0; i < size; i++) {
			if (accounts[i].equals(account)) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * Find an account from accounts.
	 * @param accountId Account unique identifier.
	 * @return Index of the target account in accounts. -1 if no target account.
	 */
	public int findByAccountId(String accountId) {
		for (int i = 0; i < size; i++) {
			if (accounts[i].getAccountId().equals(accountId)) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * Find an account from accounts.
	 *
	 * @param fname First name of the account holder.
	 * @param lname Last name of the account holder.
	 * @return Array index of the target account in accounts.
	 */
	public ArrayList<Integer> findByName(String account_type, String fname, String lname) {
		ArrayList<Integer> results = new ArrayList<Integer>();
		for (int i = 0; i < size; i++) {
			if (accounts[i].getClass().getSimpleName().equals(account_type) && accounts[i].getHolder().getFname().equals(fname) && accounts[i].getHolder().getLname().equals(lname)) {
				results.add(i);
			}
		}
		return results;
	}

	/**
	 * Grow the capacity of accounts by 5.
	 */
	private void grow() {
		Account[] newaccounts = new Account[accounts.length + GROWED_CAPACITY];
		for (int i = 0; i < size; i++) {
			newaccounts[i] = accounts[i];
		}
		accounts = newaccounts;
	}

	/**
	 * Add an account to accounts.
	 * @param account The account to be added.
	 * @return True if the account is added.<br>
	 *         False if the account exists.
	 */
	public boolean add(Account account) {
		if (findByAccountId(account.getAccountId()) != -1) {
			return false;
		}
		if (size == accounts.length) {
			grow();
		}
		accounts[size] = account;
		size++;
		return true;
	}

	/**
	 * Remove an account from accounts.
	 * @param account The account to be removed.
	 * @return True if the account is removed.<br>
	 *         False if the account does not exist.
	 */
	public boolean remove(Account account) {
		int index = find(account);
		if (index == LAST_NO) {
			return false;
		}
		accounts[index] = accounts[size + LAST_NO];
		accounts[size + LAST_NO] = null;
		size--;
		return true;
	}

	/**
	 * Deposit money to an account.
	 * @param account The account to be deposited to.
	 * @param amount  Amount to be deposited.
	 * @return True if money is deposited to the account.<br>
	 *         False if the account does not exist.
	 */
	public boolean deposit(Account account, double amount) {
		int index = find(account);
		if (index == LAST_NO) {
			return false;
		}
		accounts[index].credit(amount);
		return true;
	}

	/**
	 * Withdraw money from an account.
	 * @param account The account to be withdrawn from.
	 * @param amount  Amount to be withdrawn.
	 * @return 0 if money is withdrawn from the account.<br>
	 *         1 if insufficient funds in the account.<br>
	 *         -1 if the account does not exist.
	 */
	public int withdrawal(Account account, double amount) {
		int index = find(account);
		if (index == LAST_NO) {
			return -1;
		} else if (accounts[index].getBalance() < amount) {
			return 1;
		}
		accounts[index].debit(amount);
		if (accounts[index].getClass().getSimpleName().equals("MoneyMarket")) {
			((MoneyMarket) accounts[index]).increaseWithdrawals();
		}
		return 0;
	}

	/**
	 * Sort accounts by date of accounts opened in ascending order.
	 */
	private void sortByDateOpen() {
		for (int i = 0; i < size; i++) {
			for (int j = i; j >= 0; j--) {
				if (accounts[j].getDateOpen().compareTo(accounts[i].getDateOpen()) > 0) {
					Account temp = accounts[j];
					accounts[j] = accounts[i];
					accounts[i] = temp;
					i = j;
				}
			}
		}
	}

	/**
	 * Sort accounts by the last name of the holder of accounts in ascending order.
	 */
	private void sortByLastName() {
		for (int i = 0; i < size; i++) {
			for (int j = i; j >= 0; j--) {
				if (accounts[j].getHolder().getLname().compareTo(accounts[i].getHolder().getLname()) > 0) {
					Account temp = accounts[j];
					accounts[j] = accounts[i];
					accounts[i] = temp;
					i = j;
				}
			}
		}
	}

	/**
	 * Print accounts by date of accounts opened in ascending order.
	 * @return Accounts in string format.
	 */
	public String statementByDateOpen() {
		String output = "";
		if (size == INITIAL_SIZE) {
			return "Database is empty.\n";
		}
		sortByDateOpen();
		output += "--Printing statements by date opened--\n";
		for (int i = 0; i < size; i++) {
			output += accounts[i].toString() + "\n";

			double interest = Double.valueOf(accounts[i].monthlyInterest());
			accounts[i].credit(interest);
			output += "-interest: $ " + String.format("%,.2f", interest) + "\n";

			double fee = accounts[i].monthlyFee();
			accounts[i].debit(fee);
			output += "-fee: $ " + String.format("%,.2f", fee) + "\n";

			output += "-new balance: $ " + String.format("%,.2f", accounts[i].getBalance()) + "\n";
		}
		output += "--end of printing--\n";
		return output;
	}

	/**
	 * Print accounts by the last name of the holder of accounts in ascending order.
	 * @return Accounts in string format.
	 */
	public String statementByLastName() {
		String output = "";
		if (size == INITIAL_SIZE) {
			return "Database is empty.\n";
		}
		sortByLastName();
		output += "--Printing statements by last name--\n";
		for (int i = 0; i < size; i++) {
			output += accounts[i].toString() + "\n";

			double interest = Double.valueOf(accounts[i].monthlyInterest());
			accounts[i].credit(interest);
			output += "-interest: $ " + String.format("%,.2f", interest) + "\n";

			double fee = accounts[i].monthlyFee();
			accounts[i].debit(fee);
			output += "-fee: $ " + String.format("%,.2f", fee) + "\n";

			output += "-new balance: $ " + String.format("%,.2f", accounts[i].getBalance()) + "\n";
		}
		output += "--end of printing--\n";
		return output;
	}

	/**
	 * Print accounts by list.
	 * @return Accounts in string format.
	 */
	public String printAccount() {
		String output = "";
		if (size == INITIAL_SIZE) {
			return "Database is empty.\n";
		}
		output += "--Listing accounts in the database--\n";
		for (int i = 0; i < size; i++) {
			output += accounts[i].toString() + "\n";
		}
		output += "--end of listing--\n";
		return output;
	}
	
	/**
	 * Print accounts by list.
	 * @param file Imported file.
	 * @return Output string.
	 * @throws FileNotFoundException File not found.
	 */
	public String importFile(File file) throws FileNotFoundException {
		String output = "";
		Scanner sc = new Scanner(file);
		while (sc.hasNextLine()) {
			String command = sc.nextLine(); // Read one command line.
			Scanner scl = new Scanner(command);
			scl.useDelimiter(",");
			if (command.length() == EMPTY) {
				continue; // If command is empty, ignore it and read next command.
			}
			
			try {
				String accountId = scl.next();
				String accountType = scl.next();

				if (accountType.equals("C") || accountType.equals("S") || accountType.equals("M")) {
					String fname = scl.next();
					String lname = scl.next();
					double balance = scl.nextDouble();
					String dateStr = scl.next();
					Date date = Date.toDate(dateStr);
					if (date == null) {
						output += dateStr + " is not a valid date.\n";
						continue;
					}

					Account newAccount = null;
					if (accountType.equals("C")) {
						boolean directDeposit = scl.nextBoolean();
						newAccount = new Checking(new Profile(fname, lname), balance, date, directDeposit, accountId);
					} else if (accountType.equals("S")) {
						boolean isLoyal = scl.nextBoolean();
						newAccount = new Savings(new Profile(fname, lname), balance, date, isLoyal, accountId);
					} else {
						int withdrawals = scl.nextInt();
						newAccount = new MoneyMarket(new Profile(fname, lname), balance, date, withdrawals, accountId);
					}

					if (add(newAccount)) {
						output += "Account opened and added to the database.\n";
					} else {
						output += "Account is already in the database.\n";
					}
				}
			} catch (InputMismatchException e) {
				output += "Input data type mismatch.\n";
				continue;
			} catch (NoSuchElementException e) {
				output += "Insufficient parameters.\n";
				continue;
			} finally {
				scl.close();
			}
		}
		output += "Import from " + file.getName() + " complete.\n";
		sc.close();
		return output;
	}
	
	/**
	 * Export the database to file.
	 * @return Output string.
	 */
	public String exportFile() {
		String output = "";
		try {
			FileWriter fw = new FileWriter("Database.txt");
			for (int i = 0; i < size; i++) {
				Account account = accounts[i];
				fw.write(account.getAccountId() + ",");
				fw.write(account.getClass().getSimpleName().charAt(0) + ",");
				fw.write(account.getHolder().getFname() + ",");
				fw.write(account.getHolder().getLname() + ",");
				fw.write(String.format("%.2f", account.getBalance()) + ",");
				fw.write(account.getDateOpen() + ",");
				
				if(account.getClass().getSimpleName().equals("Checking")) {
					fw.write(((Checking) account).getDirectDeposit() + "\n");
				} else if(account.getClass().getSimpleName().equals("Savings")) {
					fw.write(((Savings) account).getIsLoyal() + "\n");
				} else {
					fw.write(((MoneyMarket) account).getWithdrawals() + "\n");
				}
				
			}
			output += "Export to Database.txt complete.\n";
			fw.close();
		} catch (IOException e) {
			output += "Export to Database.txt failed.\n";
		}
		return output;
	}

}
