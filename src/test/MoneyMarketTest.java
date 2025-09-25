import application.Date;
import application.MoneyMarket;
import application.Profile;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * MoneyMarketTest Class
 * This class tests the MoneyMarket class.
 */
class MoneyMarketTest {

	/**
	 * Test method for {@link MoneyMarket#monthlyInterest()}.
	 */
	@Test
	void testMonthlyInterest() {
		System.out.println("Testing monthlyInterest() for MoneyMarket");
		Profile holder = new Profile("Lixue", "Chen");
		Date date = new Date(2007, 4, 21);
		
		MoneyMarket account = new MoneyMarket(holder, 999.99, date, 1);
		assertEquals(0.5411, account.monthlyInterest(), 0.001);
		account = new MoneyMarket(holder, 2500, date, 12);
		assertEquals(1.35417, account.monthlyInterest(), 0.001);
		account = new MoneyMarket(holder, 19999.99, date, 36);
		assertEquals(10.8333, account.monthlyInterest(), 0.001);
		account = new MoneyMarket(holder, 1899.5, date, 4);
		assertEquals(1.028896, account.monthlyInterest(), 0.001);
		account = new MoneyMarket(holder, 199, date, 60);
		assertEquals(0.10779, account.monthlyInterest(), 0.001);
		
		System.out.println("Complete");
	}

	/**
	 * Test method for {@link MoneyMarket#monthlyFee()}.
	 */
	@Test
	void testMonthlyFee() {
		System.out.println("Testing monthlyFee() for MoneyMarket");
		Profile holder = new Profile("Maggie", "Chen");
		Date date = new Date(1999, 4, 21);
		
		MoneyMarket account = new MoneyMarket(holder, 999.99, date, 1); // Test min balance.
		assertEquals(12, account.monthlyFee(), 0);
		account = new MoneyMarket(holder, 999.99, date, 6); // Test min balance.
		assertEquals(12, account.monthlyFee(), 0);
		account = new MoneyMarket(holder, 2500, date, 2); // Test boundary.
		assertEquals(0, account.monthlyFee(), 0);
		account = new MoneyMarket(holder, 2500, date, 12); // Test boundary with excess withdrawals.
		assertEquals(12, account.monthlyFee(), 0);
		account = new MoneyMarket(holder, 2500, date, 7); // Test boundaries.
		assertEquals(12, account.monthlyFee(), 0);
		account = new MoneyMarket(holder, 2500, date, 6); // Test boundaries.
		assertEquals(0, account.monthlyFee(), 0);
		account = new MoneyMarket(holder, 19999.99, date, 3);
		assertEquals(0, account.monthlyFee(), 0);
		account = new MoneyMarket(holder, 99999.99, date, 36); // Test excess withdrawals.
		assertEquals(12, account.monthlyFee(), 0);
		account = new MoneyMarket(holder, 1899.5, date, 4); // Test min balance.
		assertEquals(12, account.monthlyFee(), 0);
		account = new MoneyMarket(holder, 199, date, 60); // Test min balance with excess withdrawals.
		assertEquals(12, account.monthlyFee(), 0);
		
		System.out.println("Complete");
	}

}
