package application;

import java.util.UUID;
/**
 * MoneyMarket Class
 * This class defines the Money Market account.
 */

public class MoneyMarket extends Account {

	/** Number of withdrawal(s) of the account. */
	private int withdrawals;
	/** To avoid magic number. */
	private static final double APR = 0.0065;
	/** To avoid magic number. */
	private static final int MONTH = 12;
	/** To avoid magic number. */
	private static final int MIN_BALANCE = 2500;
	/** To avoid magic number. */
	private static final int MAX_WITHDRAWALS = 7;
	/** To avoid magic number. */
	private static final int FEE = 12;
	/** To avoid magic number. */
	private static final int WAIVED_FEE = 0;
	/** To avoid magic number. */
	private static final int ONE_WITHDRAWAL = 1;

	/**
	 * Initialize the account by holder, balance, dateOpen, and withdrawals.
	 * @param holder      Holder of the account.
	 * @param balance     Balance of the account.
	 * @param dateOpen    Date of the account opened.
	 * @param withdrawals Number of withdrawal(s) of the account.
	 */
	public MoneyMarket(Profile holder, double balance, Date dateOpen, int withdrawals) {
		super(holder, balance, dateOpen);
		this.withdrawals = withdrawals;
	}


	/**
	 * 2nd constructor Initialize the account by holder, balance, dateOpen, withdrawals and uuid.
	 * @param holder      Holder of the account.
	 * @param balance     Balance of the account.
	 * @param dateOpen    Date of the account opened.
	 * @param withdrawals Number of withdrawal(s) of the account.
	 * @param uuid Account unique identifier.
	 */
	public MoneyMarket(Profile holder, double balance, Date dateOpen, int withdrawals, UUID uuid) {
		super(holder, balance, dateOpen, uuid);
		this.withdrawals = withdrawals;
	}

	/**
	 * Calculate the monthly interest of the account.
	 * @return Monthly interest of the account.
	 */
	@Override
	public double monthlyInterest() {
		return getBalance() * APR / MONTH;
	}

	/**
	 * Calculate the monthly fee of the account.
	 * @return Monthly fee of the account.
	 */
	@Override
	public double monthlyFee() {
		if (getBalance() >= MIN_BALANCE && withdrawals < MAX_WITHDRAWALS) {
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
		String output = "*Money Market*" + super.toString();
		if (withdrawals == ONE_WITHDRAWAL) {
			output += "*" + withdrawals + " withdrawal*";
		} else {
			output += "*" + withdrawals + " withdrawals*";
		}
		return output;
	}

	/**
	 * Increase the number of withdrawals of the account.
	 */
	public void increaseWithdrawals() {
		withdrawals++;
	}

	/**
	 * Get the number of withdrawals of the account.
	 * @return Number of withdrawals of the account.
	 */
	public int getWithdrawals() {
		return withdrawals;
	}

}
