package br.com.kennycode.gaikujin.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.kennycode.gaikujin.model.TypeTransaction;

public class TransactionDAO {

	private EntityManager connection;
	
	public TransactionDAO(EntityManager connection) {
		this.connection = connection;

	}

	public List<Double> getAverageByTypeAndDate(TypeTransaction type) {
		//Account account = em.find(Account.class, getRandomAccountId());
		//String jpql = "select sum(t.value) from Transaction t where t.account = :pAccount and t.type = :pType order by t.value desc";
		String jpql = "select avg(t.value) from Transaction t where t.type = :pType group by year(t.date), month(t.date), day(t.date)";
		TypedQuery<Double> query = connection.createQuery(jpql, Double.class);
		// PURCHASES
		query.setParameter("pType", type);
		return query.getResultList();
	}
}
