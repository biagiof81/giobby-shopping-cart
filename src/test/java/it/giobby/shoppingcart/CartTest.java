package it.giobby.shoppingcart;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import it.giobby.shoppingcart.model.Cart;
import it.giobby.shoppingcart.model.CartItem;
import it.giobby.shoppingcart.model.Product;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CartTest {

	Cart cart = new Cart();
	
	@Test
	public void shouldAddCartItem() {
		CartItem cartItem = new CartItem();
		
		Product product1 = new Product();
		
		product1.setPrice(9.9);
		product1.setTaxRate(0.04);
		
		cartItem.setProduct(product1);
		
		
		cartItem.setQuantity(2);
		cart.addLineItem(cartItem);
		
		assertNotNull(cart.getLineItems());
		
		assertEquals(new BigDecimal("0.80"), new BigDecimal(cart.getTotalTaxes()).setScale(2, RoundingMode.FLOOR));
		
		assertEquals(new BigDecimal("20.60"),new BigDecimal(cart.getTotal()).setScale(2, RoundingMode.FLOOR));
	}
}
