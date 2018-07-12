package br.com.kennycode.gaikujin.model;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class AccountTest {

	private Account account = new Account();
	private static final Integer ID = new Integer(1);
	private static final String NAME = "kenny";
	private static final String NUMBER = "0123456789";
	private static final String BANK = "BANK SA";
	private static final String AGENCY = "123-7654321";

	@Before
	public void setUp() {
		account.setId(ID);
		account.setName(NAME);
		account.setNumber(NUMBER);
		account.setBank(BANK);
		account.setAgency(AGENCY);

		
	}

	@Test
	public void setId() {
		assertEquals(account.getId(), new Integer(1));
	}

	@Test
	public void getName() {
		assertEquals(account.getName(), NAME);
	}

	@Test
	public void getNumber() {
		assertEquals(account.getNumber(), NUMBER);
	}

	@Test
	public void getBank() {
		assertEquals(account.getBank(), BANK);
	}

	@Test
	public void getAgency() {
		assertEquals(account.getAgency(), AGENCY);
	}

	@Test
	public void getTransactions() {
		assertEquals(account.getTransactions(), null);
	}
	
	@Test
	public void toStringResult() {
		String out = "Account [id=" + ID + ", name=" + NAME + ", number=" + NUMBER + ", bank=" + BANK + ", agency="
				+ AGENCY + "]";
		assertEquals(account.toString(), out);
	}
}