package br.com.kennycode.gaikujin.util;

import java.util.Random;

import javax.persistence.EntityManager;

import br.com.kennycode.gaikujin.model.Transaction;
import br.com.kennycode.gaikujin.model.TypeTransaction;

public class GenerateTransactions {

	private static int NUMBER_OF_TRANSACTIONS = 100;

	public static void main(String[] args) {
		initialize();
	}

	public static void initialize() {
		initialize(NUMBER_OF_TRANSACTIONS);
	}

	// TODO - It is kind of hard random data to generate, i will finish it later!
	// The ideia is it! may i change it.
	private static void initialize(int numberOfTransactions) {
/*		EntityManager em = JpaManager.getConnection();
		em.getTransaction().begin();*/
		
		Transaction t = new Transaction();

		Random rand = new Random();

		// CATEGORY BY TYPE
		// get random number 1-5 to type of category
		// get random number 1-max of that specific type.
		// set list with 1 or more categories
		// try to get that category in database, if there is not result try again.

		// ACCOUNT
		// get random user 1-max(number of accounts)

		// DATE
		// get random date between the range 1, 10 years.
		// example: Date randomDate = new Date(ThreadLocalRandom.nextLong(d1.getTime(),
		// d2.getTime()));

		// DESCRIPTION
		// RANDOM TEXT!!!

		// TYPE TRNASACTION
		// RANDOM VALUE (0-3)
		int type = rand.nextInt(TypeTransaction.values().length - 1);
		t.setType(TypeTransaction.values()[type]);
		
		System.out.println(t.toString());

/*		em.getTransaction().commit();
		em.close();*/
	}
}
