package br.com.kennycode.gaikujin.test;

import javax.persistence.EntityManager;

import br.com.kennycode.gaikujin.model.Account;
import br.com.kennycode.gaikujin.util.JpaManager;

public class UpdateAccountTest {
	
	// Transient > persist() > Managed > em.close() > Detached
	// Detached > merge() > Managed > em.close() > Detached
	// Managed > remove > Removed > em.close() > Detached
	
	public static void main(String[] args) {
		EntityManager em = JpaManager.getConnection();
		em.getTransaction().begin();
		// account (detached)
		Account account = new Account();
		account.setId(1);
		account.setName("CCCC");
		account.setBank("XXXX");
		account.setAgency("99999");
		account.setNumber("44444");
		// account (managed)
		em.merge(account);
		em.getTransaction().commit();
		em.close();
	}
}
