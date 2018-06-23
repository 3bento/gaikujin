package br.com.kennycode.gaikujin.util;

import java.util.Random;

import javax.persistence.EntityManager;

import br.com.kennycode.gaikujin.model.Account;

public class GenerateAccounts {

	
	// JPA states (transient, managed, detached)
	public static void main(String[] args) {

		int numberOfAccountToBeCreated=1000000;
		
		String[] names = { "Maryjo Maxey", "Mittie Marino", "Mercedez Maurer", "Margaretta Mckenney", "Ardath Abe",
				"Emilie Escamilla", "Laurena Lackner", "Loreen Lunn", "Noe Nowicki", "Tena Temme" };
		String[] banks = { "JPMorgan Chase", "Bank of America", "Wells Fargo", "Citigroup", "Goldman Sachs",
				"Morgan Stanley", "U.S. Bancorp", "	PNC Financial Services", "TD Bank, N.A." };
		String[] agency = { "10086", "12708", "10364", "13849", "13062", "12072", "13340", "13341", "10369", "12924",
				"12816", "13336", "13845", "13065", "13852", "13846", "12326", "14132", "14133", "12925", "10366"};
		String[] number = { "89","74","32","41","39","43","21","79","99","85","89","14","35"};

		for(int i = 0; i < numberOfAccountToBeCreated; i++) {
			EntityManager em = JpaManager.getConnection();
			Random rand = new Random();
			Account account = new Account();
			account.setName(names[rand.nextInt(names.length-1)+1].toString());
			account.setBank(banks[rand.nextInt(banks.length-1)+1].toString());
			account.setAgency(agency[rand.nextInt(agency.length-1)+1].toString());
			account.setNumber(number[rand.nextInt(number.length-1)+1].toString());
			
			System.out.println(account.toString());
			
			em.getTransaction().begin();
			em.persist(account);
			em.getTransaction().commit();
			em.close();
		}
		
	}

}
