package br.com.kennycode.gaikujin.test;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.com.kennycode.gaikujin.model.TypeTransaction;
import br.com.kennycode.gaikujin.util.GenerateDatabase;
import br.com.kennycode.gaikujin.util.JpaManager;

/**
 * That code is only to test sum, avg and more.
 * 
 * @author kenny
 *
 */
public class JpqlAvgAndGroupByTest {
	public static void main(String[] args) {

		// generate the data before the test work.
		GenerateDatabase.initialize(50);

		EntityManager em = JpaManager.getConnection();
		em.getTransaction().begin();

		//Account account = em.find(Account.class, getRandomAccountId());
		//String jpql = "select sum(t.value) from Transaction t where t.account = :pAccount and t.type = :pType order by t.value desc";
		String jpql = "select avg(t.value) from Transaction t where t.type = :pType group by year(t.date), month(t.date), day(t.date)";
		TypedQuery<Double> query = em.createQuery(jpql, Double.class);
		// PURCHASES
		query.setParameter("pType", TypeTransaction.PURCHASES);
		List<Double> avgOfPurchases = query.getResultList();
		// PAYMENTS
		query.setParameter("pType", TypeTransaction.PAYMENTS);
		List<Double> avgOfPayments = query.getResultList();
		// RECEIPTS
		query.setParameter("pType", TypeTransaction.RECEIPTS);
		List<Double> avgOfReceipts = query.getResultList();
		// SALSE
		query.setParameter("pType", TypeTransaction.SALES);
		List<Double> avgOfSales = query.getResultList();

		printTotalByCategory(TypeTransaction.PURCHASES, avgOfPurchases);
		printTotalByCategory(TypeTransaction.PAYMENTS, avgOfPayments);
		printTotalByCategory(TypeTransaction.RECEIPTS, avgOfReceipts);
		printTotalByCategory(TypeTransaction.SALES, avgOfSales);

		// TODO - Use the max() from PSQL!!! ;D
		// TODO - Use the count() from PSQL!!!!!!!!! ;DDDDD

		em.getTransaction().commit();
		em.clear();
	}

	private static void printTotalByCategory(TypeTransaction type, List<Double> avgs) {
		NumberFormat n = NumberFormat.getCurrencyInstance(Locale.US);
		for (Double avg : avgs) {
			try {
				System.out.println("Average of " + type + " : " + n.format(avg));
			} catch (NullPointerException e) {
				System.out.println("Average of " + type + ": " + n.format(0));
			}
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