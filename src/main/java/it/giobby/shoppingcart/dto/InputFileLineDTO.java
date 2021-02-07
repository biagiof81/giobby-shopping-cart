package it.giobby.shoppingcart.dto;

public class InputFileLineDTO {

	private int quantity;
	private String productType;
	private double cost;
	
	public InputFileLineDTO(int quantity, String productType, double cost, boolean coupon) {
		super();
		this.quantity = quantity;
		this.productType = productType;
		this.cost = cost;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}


}
