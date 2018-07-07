package br.com.kennycode.gaikujin.dao;

import javax.persistence.EntityManager;

import br.com.kennycode.gaikujin.model.Account;
import br.com.kennycode.gaikujin.util.JpaManager;

public class SearchAccountTest {

	public static void main(String[] args) {
		
		
		EntityManager em =JpaManager.getConnection();
		em.getTransaction().begin();
		
		// account(detached) > account (managed)
		Account account = em.find(Account.class, 1);
		account.setName("The Frozen from UNI9");
		account.setNumber("24");
		em.getTransaction().commit();
		
		// account (detached)
		em.close();
	}

}
