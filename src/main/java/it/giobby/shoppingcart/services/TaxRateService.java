package it.giobby.shoppingcart.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class TaxRateService {

	@Value("${basic_tax_rate}")
	double basicTaxRate;

	@Value("${book_tax_rate}")
	double bookTaxRate;

	@Value("${foodbeverage_tax_rate}")
	double foodbeverageTaxrate;
	
	@Value("${foodbeverage_items_list}")
	String[] foodbeverageItemsList;

	
	public double findRateByProductType(String productType) {
		
		if(isFoodBeverageProduct(productType)) {
			return this.foodbeverageTaxrate;
		}
		
		if(productType.toLowerCase().contains("book")) {
			return this.bookTaxRate;
		}
		
		return this.basicTaxRate;
	}
	
	private boolean isFoodBeverageProduct(String productType) {

		for (final String token : foodbeverageItemsList) {
			if (productType.toLowerCase().contains(token)) {
				return true;
			}
		}
		return false;
	}

	
}
