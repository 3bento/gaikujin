package br.com.kennycode.gaikujin.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String number;
	private String bank;
	private String agency;

	// it is biredirectional "data-bind"
	// https://vladmihalcea.com/the-best-way-to-map-a-onetomany-association-with-jpa-and-hibernate/
	// https://stackoverflow.com/questions/2584521/in-a-bidirectional-jpa-onetomany-manytoone-association-what-is-meant-by-the-in
	@OneToMany(mappedBy="account")
	private List<Transaction> transactions;

	public List<Transaction> getTransactions() {
		return transactions;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public String getAgency() {
		return agency;
	}

	public void setAgency(String agency) {
		this.agency = agency;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", name=" + name + ", number=" + number + ", bank=" + bank + ", agency=" + agency
				+ "]";
	}
}
