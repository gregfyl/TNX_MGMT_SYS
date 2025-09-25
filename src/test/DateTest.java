package test;

import application.Date;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * DateTest Class
 * This class tests the Date class.
 */
class DateTest {

	/**
	 * Test method for {@link Date#isValid()}.
	 */
	@Test
	void testIsValid() {
		System.out.println("Testing isValid() for Date");
		
		Date date = new Date(1996, 2, 29); // Test leap month.
		assertTrue(date.isValid());
		date = new Date(2008, 2, 29); // Test leap month.
		assertTrue(date.isValid());
		date = new Date(2008, 02, 29); // Test leap month and format.
		assertTrue(date.isValid());
		date = new Date(2000, 2, 29); // Test leap month.
		assertTrue(date.isValid());
		date = new Date(2020, 01, 01); // Test format.
		assertTrue(date.isValid());
		date = new Date(2020, 1, 1);
		assertTrue(date.isValid());
		date = new Date(2020, 11, 1);
		assertTrue(date.isValid());		
		date = new Date(2020, 12, 31);
		assertTrue(date.isValid());
		
		date = new Date(2010, 2, 29); // Test leap month.
		assertFalse(date.isValid());
		date = new Date(2010, 4, 31); // Test max day.
		assertFalse(date.isValid());	
		date = new Date(2010, 4, 0); // Test min day.
		assertFalse(date.isValid());	
		date = new Date(2010, 15, 31); // Test max month.
		assertFalse(date.isValid());	
		date = new Date(2010, 0, 31); // Test min month.
		assertFalse(date.isValid());
		date = new Date(0, 5, 20); // Test min year.
		assertFalse(date.isValid());
		date = new Date(2010, 15, 40); // Test max month and day.
		assertFalse(date.isValid());
		date = new Date(12, 31, 2020); // Test invalid format.
		assertFalse(date.isValid());
		date = new Date(31, 12, 2020); // Test invalid format.
		assertFalse(date.isValid());
		date = new Date(2010, 6, 32); // Test max day.
		assertFalse(date.isValid());
		date = new Date(2100, 2, 29); // Test leap month.
		assertFalse(date.isValid());
		date = new Date(3200, 2, 29); // Test leap month.
		assertFalse(date.isValid());


		date = new Date(1899, 2, 28); // Test min year.
		assertFalse(date.isValid());
		date = new Date(1900, 2, 28); // Test min year.
		assertTrue(date.isValid());
		date = new Date(3201, 2, 28); // Test max year.
		assertFalse(date.isValid());
		date = new Date(3200, 2, 28); // Test max year.
		assertTrue(date.isValid());

		System.out.println("Complete");
	}

}
