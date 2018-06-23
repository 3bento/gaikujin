package br.com.kennycode.gaikujin.test;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.EntityManager;

import br.com.kennycode.gaikujin.model.Account;
import br.com.kennycode.gaikujin.model.Transaction;
import br.com.kennycode.gaikujin.model.TypeTransaction;
import br.com.kennycode.gaikujin.util.JpaManager;

public class RelationshipTransactionAndAccountTest {

	public static void main(String[] args) {

		Account account= new Account();
		
		Transaction transaction = new Transaction();
		transaction.setDate(Calendar.getInstance());
		transaction.setDescription("Wallmart");
		transaction.setType(TypeTransaction.PURCHASES);
		transaction.setValue(new BigDecimal("250.0"));
		
		EntityManager em = JpaManager.getConnection();
		em.getTransaction().begin();
		// get account
		account = em.find(Account.class, 6);
		// set in transaction
		transaction.setAccount(account);
		// transaction  (managed)
		em.persist(transaction);
		
		em.getTransaction().commit();
		em.close();
		
	}
	
}
