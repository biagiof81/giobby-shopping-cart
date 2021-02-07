package it.giobby.shoppingcart.model;

import java.math.BigDecimal;

public class Category {

	private String name;
	
	private BigDecimal taxRate;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getTaxRate() {
		return taxRate;
	}

	public void setTaxRate(BigDecimal taxRate) {
		this.taxRate = taxRate;
	}
	
	
}
