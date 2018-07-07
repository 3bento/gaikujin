package br.com.kennycode.gaikujin.dao;

import javax.persistence.EntityManager;

import br.com.kennycode.gaikujin.model.Client;

public class ClientDAO {

	
	private EntityManager connection;

	public ClientDAO(EntityManager connection) {
		this.connection = connection;
		// TODO Auto-generated constructor stub
	}

	public Client create(Client client) {
		connection.getTransaction();
		connection.persist(client);
		connection.getTransaction().commit();
		connection.close();
		return client;
	}

}
