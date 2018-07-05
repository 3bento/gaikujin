package br.com.kennycode.gaikujin.test;

import javax.persistence.EntityManager;

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
		//c2.setAccount(a1);
		
		EntityManager em = JpaManager.getConnection();
		em.getTransaction().begin();
		em.persist(a1);
		em.persist(c1);
		//em.persist(c2);
		
		em.getTransaction().commit();
		em.close();
		
	}
}
