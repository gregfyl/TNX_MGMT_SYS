package application;
import java.util.Date;


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
		this.date = null;
	}
	// ===== Getters =====
    public String getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public Date getDate() {
        return date;
    }

    public String getNote() {
        return note;
    }

    // ===== Setters =====
    public void setType(String type) {
        this.type = type;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setNote(String note) {
        this.note = note;
    }

    // ===== Example modification: formatted summary =====
    @Override
    public String toString() {
        return "Transaction[type=" + type + ", amount=" + amount + ", date=" + date + ", note=" + note + "]";
    }

    // Optional helper to print details
    public void printTransaction() {
        System.out.println(this.toString());
    }

}
