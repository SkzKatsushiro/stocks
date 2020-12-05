package bg.delyan.stock.test.controller;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@SpringBootTest
@AutoConfigureMockMvc
public class TestStocksController {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testPut() throws Exception {
		mockMvc.perform(put("/stocks/" + "IBM" + "/value/" + 10)).andExpect(status().isCreated());
	}

	@Test
	public void testGet() throws Exception {
		mockMvc.perform(put("/stocks/" + "IBM" + "/value/" + 10)).andExpect(status().isCreated());
		String testdate = LocalDateTime.now().toString();
		MvcResult result = mockMvc.perform(get("/stocks/" + "IBM" + "/at-time/" + testdate)).andExpect(status().isOk())
				.andReturn();

		String content = result.getResponse().getContentAsString();
		assertTrue(content.equals("10"));
	}

	@Test
	public void testGetWithSeveralEntriesForOneCompanyInsertAnd() throws Exception {
		mockMvc.perform(put("/stocks/" + "IBM" + "/value/" + 10)).andExpect(status().isCreated());
		mockMvc.perform(put("/stocks/" + "IBM" + "/value/" + 11)).andExpect(status().isCreated());
		mockMvc.perform(put("/stocks/" + "IBM" + "/value/" + 12)).andExpect(status().isCreated());
		mockMvc.perform(put("/stocks/" + "IBM" + "/value/" + 13)).andExpect(status().isCreated());

		String testdate = LocalDateTime.now().toString();
		MvcResult result = mockMvc.perform(get("/stocks/" + "IBM" + "/at-time/" + testdate)).andExpect(status().isOk())
				.andReturn();

		String content = result.getResponse().getContentAsString();
		assertTrue(content.equals("13"));
	}

	@Test
	public void testGetForTheMiddleValue() throws Exception {
		mockMvc.perform(put("/stocks/" + "IBM" + "/value/" + 10)).andExpect(status().isCreated());
		mockMvc.perform(put("/stocks/" + "IBM" + "/value/" + 11)).andExpect(status().isCreated());

		Thread.sleep(10000); // 10 sec
		String testdate = LocalDateTime.now().toString();
		Thread.sleep(1000); // 1sec

		mockMvc.perform(put("/stocks/" + "IBM" + "/value/" + 12)).andExpect(status().isCreated());
		mockMvc.perform(put("/stocks/" + "IBM" + "/value/" + 13)).andExpect(status().isCreated());

		MvcResult result = mockMvc.perform(get("/stocks/" + "IBM" + "/at-time/" + testdate)).andExpect(status().isOk())
				.andReturn();

		String content = result.getResponse().getContentAsString();
		assertTrue(content.equals("11"));
	}

	@Test
	public void testGetWithSeveralDifferentCompaniesAnd() throws Exception {
		mockMvc.perform(put("/stocks/" + "IBM" + "/value/" + 10)).andExpect(status().isCreated());
		mockMvc.perform(put("/stocks/" + "USS" + "/value/" + 11)).andExpect(status().isCreated());
		mockMvc.perform(put("/stocks/" + "RSS" + "/value/" + 12)).andExpect(status().isCreated());
		mockMvc.perform(put("/stocks/" + "TSS" + "/value/" + 13)).andExpect(status().isCreated());

		String testdate = LocalDateTime.now().toString();
		
		MvcResult result = mockMvc.perform(get("/stocks/" + "IBM" + "/at-time/" + testdate)).andExpect(status().isOk())
				.andReturn();
		String content = result.getResponse().getContentAsString();
		assertTrue(content.equals("10"));

		result = mockMvc.perform(get("/stocks/" + "USS" + "/at-time/" + testdate)).andExpect(status().isOk())
				.andReturn();
		content = result.getResponse().getContentAsString();
		assertTrue(content.equals("11"));
		
		result = mockMvc.perform(get("/stocks/" + "RSS" + "/at-time/" + testdate)).andExpect(status().isOk())
				.andReturn();
		content = result.getResponse().getContentAsString();
		assertTrue(content.equals("12"));
		
		result = mockMvc.perform(get("/stocks/" + "TSS" + "/at-time/" + testdate)).andExpect(status().isOk())
				.andReturn();
		content = result.getResponse().getContentAsString();
		assertTrue(content.equals("13"));
	}
}
