package application;

/**
 * Checking Class
 * This class defines the Checking account.
 */

public class Checking extends Account {

	/** Direct deposit information of the account. */
	private boolean directDeposit;
	/** To avoid magic number. */
	private static final double APR = 0.0005;
	/** To avoid magic number. */
	private static final int MONTH = 12;
	/** To avoid magic number. */
	private static final int MIN_BALANCE = 1500;
	/** To avoid magic number. */
	private static final int FEE = 25;
	/** To avoid magic number. */
	private static final int WAIVED_FEE = 0;

	/**
	 * Initialize the account by holder, balance, dateOpen, and directDeposit.
	 * @param holder        Holder of the account.
	 * @param balance       Balance of the account.
	 * @param dateOpen      Date of the account opened.
	 * @param directDeposit Direct deposit information of the account.
	 */
	public Checking(Profile holder, double balance, Date dateOpen, boolean directDeposit) {
		super(holder, balance, dateOpen);
		this.directDeposit = directDeposit;
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
		if (directDeposit || getBalance() >= MIN_BALANCE) {
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
		String output = "*Checking*" + super.toString();
		if (directDeposit) {
			output += "*direct deposit account*";
		}
		return output;
	}

	/**
	 * Get the direct deposit information of the account.
	 * @return Direct deposit information of the account.
	 */
	public boolean getDirectDeposit() {
		return directDeposit;
	}

}
