package it.giobby.shoppingcart.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;
import java.math.RoundingMode;

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
	public void shouldGetCartFromFile() throws DataReaderException {

		Cart cart = shoppingCartService.getCartFromFile(1);

		assertNotNull(cart.getLineItems());

		assertEquals(new BigDecimal("9.50"), new BigDecimal(cart.getTotalTaxes()).setScale(2, RoundingMode.FLOOR));
		assertEquals(new BigDecimal("60.85"), new BigDecimal(cart.getTotal()).setScale(2, RoundingMode.FLOOR));

	}
}
