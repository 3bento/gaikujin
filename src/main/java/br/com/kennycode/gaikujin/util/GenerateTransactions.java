package br.com.kennycode.gaikujin.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.kennycode.gaikujin.model.Account;
import br.com.kennycode.gaikujin.model.Category;
import br.com.kennycode.gaikujin.model.Transaction;
import br.com.kennycode.gaikujin.model.TypeCategory;
import br.com.kennycode.gaikujin.model.TypeTransaction;

public class GenerateTransactions {

	private static int NUMBER_OF_TRANSACTIONS = 100;
	private static int MAX_AMOUNT_OF_MONEY = 100000;

	/**
	 * create users 
	 * create category
	 * create transaction
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		GenerateAccounts.initialize(10);
		GenerateCategories.initialize();
		initialize();
		
	}

	public static void initialize() {
		initialize(NUMBER_OF_TRANSACTIONS);
	}

	public static void initialize(int numberOfTransactions) {
		/*
		 * EntityManager em = JpaManager.getConnection(); em.getTransaction().begin();
		 */
		for (int i = 0; i <= numberOfTransactions; i++) {
			EntityManager em = JpaManager.getConnection();
			em.getTransaction().begin();

			Transaction t = new Transaction();
			Random rand = new Random();
			// CATEGORY BY TYPE
			// get random number 1-5 to type of category
			// get random number 1-max of that specific type.
			// set list with 1 or more categories
			// try to get that category in database, if there is not result try again.
			t.setCategories(getRandomCategoriesByType());

			// I FORGOT THE VALUE !!!!!!!!!!! XDDDDD
			BigDecimal randomValue = new BigDecimal(rand.nextInt(MAX_AMOUNT_OF_MONEY) + 1);
			t.setValue(randomValue);

			// RANDOM ACCOUNT (OKAY)
			Account account = em.find(Account.class, randomIdFromAccounts());
			t.setAccount(account);
			// GENERATE RANDOM DATE (OKAY)
			t.setDate(generateRandomDate());
			// RANDOM (OKAY) XDDDDDDDD
			t.setDescription("I did it!!!!");
			// RANDOM VALUE (0-3) (OKAY)
			int randNum = rand.nextInt(TypeTransaction.values().length);
			t.setType(TypeTransaction.values()[randNum]);

			em.persist(t);

			em.getTransaction().commit();
			em.close();
		}
	}

	// TODO - CHANGE IT, SOON as POSSIBLE.
	// Actual behavior: It is temporary, it will create duplicate category (not
	// good).
	// Expected behavior: get the size of categories and get random number with it,
	// and get one category from database.
	private static List<Category> getRandomCategoriesByType() {
		Random rand = new Random();
		int randNum = 0;

		// GET ALL CATEGORIES BY ONE TYPE!!!
		TypeCategory type = TypeCategory.values()[rand.nextInt(5)];
		EntityManager em = JpaManager.getConnection();
		em.getTransaction().begin();
		String jpql = "select c from Category c where c.type=:pType";
		Query query = em.createQuery(jpql).setParameter("pType", type);
		int qtdRows = query.getResultList().size();
		em.getTransaction().commit();
		em.close();

		// GET RANDOM CATEGORIES
		List<Category> categories = new ArrayList<Category>();
		int categoriesToBeCreated = rand.nextInt(10) + 1;
		for (int i = 0; i <= categoriesToBeCreated; i++) {

			em = JpaManager.getConnection();
			em.getTransaction().begin();
			// random number based in qtdRows
			randNum = rand.nextInt(qtdRows) + 1;
			Category category = em.find(Category.class, randNum);
			em.getTransaction().commit();
			em.close();
			categories.add(category);
			System.out.println(category.toString());
		}
		return categories;
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
