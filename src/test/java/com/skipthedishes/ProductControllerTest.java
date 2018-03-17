package com.skipthedishes;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.skipthedishes.builders.CousineBuilder;
import com.skipthedishes.builders.ProductBuilder;
import com.skipthedishes.builders.StoreBuilder;
import com.skipthedishes.entity.Cousine;
import com.skipthedishes.entity.Product;
import com.skipthedishes.entity.Store;

@RunWith(SpringRunner.class)
@SpringBootTest(
		webEnvironment = WebEnvironment.RANDOM_PORT,
		classes = MainApplication.class)
@AutoConfigureMockMvc
public class ProductControllerTest {

	private static String URI = "/products/";
	
	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Test
	public void shouldCreate() throws Exception {
		String json = objectMapper.writeValueAsString(buildProduct());
		mvc.perform(post(URI)
			.contentType(MediaType.APPLICATION_JSON)
			.content(json)
			.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk());
		
	}

	private Product buildProduct() {
		return new ProductBuilder()
				.withName("Pizza")
				.withDescription("The best pizza in the world")
				.withPrice(new BigDecimal(18.99))
				.withStore(buildStore())
				.build();
	}

	private Store buildStore() {
		return new StoreBuilder()
				.withId(1L)
				.withName("Super Pizza Store")
				.withAddress("123 Convent Garden St")
				.withCousine(buildCousine())
				.build();
	}
	
	private Cousine buildCousine() {
		return new CousineBuilder()
				.withId(1L)
				.withName("Super Pizza Store")
				.build();
	}
}
