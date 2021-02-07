package it.giobby.shoppingcart.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class Cart {

	private String number;
	
	private List<CartItem> lineItems = new ArrayList<CartItem>();

	private double totalTaxes;
	
	private double total;
	
	private double subCost;
	
	private boolean couponApplied;
	
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public double getTotalTaxes() {
		return totalTaxes;
	}

	public double getTotal() {
		return new BigDecimal(this.subCost+this.totalTaxes).setScale(2,RoundingMode.FLOOR).doubleValue();
	}

	public void setLineItems(List<CartItem> lineItems) {
		this.lineItems = lineItems;
	}

	public List<CartItem> getLineItems() {
		return lineItems;
	}
	
    public double getSubCost() {
		return subCost;
	}

	public void setSubCost(double subCost) {
		this.subCost = subCost;
	}

	public double calculateSaleTax(CartItem lineItem) {
		return Math.round(lineItem.getQuantity() * lineItem.getProduct().getPrice() * lineItem.getTaxRate() * 100.0) / 100.0;
        
    }

	//Business method
	public void addLineItem(CartItem lineItem) {
		
		lineItems.add(lineItem);
		
		this.totalTaxes+= calculateSaleTax(lineItem);
		
		this.subCost+=  lineItem.getQuantity()*lineItem.getProduct().getPrice();
		
	}

	public boolean isCouponApplied() {
		return couponApplied;
	}

	public void setCouponApplied(boolean couponApplied) {
		this.couponApplied = couponApplied;
	}
	
}
