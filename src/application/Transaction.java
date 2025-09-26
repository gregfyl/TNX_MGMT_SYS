package application;
import java.util.Date;

/**
 * Account Class
 * This is an abstract class that defines the common features of all account types.
 */

public abstract class Transaction {

	/** Transaction type, e.g. "deposit", "withdraw". */
	private String type;
	/** Transaction amount. */
	private double amount;
	/** Transaction date. */
	private Date date;
	/** Remarks. */
	private String note;

	/**
	 * Initialize the account by holder, balance, and dateOpen.
	 * @param type  	Transaction type.
	 * @param amount  	Transaction amount.
	 * @param date 		Transaction date.
	 * @param note 		Transaction remarks.
	 */
	public Transaction(String type, double amount, Date date, String note) {
		this.type = type;
		this.amount = amount;
		this.note = note;
		this.date = date;
	}

}
