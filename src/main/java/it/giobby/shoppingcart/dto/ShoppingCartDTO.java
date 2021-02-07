package it.giobby.shoppingcart.dto;

import java.util.List;

public class ShoppingCartDTO {

	private List<InputFileLineDTO> products;
	
	private boolean coupon;

	public List<InputFileLineDTO> getProducts() {
		return products;
	}

	public void setProducts(List<InputFileLineDTO> products) {
		this.products = products;
	}

	public boolean getCoupon() {
		return coupon;
	}

	public void setCoupon(boolean coupon) {
		this.coupon = coupon;
	}

	
	
}
