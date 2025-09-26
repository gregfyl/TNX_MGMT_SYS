package application;

import java.util.UUID;
/**
 * Savings Class
 * This class defines the Savings account.
 */

public class Savings extends Account {

	/** Loyal customer information of the account. */
	private boolean isLoyal;
	/** To avoid magic number. */
	private static final double APR = 0.0025;
	/** To avoid magic number. */
	private static final double LOYAL_APR = 0.0035;
	/** To avoid magic number. */
	private static final int MONTH = 12;
	/** To avoid magic number. */
	private static final int MIN_BALANCE = 300;
	/** To avoid magic number. */
	private static final int FEE = 5;
	/** To avoid magic number. */
	private static final int WAIVED_FEE = 0;

	/**
	 * Initialize the account by holder, balance, dateOpen, and isLoyal.
	 * @param holder   Holder of the account.
	 * @param balance  Balance of the account.
	 * @param dateOpen Date of the account opened.
	 * @param isLoyal  Loyal customer information of the account.
	 */
	public Savings(Profile holder, double balance, Date dateOpen, boolean isLoyal) {
		super(holder, balance, dateOpen);
		this.isLoyal = isLoyal;
	}

	/**
	 * 2nd constructor Initialize the account by holder, balance, dateOpen, isLoyal and uuid.
	 * @param holder   Holder of the account.
	 * @param balance  Balance of the account.
	 * @param dateOpen Date of the account opened.
	 * @param isLoyal  Loyal customer information of the account.
	 * @param uuid Account unique identifier.
	 */
	public Savings(Profile holder, double balance, Date dateOpen, boolean isLoyal, UUID uuid) {
		super(holder, balance, dateOpen, uuid);
		this.isLoyal = isLoyal;
	}

	/**
	 * Calculate the monthly interest of the account.
	 * @return Monthly interest of the account.
	 */
	@Override
	public double monthlyInterest() {
		if (isLoyal)
			return getBalance() * LOYAL_APR / MONTH;
		return getBalance() * APR / MONTH;
	}

	/**
	 * Calculate the monthly fee of the account.
	 * @return Monthly fee of the account.
	 */
	@Override
	public double monthlyFee() {
		if (getBalance() >= MIN_BALANCE) {
			return WAIVED_FEE;
		}
		return FEE;
	}

	/**
	 * Convert the account to a string.
	 * @return The account in string format.
	 */
	@Override
	public String toString() {
		String output = "*Savings*" + super.toString();
		if (isLoyal) {
			output += "*special Savings account*";
		}
		return output;
	}

	/**
	 * Get the loyal customer information of the account.
	 * @return Loyal customer information of the account.
	 */
	public boolean getIsLoyal() {
		return isLoyal;
	}

}
