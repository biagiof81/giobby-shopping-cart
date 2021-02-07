package it.giobby.shoppingcart.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import it.giobby.shoppingcart.model.Cart;
import it.giobby.shoppingcart.reader.DataReaderException;
import it.giobby.shoppingcart.services.ShoppingCartService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShoppingCartServiceTest {

	@Autowired
	ShoppingCartService shoppingCartService;

	@Test
	public void shouldGetCartInput1() throws DataReaderException {

		Cart cart = shoppingCartService.getCartFromFile(1);

		assertNotNull(cart.getLineItems());

		assertEquals(9.50,cart.getTotalTaxes(),0.0);
		assertEquals(60.85,cart.getTotal(),0.0);

	}
	
	@Test
	public void shouldGetCartInput2() throws DataReaderException {

		Cart cart = shoppingCartService.getCartFromFile(2);

		assertNotNull(cart.getLineItems());

		assertEquals(11.14,cart.getTotalTaxes(),0.0);
		assertEquals(85.76,cart.getTotal(),0.0);

	}
	
	@Test
	public void shouldGetCartInput3() throws DataReaderException {

		Cart cart = shoppingCartService.getCartFromFile(3);

		assertNotNull(cart.getLineItems());

		assertEquals(12.82,cart.getTotalTaxes(),0.0);
		assertEquals(121.14,cart.getTotal(),0.0);

	}
	
	@Test(expected = DataReaderException.class)
	public void shouldGetException() throws DataReaderException {

		Cart cart = shoppingCartService.getCartFromFile(4);


	}
	
	@Test
	public void souldGetOrderSummary() throws DataReaderException, IOException {
		Cart cart = shoppingCartService.getCartFromFile(3);

		File file = shoppingCartService.file(cart);
		
		assertNotNull(file);
	}
}
