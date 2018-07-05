package br.com.kennycode.gaikujin.test;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.kennycode.gaikujin.model.Account;
import br.com.kennycode.gaikujin.model.TypeTransaction;
import br.com.kennycode.gaikujin.util.GenerateDatabase;
import br.com.kennycode.gaikujin.util.JpaManager;

/**
 * That code is only to test sum, avg and more.
 * 
 * @author kenny
 *
 */
public class JpqlSumAndAvgTest {
	public static void main(String[] args) {

		// generate the data before the test work.
		GenerateDatabase.initialize(50);

		EntityManager em = JpaManager.getConnection();
		em.getTransaction().begin();

		//Account account = em.find(Account.class, getRandomAccountId());
		//String jpql = "select sum(t.value) from Transaction t where t.account = :pAccount and t.type = :pType order by t.value desc";
		String jpql = "select sum(t.value) from Transaction t where t.type = :pType order by t.value desc";
		Query query = em.createQuery(jpql);
		//query.setParameter("pAccount", account);

		// PURCHASES
		query.setParameter("pType", TypeTransaction.PURCHASES);
		BigDecimal totalOfPurchases = (BigDecimal) query.getSingleResult();
		// PAYMENTS
		query.setParameter("pType", TypeTransaction.PAYMENTS);
		BigDecimal totalOfPayments = (BigDecimal) query.getSingleResult();
		// RECEIPTS
		query.setParameter("pType", TypeTransaction.RECEIPTS);
		BigDecimal totalOfReceipts = (BigDecimal) query.getSingleResult();
		// SALSE
		query.setParameter("pType", TypeTransaction.SALES);
		BigDecimal totalOfSales = (BigDecimal) query.getSingleResult();
		
		jpql = "select avg(t.value) from Transaction t where t.type = :pType order by t.value desc";
		query = em.createQuery(jpql);
		// PURCHASES
		query.setParameter("pType", TypeTransaction.PURCHASES);
		BigDecimal avgOfPurchases = new BigDecimal((double) query.getSingleResult());
		// PAYMENTS
		query.setParameter("pType", TypeTransaction.PAYMENTS);
		BigDecimal avgOfPayments = new BigDecimal((double) query.getSingleResult());
		// RECEIPTS
		query.setParameter("pType", TypeTransaction.RECEIPTS);
		BigDecimal avgOfReceipts = new BigDecimal((double) query.getSingleResult());
		// SALSE
		query.setParameter("pType", TypeTransaction.SALES);
		BigDecimal avgOfSales = new BigDecimal((double) query.getSingleResult());
		
		
		System.out.println("------##SUM##------");
		printTotalByCategory("Total ", TypeTransaction.PURCHASES, totalOfPurchases);
		printTotalByCategory("Total ", TypeTransaction.PAYMENTS, totalOfPayments);
		printTotalByCategory("Total ", TypeTransaction.RECEIPTS, totalOfReceipts);
		printTotalByCategory("Total ", TypeTransaction.SALES, totalOfSales);
		System.out.println("------##SUM##------");
		
		System.out.println("------##AVG##------");
		printTotalByCategory("Avg",TypeTransaction.PURCHASES, avgOfPurchases);
		printTotalByCategory("Avg",TypeTransaction.PAYMENTS, avgOfPayments);
		printTotalByCategory("Avg",TypeTransaction.RECEIPTS, avgOfReceipts);
		printTotalByCategory("Avg",TypeTransaction.SALES, avgOfSales);
		System.out.println("------##AVG##------");
		
		// TODO - Use the max() from PSQL!!! ;D
		// TODO - Use the count() from PSQL!!!!!!!!! ;DDDDD
		
		em.getTransaction().commit();
		em.clear();
	}

	private static void printTotalByCategory(String msg, TypeTransaction type, BigDecimal total) {
		NumberFormat n = NumberFormat.getCurrencyInstance(Locale.US);

		try {
			System.out.println(msg +"of " + type + ": " + n.format(total.longValue()));
		} catch (NullPointerException e) {
			System.out.println(msg +" of " + type + ": " + n.format(0));
		}
	}

	private static int getRandomAccountId() {

		Random rand = new Random();
		EntityManager em = JpaManager.getConnection();
		em.getTransaction().begin();
		// query to get all accounts
		String jpql = "select a from Account a";
		Query query = em.createQuery(jpql);
		// get the size of accounts
		int accountId = rand.nextInt(query.getResultList().size()) + 1;
		em.getTransaction().commit();
		em.close();

		return accountId;
	}
}