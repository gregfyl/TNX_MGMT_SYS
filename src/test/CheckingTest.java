package test;

import application.Checking;
import application.Date;
import application.Profile;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * CheckingTest Class
 * This class tests the Checking class.
 */
class CheckingTest {

	/**
	 * Test method for {@link Checking#monthlyInterest()}.
	 */
	@Test
	void testMonthlyInterest() {
		System.out.println("Testing monthlyInterest() for Checking");
		Profile holder = new Profile("Lixue", "Chen");
		Date date = new Date(2007, 4, 21);

		Checking account = new Checking(holder, 999.99, date, true);   
		assertEquals(0.04167, account.monthlyInterest(), 0.001);
		account = new Checking(holder, 1500, date, true);   
		assertEquals(0.0625, account.monthlyInterest(), 0.001);
		account = new Checking(holder, 19999.99, date, false);   
		assertEquals(0.8333, account.monthlyInterest(), 0.001);
		account = new Checking(holder, 1899.5, date, false);   
		assertEquals(0.07915, account.monthlyInterest(), 0.001);
		account = new Checking(holder, 199, date, true);   
		assertEquals(0.008, account.monthlyInterest(), 0.001);
		
		System.out.println("Complete");
	}

	/**
	 * Test method for {@link Checking#monthlyFee()}.
	 */
	@Test
	void testMonthlyFee() {
		System.out.println("Testing monthlyFee() for Checking");
		Profile holder = new Profile("Maggie", "Chen");
		Date date = new Date(1999, 4, 21);
		
		Checking account = new Checking(holder, 999.99, date, false); // Test min balance.
		assertEquals(25, account.monthlyFee(), 0);
		account = new Checking(holder, 1500, date, true); // Test boundary with direct deposit.
		assertEquals(0, account.monthlyFee(), 0);
		account = new Checking(holder, 1500, date, false); // Test boundary.
		assertEquals(0, account.monthlyFee(), 0);
		account = new Checking(holder, 19999.99, date, false);
		assertEquals(0, account.monthlyFee(), 0);
		account = new Checking(holder, 1899.5, date, true);
		assertEquals(0, account.monthlyFee(), 0);
		account = new Checking(holder, 199, date, true); // Test direct deposit.
		assertEquals(0, account.monthlyFee(), 0);
		account = new Checking(holder, 199, date, false); // Test min balance.
		assertEquals(25, account.monthlyFee(), 0);
		
		System.out.println("Complete");
	}

}
