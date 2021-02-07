package it.giobby.shoppingcart.dto;

import java.math.BigDecimal;

public class ProductDTO {

	private Integer quantity;

	private String type;

	private BigDecimal price;

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}


}
