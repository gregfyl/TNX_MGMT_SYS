package application;

/**
 * Date Class
 * This class defines the date.
 */

public class Date implements Comparable<Date> {

	/** Year of the date. */
	private int year;
	/** Month of the date. */
	private int month;
	/** Day of the date. */
	private int day;
	/** To avoid magic number. */
	private static final int REMAINDER = 0;
	/** To avoid magic number. */
	private static final int MIN_DAY_MONTH = 1;
	/** To avoid magic number. */
	private static final int MAX_MONTH = 12;
	/** To avoid magic number. */
	private static final int MAX_BIG_MONTH_DAY = 31;
	/** To avoid magic number. */
	private static final int MAX_SMALL_MONTH_DAY = 30;
	/** To avoid magic number. */
	private static final int MAX_LEAP_MONTH_DAY = 29;
	/** To avoid magic number. */
	private static final int LEAP_MONTH = 2;
	/** To avoid magic numbers. */
	private static final int[] SMALL_MONTHS = { 4, 6, 9, 11 };
	/** To avoid magic number. */
	private static final int MIN_YEAR = 1900;
	/** To avoid magic number. */
	private static final int MAX_YEAR = 3200;
	/** To avoid magic number. */
	private static final int LEAP_YEAR = 4;
	/** To avoid magic number. */
	private static final int CENTURY_YEAR = 100;
	/** To avoid magic number. */
	private static final int LEAP_CENTURY_YEAR = 400;
	/** To avoid magic number. */
	private static final int SPECIAL_LEAP_YEAR = 3200;
	/** To avoid magic number. */
	private static final int START = 0;
	/** To avoid magic number. */
	private static final int NEXT = 1;

	/**
	 * Initialize the date by year, month, and day.
	 * @param year  Year of the date.
	 * @param month Month of the date.
	 * @param day   Day of the date.
	 */
	public Date(int year, int month, int day) {
		this.year = year;
		this.month = month;
		this.day = day;
	}

	/**
	 * Compare the date with another date.
	 * @param date Another date that is to compared with.
	 * @return 1 if the date is more recent than another date.<br>
	 *         0 if the date matches with another date.<br>
	 *         -1 if the date is more vintage than another date.
	 */
	@Override
	public int compareTo(Date date) {
		if (year > date.year) {
			return 1;
		} else if (year < date.year) {
			return -1;
		} else {
			if (month > date.month) {
				return 1;
			} else if (month < date.month) {
				return -1;
			} else {
				if (day > date.day) {
					return 1;
				} else if (day < date.day) {
					return -1;
				} else {
					return 0;
				}
			}
		}
	}

	/**
	 * Convert the date to a string.
	 * @return The date in string format.
	 */
	@Override
	public String toString() {
		return month + "/" + day + "/" + year;
	}

	/**
	 * Check if the date is valid.
	 * @return True if the date is valid.<br>
	 *         False otherwise.
	 */
	public boolean isValid() {
		if (year >= MIN_YEAR && year <= MAX_YEAR && month >= MIN_DAY_MONTH && month <= MAX_MONTH
				&& day >= MIN_DAY_MONTH) {
			boolean bigMonth = true;
			for (int i = 0; i < SMALL_MONTHS.length; i++) {
				if (month == SMALL_MONTHS[i]) {
					bigMonth = false;
					if (day <= MAX_SMALL_MONTH_DAY)
						return true;
				}
			}
			if (month == LEAP_MONTH) {
				if (year % SPECIAL_LEAP_YEAR == REMAINDER) {
					if (day < MAX_LEAP_MONTH_DAY)
						return true;
				} else if (year % CENTURY_YEAR == REMAINDER) {
					if (year % LEAP_CENTURY_YEAR == REMAINDER && day <= MAX_LEAP_MONTH_DAY)
						return true;
					if (year % LEAP_CENTURY_YEAR != REMAINDER && day < MAX_LEAP_MONTH_DAY)
						return true;
				} else {
					if (day < MAX_LEAP_MONTH_DAY || (year % LEAP_YEAR == REMAINDER && day <= MAX_LEAP_MONTH_DAY))
						return true;
				}
			} else {
				if (bigMonth && day <= MAX_BIG_MONTH_DAY)
					return true;
			}
		}
		return false;
	}
	
	/**
	 * Determine if a string is an integer.
	 * @param str String to be determined.
	 * @return True if the string is an integer.<br>
	 *         False otherwise.
	 */
	private static boolean isInt(String str) {
		if (str == null || str.equals(""))
			return false;
		for (int i = 0; i < str.length(); i++) {
			if ((int) str.charAt(i) < (int) '0' || (int) str.charAt(i) > (int) '9')
				return false;
		}
		return true;
	}

	/**
	 * Translate a string to a Date.
	 * @param dateStr Date in format mm/dd/yyyy.
	 * @return The date in Date format as described in dateStr.
	 */
	public static Date toDate(String dateStr) {
		if (dateStr == null || dateStr.equals(""))
			return null;

		int index = dateStr.indexOf("/");
		if (index < 1)
			return null;
		String monthStr = dateStr.substring(START, index);
		dateStr = dateStr.substring(index + NEXT);

		index = dateStr.indexOf("/");
		if (index < 1)
			return null;
		String dayStr = dateStr.substring(START, index);
		dateStr = dateStr.substring(index + NEXT);

		if (!isInt(monthStr) || !isInt(dayStr) || !isInt(dateStr))
			return null;
		Date date = new Date(Integer.valueOf(dateStr), Integer.valueOf(monthStr), Integer.valueOf(dayStr));
		if (!date.isValid())
			return null;
		return date;
	}
	
}
