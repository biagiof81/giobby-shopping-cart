package it.giobby.shoppingcart.reader;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShoppingCartFileReaderTest {

	@Autowired
	ShoppingCartItemFileReader shoppingCartFileReader;
	
	@Test
	public void shouldReadInputFile() throws DataReaderException {
		Resource resource = new ClassPathResource("input1");
		
		List<String> lines = shoppingCartFileReader.read(resource);
		
		assertNotNull(lines);
		assertFalse(lines.isEmpty());
	}
	
	@Test(expected = DataReaderException.class)
	public void shouldExpectException() throws DataReaderException {
		Resource resource = new ClassPathResource("input");
		
		List<String> lines = shoppingCartFileReader.read(resource);
		
		assertNotNull(lines);
		assertFalse(lines.isEmpty());
	}
	
}
