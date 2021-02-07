package it.giobby.shoppingcart.services;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.List;
import java.util.Locale;

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
	
	@Value("${coupon__discount}")
	private double couponDiscount;
	
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
		
		cart.setNumber(cartNumber.toString());

		boolean couponApplied = false;
		if (lines.contains(new String("Coupon applied"))) {
			couponApplied = true;
			lines.remove(new String("Coupon applied"));
		}
		
		cart.setCouponApplied(couponApplied);

		for (String line : lines) {

			final String array[] = line.split(" ");

			final int quantity = NumberUtils.parseNumber(array[0], Integer.class);
			final double unitCost = NumberUtils.parseNumber(array[3], Double.class);
			final String productType = array[1];

			Product prod = new Product();
			prod.setPrice(unitCost);
			prod.setType(productType);
			
			if(productType.contains("book") && couponApplied){
				prod.setDiscount(couponDiscount);
			}
	
			CartItem cartItem = new CartItem();
			cartItem.setProduct(prod);
			cartItem.setQuantity(quantity);
			cartItem.setTaxRate(taxRateService.findRateByProductType(productType));

			cart.addLineItem(cartItem);
		}

		return cart;
	}

	public File file(Cart cart) throws IOException {
		File orderSummary = new File("order_summary.txt");
		FileWriter fileWriter = new FileWriter(orderSummary);

		
		DecimalFormat format = (DecimalFormat) DecimalFormat.getCurrencyInstance(Locale.GERMANY);
		PrintWriter printWriter = new PrintWriter(fileWriter);
		printWriter.printf(orderSummaryHeader, cart.getNumber());
		printWriter.println();
		
		for(CartItem cartItem: cart.getLineItems()) {
			printWriter.printf("%s %s | %s", cartItem.getQuantity().toString(),cartItem.getProduct().getType(),format.format(cartItem.getQuantity()*cartItem.getProduct().getPrice()).replace(",", ".").replace(",", ".").replace(".00", ""));
			printWriter.println();
		}
		if(cart.isCouponApplied()){
			printWriter.print("1 coupon added | GIFT5");
			printWriter.println();

		}
		printWriter.println();
		printWriter.printf(orderSummaryTotalTaxes,format.format(cart.getTotalTaxes()).replace(",", ".").replace(".00", ""));
		printWriter.println();
		printWriter.printf(orderSummaryTotal, format.format(cart.getTotal()).replace(",", ".").replace(".00", ""));
		printWriter.println();

		

		printWriter.close();
		
		
		return orderSummary;
	}
}
