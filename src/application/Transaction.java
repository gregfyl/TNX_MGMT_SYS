package application;
//import java.util.Date;

/**
 * Account Class
 * This is an abstract class that defines the common features of all account types.
 */

public class Transaction {

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

	/**
	 * Initialize the account by holder, balance, and dateOpen.
	 * @param type  	Transaction type.
	 * @param amount  	Transaction amount.
	 * @param note 		Transaction remarks.
	 */
	public Transaction(String type, double amount, String note) {
		this.type = type;
		this.amount = amount;
		this.note = note;

		java.time.LocalDate today = java.time.LocalDate.now();
		int year = today.getYear();
		int month = today.getMonthValue();
		int day = today.getDayOfMonth();
		this.date = new Date(year, month, day);
	}


	/**
	 * Convert the transaction to a string.
	 * @return The transaction in string format.
	 */
	@Override
	public String toString() {
		return date + ", " + type + " $" + String.format("%,.2f", amount) + ", Note: " + note;
	}

	// getters and setters
	public String getType() { return type; }
	public void setType(String type) { this.type = type; }

	public double getAmount() { return amount; }
	public void setAmount(double amount) { this.amount = amount; }

	public Date getDate() { return date; }
	public void setDate(Date date) { this.date = date; }

	public String getNote() { return note; }
	public void setNote(String note) { this.note = note; }

}
