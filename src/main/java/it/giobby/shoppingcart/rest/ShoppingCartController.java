package it.giobby.shoppingcart.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.giobby.shoppingcart.dto.ShoppingCartDTO;
import it.giobby.shoppingcart.model.Cart;
import it.giobby.shoppingcart.model.CartItem;
import it.giobby.shoppingcart.model.Product;
import it.giobby.shoppingcart.services.ShoppingCartService;
import it.giobby.shoppingcart.services.TaxRateService;

@RestController
@RequestMapping(path = "/carts", produces = MediaType.APPLICATION_JSON_VALUE)
public class ShoppingCartController {

	@Autowired
	TaxRateService taxRateService;
	
	@Autowired
	ShoppingCartService shoppingCartService;
	
	
	@GetMapping("/file/{}")
	public void loadCartFromFile() {
		
	}
	
	@GetMapping("/json/{}")
	public void loadCartFromJson() {
		
	}
}
