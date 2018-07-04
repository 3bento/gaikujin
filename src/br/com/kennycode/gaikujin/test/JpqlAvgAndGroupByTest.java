package br.com.kennycode.gaikujin.test;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import javax.persistence.EntityManager;

import br.com.kennycode.gaikujin.dao.TransactionDAO;
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

		GenerateDatabase.initialize(50);

		EntityManager em = JpaManager.getConnection();
		em.getTransaction().begin();

		TransactionDAO dao = new TransactionDAO(em);
		
		List<Double> avgOfPayments = dao.getAverageByTypeAndDate(TypeTransaction.PAYMENTS); 
		List<Double> avgOfPurchases = dao.getAverageByTypeAndDate(TypeTransaction.PURCHASES); 
		List<Double> avgOfReceipts = dao.getAverageByTypeAndDate(TypeTransaction.RECEIPTS); 
		List<Double> avgOfSales = dao.getAverageByTypeAndDate(TypeTransaction.SALES); 

		printTotalByCategory(TypeTransaction.PURCHASES, avgOfPurchases);
		printTotalByCategory(TypeTransaction.PAYMENTS, avgOfPayments);
		printTotalByCategory(TypeTransaction.RECEIPTS, avgOfReceipts);
		printTotalByCategory(TypeTransaction.SALES, avgOfSales);

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

//	private static int getRandomAccountId() {
//		Random rand = new Random();
//		EntityManager em = JpaManager.getConnection();
//		em.getTransaction().begin();
//		// query to get all accounts
//		String jpql = "select a from Account a";
//		Query query = em.createQuery(jpql);
//		// get the size of accounts
//		int accountId = rand.nextInt(query.getResultList().size()) + 1;
//		em.getTransaction().commit();
//		em.close();
//		return accountId;
//	}
}