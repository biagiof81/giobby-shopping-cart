package it.giobby.shoppingcart.reader;

import java.io.InputStream;
import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

@Component
public class ShoppingCartJsonReader implements DataReader{

	@Override
	public List<String> read(Resource resoure) throws DataReaderException {
		InputStream inputStream = null;
		try {
			ObjectMapper mapper = new ObjectMapper();
			return mapper.readValue(inputStream, TypeFactory.defaultInstance().constructCollectionType(List.class, String.class));
		} catch (Exception ex) {
			throw new DataReaderException("input file reader error",ex);
		} 
	}

}
