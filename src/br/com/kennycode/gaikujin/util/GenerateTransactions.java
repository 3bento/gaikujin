package br.com.kennycode.gaikujin.util;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.kennycode.gaikujin.model.Account;
import br.com.kennycode.gaikujin.model.Transaction;
import br.com.kennycode.gaikujin.model.TypeTransaction;

public class GenerateTransactions {

	private static int NUMBER_OF_TRANSACTIONS = 100;

	public static void main(String[] args) {
		initialize();
	}

	public static void initialize() {
		GenerateAccounts.initialize(10);
		initialize(NUMBER_OF_TRANSACTIONS);
	}

	// TODO - It is kind of hard random data to generate, i will finish it later!
	// The ideia is it! may i change it.
	private static void initialize(int numberOfTransactions) {
		/*
		 * EntityManager em = JpaManager.getConnection(); em.getTransaction().begin();
		 */

		EntityManager em = JpaManager.getConnection();
		em.getTransaction().begin();

		Transaction t = new Transaction();
		Random rand = new Random();

		// CATEGORY BY TYPE
		// get random number 1-5 to type of category
		// get random number 1-max of that specific type.
		// set list with 1 or more categories
		// try to get that category in database, if there is not result try again.

		// RANDOM ACCOUNT (OKAY)
		Account account = em.find(Account.class, randomIdFromAccounts());
		t.setAccount(account);
		// GENERATE RANDOM DATE (OKAY)
		t.setDate(generateRandomDate());
		// RANDOM (OKAY) XDDDDDDDD
		t.setDescription("I did it!!!!");
		// RANDOM VALUE (0-3) (OKAY)
		int type = rand.nextInt(TypeTransaction.values().length - 1);
		t.setType(TypeTransaction.values()[type]);

		System.out.println(t.toString());

		em.getTransaction().commit();
		em.close();

		/*
		 * em.getTransaction().commit(); em.close();
		 */
	}

	private static int randomIdFromAccounts() {
		EntityManager em = JpaManager.getConnection();
		em.getTransaction().begin();
		String jpql = "select a from Account a";
		Query query = em.createQuery(jpql);
		Random rand = new Random();
		int numOfAccounts = query.getResultList().size();
		em.getTransaction().commit();
		em.close();
		if (numOfAccounts == 0)
			return 0;
		return rand.nextInt(numOfAccounts) + 1;

	}

	private static Calendar generateRandomDate() {
		// get instance of calendar
		Calendar start = Calendar.getInstance();
		// set a old date.
		start.set(2000, 1, 1);
		// get new instance of calendar
		Calendar today = Calendar.getInstance();
		// get a random date!!
		Date randomDate = new Date(
				ThreadLocalRandom.current().nextLong(start.getTime().getTime(), today.getTime().getTime()));
		// pass new date to calendar
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(randomDate);

		return calendar;
	}
}
