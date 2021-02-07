package it.giobby.shoppingcart.model;

import java.util.ArrayList;
import java.util.List;

public class Cart {

	private String number;
	
	private List<CartItem> lineItems = new ArrayList<CartItem>();

	private double totalTaxes;
	
	private double total;
	
	private double subCost;
	
	private boolean coupon;
	
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
		return this.subCost+this.totalTaxes;
	}

	public void setLineItems(List<CartItem> lineItems) {
		this.lineItems = lineItems;
	}

	public List<CartItem> getLineItems() {
		return lineItems;
	}
	
	public boolean getCoupon() {
		return coupon;
	}

	public void setCoupon(boolean coupon) {
		this.coupon = coupon;
	}
	
	
    public double getSubCost() {
		return subCost;
	}

	public void setSubCost(double subCost) {
		this.subCost = subCost;
	}

	public double calculateSaleTax(CartItem lineItem) {
        
        return (float)Math.ceil(lineItem.getQuantity() * lineItem.getProduct().getTaxedValue() / .05f) * .05f;
    }

	//Business method
	public void addLineItem(CartItem lineItem) {
		
		lineItems.add(lineItem);
		
		this.totalTaxes+= calculateSaleTax(lineItem);
		
		this.subCost+=(lineItem.getQuantity()*lineItem.getProduct().getPrice());
		
	}
	
}
