package it.giobby.shoppingcart.reader;

import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
public class ShoppingCartItemFileReader implements DataReader {

	@Override
	public List<String> read(Resource resoure) throws DataReaderException {
		InputStream inputStream = null;
		List<String> result = new LinkedList<String>();
		try {
			inputStream = resoure.getInputStream();

			Scanner scanner = new Scanner(inputStream);
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				result.add(line);
			}

			return result;
		} catch (Exception ex) {
			throw new DataReaderException("input file reader error",ex);
		} 
	}
}
