package br.com.kennycode.gaikujin.test;

import br.com.kennycode.gaikujin.dao.AccountDAO;
import br.com.kennycode.gaikujin.dao.ClientDAO;
import br.com.kennycode.gaikujin.model.Account;
import br.com.kennycode.gaikujin.model.Client;
import br.com.kennycode.gaikujin.util.JpaManager;

public class AccountClientTest {
	public static void main(String[] args) {

		Client c1 = new Client();
		c1.setName("Kenny");
		c1.setAdress("T9997");
		c1.setJob("Developer Java Junior");
		
		Client c2 = new Client();
		c2.setName("Milkwalk");
		c2.setAdress("T9996");
		c2.setJob("Developer Java PLeno");

		Account a1 = new Account();
		a1.setName("Test");
		
		c1.setAccount(a1);
		
		ClientDAO clientDao = new ClientDAO(JpaManager.getConnection());
		AccountDAO accountDao = new AccountDAO(JpaManager.getConnection());
		
		accountDao.create(a1);
		c1.setAccount(a1);
		clientDao.create(c1);

		System.out.println(c1);
	}
}
