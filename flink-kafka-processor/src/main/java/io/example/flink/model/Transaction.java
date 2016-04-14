package io.example.flink.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;




/**
 * @author Tuhin Gupta
 *
 */
public class Transaction {
	
	
	String transactionDate;
	String accountNumber;
	String transactionAmount;
	String lastName;
	String id;
	String firstName;
	String email;
	
	@JsonProperty("transaction_date")
	public String getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}
	
	@JsonProperty("account_number")
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	
	@JsonProperty("transaction_amount")
	public String getTransactionAmount() {
		return transactionAmount;
	}
	public void setTransactionAmount(String transactionNmount) {
		this.transactionAmount = transactionNmount;
	}
	
	@JsonProperty("last_name")
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	@JsonProperty("id")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	@JsonProperty("first_name")
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	@JsonProperty("email")
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public BigDecimal getAmount(){
		return new BigDecimal(this.transactionAmount);
	}
	
	
}
