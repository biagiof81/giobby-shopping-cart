package it.giobby.shoppingcart.reader;

import java.util.List;

import org.springframework.core.io.Resource;

public interface DataReader {

	List<String> read(Resource resource) throws DataReaderException;
}
