package br.com.kennycode.gaikujin.dao;

import static org.junit.Assert.assertEquals;

import javax.persistence.EntityManager;

import org.junit.Before;
import org.junit.Test;

import br.com.kennycode.gaikujin.model.Account;
import br.com.kennycode.gaikujin.util.JpaManager;


public class AccountDAOTest {

    private EntityManager connection;

    @Before
    public void init() {
    	connection = JpaManager.getConnection("gaikujin");
    }

	@Test
	public void testCreateOneAccount() {
		// account (transient)
		Account account = new Account();
		account.setName("Kenny");
		account.setBank("NSA TOU");
		account.setAgency("9997");
		account.setNumber("7777");

		// create account
		AccountDAO dao = new AccountDAO(JpaManager.getConnection());
		dao.create(account);
		
		assertEquals(new Integer(1), account.getId());
	}
}
