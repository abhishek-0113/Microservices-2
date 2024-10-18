package com.example;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Trade {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String ticker;
	private double price;
	private int qty;
	private double totalCost;
	
	

	public Trade(long id,String ticker, double price, int qty, double totalCost) {
		super();
		this.id= id;
		this.ticker = ticker;
		this.price = price;
		this.qty = qty;
		this.totalCost = price * qty;
	}
	
	

	public Trade() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Trade(String ticker, Double price, int qty) {
		// TODO Auto-generated constructor stub
	}

	public String getTicker() {
		return ticker;
	}

	public void setTicker(String ticker) {
		this.ticker = ticker;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Trade [id=" + id + ", ticker=" + ticker + ", price=" + price + ", qty=" + qty + ", totalCost="
				+ totalCost + "]";
	}

	
	
	

}
