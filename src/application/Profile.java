package application;

/**
 * Profile Class
 * This class defines the profile of an account holder.
 */

public class Profile {

	/** First name of the account holder. */
	private String fname;
	/** Last name of the account holder. */
	private String lname;

	/**
	 * Initialize the account by fname and lname.
	 * @param fname First name of the account holder.
	 * @param lname Last name of the account holder.
	 */
	public Profile(String fname, String lname) {
		this.fname = fname;
		this.lname = lname;
	}

	/**
	 * Get the first name of the account holder.
	 * @return First Name of the account holder.
	 */
	public String getFname() {
		return fname;
	}

	/**
	 * Get the last name of the account holder.
	 * @return Last Name of the account holder.
	 */
	public String getLname() {
		return lname;
	}

	/**
	 * Convert the name of the account holder to a string.
	 * @return The name of the account holder in string format.
	 */
	@Override
	public String toString() {
		return fname + " " + lname;
	}

}