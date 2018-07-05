package br.com.kennycode.gaikujin.test;

import javax.persistence.EntityManager;

import br.com.kennycode.gaikujin.model.Account;
import br.com.kennycode.gaikujin.util.JpaManager;

public class CreateAccountTest {
	public static void main(String[] args) {
		
		// account (transient)
		Account account = new Account();
		account.setName("Kenny");
		account.setBank("NSA TOU");
		account.setAgency("9997");
		account.setNumber("7777");

		EntityManager em = JpaManager.getConnection();
		em.getTransaction().begin();
		
		// account (managed)
		em.persist(account);
		em.getTransaction().commit();

		// account (detached)
		em.close();
	}
}
