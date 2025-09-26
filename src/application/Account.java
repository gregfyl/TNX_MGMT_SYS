package application;
import java.util.UUID;

/**
 * Account Class
 * This is an abstract class that defines the common features of all account types.
 */

public abstract class Account {

	/** Holder of the account. */
	private Profile holder;
	/** Balance of the account. */
	private double balance;
	/** Date of the account opened. */
	private Date dateOpen;
	/** Account unique identifier. */
	private String accountId;

	/**
	 * Initialize the account by holder, balance, and dateOpen.
	 * @param holder   Holder of the account.
	 * @param balance  Balance of the account.
	 * @param dateOpen Date of the account opened.
	 * @param accountId Account unique identifier.
	 */
	public Account(Profile holder, double balance, Date dateOpen, String accountId) {
		this.holder = holder;
		this.balance = balance;
		this.dateOpen = dateOpen;
		this.accountId = accountId;
	}

	/**
	 * Initialize the account by holder, balance, and dateOpen.
	 * @param holder   Holder of the account.
	 * @param balance  Balance of the account.
	 * @param dateOpen Date of the account opened.
	 */
	public Account(Profile holder, double balance, Date dateOpen) {
		this.holder = holder;
		this.balance = balance;
		this.dateOpen = dateOpen;
		this.accountId = String.valueOf(UUID.randomUUID());
	}

	/**
	 * Decrease the balance by amount.
	 * @param amount Amount to be decreased.
	 */
	public void debit(double amount) {
		balance -= amount;
	}

	/**
	 * Increase the balance by amount.
	 * @param amount Amount to be increase.
	 */
	public void credit(double amount) {
		balance += amount;
	}

	/**
	 * Convert the account to a string.
	 * @return The account in string format.
	 */
	@Override
	public String toString() {
		return accountId + "*" + this.getClass().getSimpleName() + "*" + holder.toString() + "* $" + String.format("%,.2f", balance) + "*" + dateOpen;
	}

	/**
	 * Calculate the monthly interest of the account.
	 * @return Monthly interest of the account.
	 */
	public abstract double monthlyInterest();

	/**
	 * Calculate the monthly fee of the account.
	 * @return Monthly fee of the account.
	 */
	public abstract double monthlyFee();

	/**
	 * Determine if the account matches with another account.
	 * @param account Another account that is to compared with.
	 * @return True if the account with another account.<br>
	 *         False otherwise.
	 */
	@Override
	public boolean equals(Object account) {
		if (account == null)
			return false;
		if (this.getClass().toString().equals(account.getClass().toString())) {
			if (this.getHolder().toString().equals(((Account) account).getHolder().toString())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Get the holder of the account.
	 * @return Holder of the account.
	 */
	public Profile getHolder() {
		return holder;
	}

	/**
	 * Get the balance of the account.
	 * @return Balance of the account.
	 */
	public double getBalance() {
		return balance;
	}

	/**
	 * Get the date of the account opened.
	 * @return Date of the account opened.
	 */
	public Date getDateOpen() {
		return dateOpen;
	}

	/**
	 * Get the account unique identifier.
	 * @return Account unique identifier.
	 */
	public String getAccountId() {
		return accountId;
	}

}
