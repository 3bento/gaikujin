package br.com.kennycode.gaikujin.dao;

import javax.persistence.EntityManager;

import br.com.kennycode.gaikujin.model.Account;

public class AccountDAO {

	private EntityManager em;

	public AccountDAO(EntityManager em) {
		super();
		this.em = em;
	}
	
	public Account create(Account account) {
		em.getTransaction().begin();
		// account (managed)
		em.persist(account);
		em.getTransaction().commit();
		// account (detached)
		em.close();
		return account;
	}
}
