package br.com.kennycode.gaikujin.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaManager {

	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("gaikujin");

	public static EntityManager getConnection() {
		return emf.createEntityManager();
	}
}
