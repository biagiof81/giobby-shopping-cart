package it.giobby.shoppingcart.model;

import java.math.BigDecimal;
import java.util.List;

public class PurchaseReport {

	private List<CartItem> lineItems;
	BigDecimal salesTax = null;
	BigDecimal total = null;

	public List<CartItem> getLineItems() {
		return lineItems;
	}

	public void setLineItems(List<CartItem> lineItems) {
		this.lineItems = lineItems;
	}

	public BigDecimal getSalesTax() {
		return salesTax;
	}

	public void setSalesTax(BigDecimal salesTax) {
		this.salesTax = salesTax;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}
}
