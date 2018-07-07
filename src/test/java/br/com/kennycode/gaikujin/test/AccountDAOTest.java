package br.com.kennycode.gaikujin.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import br.com.kennycode.gaikujin.dao.AccountDAO;
import br.com.kennycode.gaikujin.model.Account;
import br.com.kennycode.gaikujin.util.JpaManager;


public class AccountDAOTest {
	
	@Test
	public void testCreateAccount() {
		
		// account (transient)
		Account account = new Account();
		account.setName("Kenny");
		account.setBank("NSA TOU");
		account.setAgency("9997");
		account.setNumber("7777");

		// create account
		AccountDAO dao = new AccountDAO(JpaManager.getConnection());
		dao.create(account);
		
		assertNull(account.getId());
	}
}
