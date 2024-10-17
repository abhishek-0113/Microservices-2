package com.example;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Investor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;   //Integer if error
	private String email;
	private String password;
	private double balance;
	public Investor() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Investor(String email, String password, double balance) {
		super();
		this.email = email;
		this.password = password;
		this.balance = balance;
	}
	public Investor(int id, String email, String password, double balance) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.balance = balance;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	@Override
	public String toString() {
		return "Investor [id=" + id + ", email=" + email + ", password=" + password + ", balance=" + balance + "]";
	}
	
	

}
