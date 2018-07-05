package br.com.kennycode.gaikujin.test;

import javax.persistence.EntityManager;

import br.com.kennycode.gaikujin.model.Account;
import br.com.kennycode.gaikujin.util.JpaManager;

public class DeleteAccountTest {

	public static void main(String[] args) {
		EntityManager em = JpaManager.getConnection();
		em.getTransaction().begin();

		// account (managed)
		Account account = em.find(Account.class, 1);
		// account (removed) -> object must be managed to be removed!
		em.remove(account);
		
		em.getTransaction().commit();
		em.close();
	}
	
}
