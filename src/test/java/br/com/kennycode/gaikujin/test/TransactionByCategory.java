package br.com.kennycode.gaikujin.test;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.kennycode.gaikujin.model.Category;
import br.com.kennycode.gaikujin.model.Transaction;
import br.com.kennycode.gaikujin.util.JpaManager;

public class TransactionByCategory {

	public static void main(String[] args) {
		// JPQL to get some t ransactions
		String search = "select t from Transaction t join t.categories c where c = :pCategory";
		
		Category category = new Category();
		category.setId(1);
		
		EntityManager em = JpaManager.getConnection();
		em.getTransaction().begin();
		Query query = em.createQuery(search);
		query.setParameter("pCategory", category);
		
		List<Transaction> transactions = query.getResultList();
		
		for(Transaction t: transactions) {
			System.out.println(t.toString());
		}
		
		em.getTransaction().commit();
		em.close();
	}

}
