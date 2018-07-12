package br.com.kennycode.gaikujin.model;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class ClientTest {

	private Client client = new Client();
	private static final Integer ID = new Integer(1);
	private static final String NAME = "kenny code";
	private static final String JOB = "developer";
	private static final String ADDRESS = " street 123";
	private static final Account ACCOUNT = new Account();

	@Before
	public void setUp() throws Exception {
		client.setId(ID);
		client.setName(NAME);
		client.setJob(JOB);
		client.setAddress(ADDRESS);
		client.setAccount(ACCOUNT);
	}

	@Test
	public void getId() {
		assertEquals(client.getId(), ID);
	}
	
	@Test
	public void getName() {
		assertEquals(client.getName(), NAME);
	}

	@Test
	public void getJob() {
		assertEquals(client.getJob(), JOB);
	}

	@Test
	public void getAddress() {
		assertEquals(client.getAddress(), ADDRESS);
	}

	@Test
	public void getAccount() {
		assertEquals(client.getAccount(), ACCOUNT);
	}
}
