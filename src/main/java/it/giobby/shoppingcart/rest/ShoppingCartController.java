package it.giobby.shoppingcart.rest;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.giobby.shoppingcart.model.Cart;
import it.giobby.shoppingcart.reader.DataReaderException;
import it.giobby.shoppingcart.services.ShoppingCartService;
import it.giobby.shoppingcart.services.TaxRateService;

@RestController
@RequestMapping(path = "/carts", produces = MediaType.APPLICATION_JSON_VALUE)
public class ShoppingCartController {

	@Autowired
	TaxRateService taxRateService;
	
	@Autowired
	ShoppingCartService shoppingCartService;
	
	
	@GetMapping("/{cartNumber}")
	public void getCart(@PathVariable("cartNumber") Integer cartNumber, HttpServletResponse response) throws IOException, DataReaderException {
		
		ServletOutputStream out = response.getOutputStream();
	    Cart cart = shoppingCartService.getCartFromFile(cartNumber);
	    
	    File file = shoppingCartService.file(cart);
	    
	    response.setContentType("text/plain");
	    response.setHeader("Content-Disposition","attachment;filename="+file.getName());
	    
	    
	    Path path = Paths.get(file.getName());
	    
	    Files.copy(path, out);
	    
	    out.flush();
	    out.close();
	}
	
}
