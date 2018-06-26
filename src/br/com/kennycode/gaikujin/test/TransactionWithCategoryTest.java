package br.com.kennycode.gaikujin.test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Random;

import javax.persistence.EntityManager;

import br.com.kennycode.gaikujin.model.Category;
import br.com.kennycode.gaikujin.model.Transaction;
import br.com.kennycode.gaikujin.model.TypeTransaction;
import br.com.kennycode.gaikujin.util.GenerateCategories;
import br.com.kennycode.gaikujin.util.JpaManager;

public class TransactionWithCategoryTest {

	public static void main(String[] args) {

		GenerateCategories.initialize();
		
		
		
		EntityManager em = JpaManager.getConnection();
		em.getTransaction().begin();
		
		Random rand = new Random();
		int max = em.createQuery("select c from Category c", Category.class).getResultList().size();
		int id = rand.nextInt(max)+1;
		
		Category c1 = em.find(Category.class, id);
		id = rand.nextInt(max)+1;
		Category c2 = em.find(Category.class, id);
		id = rand.nextInt(max)+1;
		Category c3 = em.find(Category.class, id);
		id = rand.nextInt(max)+1;
		Category c4 = em.find(Category.class, id);
		em.getTransaction().commit();
		em.close();

		// t1
		Transaction t1 = new Transaction();
		t1.setDate(Calendar.getInstance());
		t1.setDescription("buy a new car.");
		t1.setType(TypeTransaction.PURCHASES);
		t1.setValue(new BigDecimal("300.0"));
		t1.setCategories(Arrays.asList(c1,c2));
		// t2
		Transaction t2 = new Transaction();
		t2.setDate(Calendar.getInstance());
		t2.setDescription("sold my car.");
		t2.setType(TypeTransaction.SALES);
		t2.setValue(new BigDecimal("700.0"));
		t2.setCategories(Arrays.asList(c3,c4));
		// t3
		Transaction t3 = new Transaction();
		t3.setDate(Calendar.getInstance());
		t3.setDescription("My payments");
		t3.setType(TypeTransaction.RECEIPTS);
		t3.setValue(new BigDecimal("71400.0"));
		t3.setCategories(Arrays.asList(c1,c4,c3));
		

		em = JpaManager.getConnection();
		em.getTransaction().begin();

		em.persist(t1);
		em.persist(t2);
		em.persist(t3);
		
		em.getTransaction().commit();
		em.close();
		
	}
}
