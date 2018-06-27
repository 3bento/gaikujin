package br.com.kennycode.gaikujin.util;

public class GenerateDatabase {
	
	/**
	 * That classes will generate all of data that we need to work with that project.
	 * Probably i will set flag to clean the database and more stuffs here.
	 * You can also use generateAccounts or generateCategories direct.
	 * @param categories
	 */
	public static void main(String[] categories) {
		GenerateAccounts.initialize();
		GenerateCategories.initialize();
		GenerateTransactions.initialize();
		// TODO - How many records is it doing?
		// TODO - USE LOGS (debug) to show quantity of inserts
		// TODO - How long is it take to insert 1.000.000 of each entity?
	}
}
