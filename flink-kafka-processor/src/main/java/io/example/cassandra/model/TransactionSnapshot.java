package io.example.cassandra.model;

import org.springframework.data.cassandra.mapping.Column;
import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

/**
 * @author Tuhin Gupta
 * 
 */


@Table(value = "transaction_snapshot")
public class TransactionSnapshot {
	
	@PrimaryKey(value = "account_number")
	String accountNumber;
	
	@Column(value = "transaction_amount")
	Float amount;
	
	

	public TransactionSnapshot(String accountNumber, Float amount) {
		super();
		this.accountNumber = accountNumber;
		this.amount = amount;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public Float getAmount() {
		return amount;
	}

	public void setAmount(Float amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "TransactionSnapshot [accountNumber=" + accountNumber + ", amount=" + amount + "]";
	}
	
	
	

}
