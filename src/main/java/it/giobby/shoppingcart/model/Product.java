package it.giobby.shoppingcart.model;

public class Product {

	private String type;

	private double price;

	private double taxRate;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getTaxRate() {
		return taxRate;
	}

	public void setTaxRate(double taxRate) {
		this.taxRate = taxRate;
	}

	// business method
	public double getTaxedValue() {
		return this.price * this.taxRate;
	}
}
