package it.giobby.shoppingcart.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import it.giobby.shoppingcart.services.TaxRateService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaxRateServiceTest {

	@Autowired
	TaxRateService taxRateService;
	
	@Test
	public void shouldGetBasicTaxRate() {
		String productType = "t-shirts";
		
		double basicTaxRate = taxRateService.findRateByProductType(productType);
		
		assertNotNull(basicTaxRate);
		assertEquals(0.22, basicTaxRate,0.0);
	}
	
	@Test
	public void shouldGetFoodBeverageTaxRate() {
		String productType = "beer";
		
		double basicTaxRate = taxRateService.findRateByProductType(productType);
		
		assertNotNull(basicTaxRate);
		assertEquals(0.04, basicTaxRate,0.0);
		
	}
	
	@Test
	public void shouldGetBookTaxRate() {
		String productType = "book";
		
		double basicTaxRate = taxRateService.findRateByProductType(productType);
		
		assertNotNull(basicTaxRate);
		assertEquals(0.10, basicTaxRate,0.0);
		
	}
}
