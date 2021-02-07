package it.giobby.shoppingcart.services;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.NumberUtils;

import it.giobby.shoppingcart.model.Cart;
import it.giobby.shoppingcart.model.CartItem;
import it.giobby.shoppingcart.model.Product;
import it.giobby.shoppingcart.reader.DataReaderException;
import it.giobby.shoppingcart.reader.ShoppingCartItemFileReader;
import it.giobby.shoppingcart.reader.ShoppingCartJsonReader;

@Service
public class ShoppingCartService {

	@Autowired
	TaxRateService taxRateService;

	@Autowired
	ShoppingCartItemFileReader shoopingCartFileReader;

	@Autowired
	ShoppingCartJsonReader shoppingCartJsonReader;

	private static final String INPUT_PREFIX = "input";
	
	@Value("${order.summary.header}")
	private String orderSummaryHeader;
	
	@Value("${order.summary.totaltaxes}")
	private String orderSummaryTotalTaxes;
	
	@Value("${order.summary.total}")
	private String orderSummaryTotal;

	public Cart getCartFromFile(Integer cartNumber) throws DataReaderException {

		Resource resource = new ClassPathResource(INPUT_PREFIX + Integer.toString(cartNumber));

		List<String> lines = shoopingCartFileReader.read(resource);

		Cart cart = new Cart();

		if (lines.contains(new String("Coupon Applied"))) {
			cart.setCoupon(true);
			lines.remove(new String("Coupon Applied"));
		}

		for (String line : lines) {

			final String array[] = line.split(" ");

			final int quantity = NumberUtils.parseNumber(array[0], Integer.class);
			final double unitCost = NumberUtils.parseNumber(array[3], Double.class);
			final String productType = array[1];

			Product prod = new Product();
			prod.setPrice(unitCost);
			prod.setType(productType);
			prod.setTaxRate(taxRateService.findRateByProductType(productType));

			CartItem cartItem = new CartItem();
			cartItem.setProduct(prod);
			cartItem.setQuantity(quantity);

			cart.addLineItem(cartItem);
		}

		return cart;
	}

	public File file(Cart cart) throws IOException {
		File orderSummary = new File("order_summary.txt");
		FileWriter fileWriter = new FileWriter(orderSummary);

		PrintWriter printWriter = new PrintWriter(fileWriter);
		printWriter.printf(orderSummaryHeader, cart.getNumber());
		
		cart.getLineItems().forEach(cartItem->printWriter.printf("%d %s" + "|" +"%d u00a3", cartItem.getQuantity(),cartItem.getProduct().getType(),cartItem.getQuantity()*cartItem.getProduct().getPrice()));
		printWriter.printf(orderSummaryTotalTaxes,cart.getTotalTaxes());
		printWriter.printf(orderSummaryTotal, cart.getTotal());
		printWriter.close();
		
		
		return orderSummary;
	}
}
