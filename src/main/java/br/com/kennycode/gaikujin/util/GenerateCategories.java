package br.com.kennycode.gaikujin.util;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import br.com.kennycode.gaikujin.model.Category;
import br.com.kennycode.gaikujin.model.TypeCategory;

public class GenerateCategories {

	public static final String[] EXPENSES_TYPE = { "expenses", "Charitable Giving", "Gifts", "Automotive/Fuel",
			"Healthcare/Medical", "Insurance", "Office Expenses", "Services/Supplies", "Postage/Shipping", "Taxes",
			"Other Expenses", "Check Payment", "Service Charges/Fees", "Home Improvement",
			"Electronics/General Merchandise", "Groceries", "Pets/Pet Care", "Mortage", "Rent", "Loans", "Utilities",
			"Cable/Satellite/Telecom", "Personal/Family", "ATM/Cash Withdraws", "Education", "Subscriptions/Renewals",
			"Restaurants", "Entertainment/Recreation", "Travel" };
	public static final String[] INCOME_TYPE = { "income", "Sales/Service Income", "Salary/Regular Income",
			"Investment/Retirement Income", "Interest Income", "Other Income", "	\r\n" + "Rewards", "Deposits",
			"Expenses Reimbursements", "Refunds/Adjustments" };
	public static final String[] TRANSFER_TYPE = { "transfer", "Transfers", "Securities Trades", "Saving",
			"Credit Card Payments" };
	public static final String[] DEFERRED_COMPENSATION_TYPE = { "deferred_comprensation", "Retirement Contributions" };
	public static final String[] GENERAL_TYPE = { "general", "Uncategorized" };

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		initialize();
	}

	public static void initialize() {
		populateCategories(EXPENSES_TYPE);
		populateCategories(INCOME_TYPE);
		populateCategories(TRANSFER_TYPE);
		populateCategories(DEFERRED_COMPENSATION_TYPE);
		populateCategories(GENERAL_TYPE);
	}

	private static void populateCategories(String[] categories) {
		EntityManager em = JpaManager.getConnection();
		em.getTransaction().begin();

		TypeCategory type = TypeCategory.valueOf(categories[0].toUpperCase());

		for (String name : categories) {
			// check the name with type
			if (name.equals(type.name().toLowerCase()))
				continue;

			Category category;
			try {
				category = em.createQuery("select c from Category c where c.name=:pName", Category.class)
						.setParameter("pName", name).getSingleResult();
			} catch (NoResultException e) {
				category = new Category(name, type);
				em.persist(category);
			}
		}

		em.getTransaction().commit();
		em.close();
	}
}
