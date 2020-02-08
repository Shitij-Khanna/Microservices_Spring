package com.appsdeveloperblog.api.books.io.controllers;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.MediaType;

import org.assertj.core.util.Arrays;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.appsdeveloperblog.api.books.data.Book;
import com.appsdeveloperblog.api.books.service.BooksService;

@RunWith(SpringRunner.class)
@WebMvcTest(BookController.class)
public class BooksControllerTest {

	@Autowired
	private MockMvc mockMVC;

	@MockBean
	private BooksService bookService;

	@Test
	public void getStringForTest_Basic() throws Exception {
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/users/books/test")
				.accept(MediaType.APPLICATION_JSON);
		// call "/test"
		MvcResult result = mockMVC.perform(request).andExpect(status().isOk()).andExpect(content().string("Hello Test"))
				.andReturn();
		assertEquals("Hello Test", result.getResponse().getContentAsString());
	}

	@Test
	public void testDummyBook() throws Exception {
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/users/books/dummyBook")
				.accept(MediaType.APPLICATION_JSON);
		// call "/test"
		MvcResult result = mockMVC.perform(request).andExpect(status().isOk())
				.andExpect(
						content().json("{\r\n" + "    \"id\": 12345,\r\n" + "    \"bookName\": \"Vampire Diaries\",\r\n"
								+ "    \"authorName\": \"Petre Malan\",\r\n" + "    \"userID\": \"444\"\r\n" + "}"))
				.andReturn();
		// assertEquals("Hello Test", result.getResponse().getContentAsString());
	}

	@Test
	public void testDummyBookFromService() throws Exception {

		when(bookService.getDummyBook()).thenReturn(new Book(12345, "Vampire Diaries", "Petre Malan", "444"));

		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/users/books/dummyBookFromService")
				.accept(MediaType.APPLICATION_JSON);
		// call "/test"
		MvcResult result = mockMVC.perform(request).andExpect(status().isOk())
				.andExpect(
						content().json("{\r\n" + "    \"id\": 12345,\r\n" + "    \"bookName\": \"Vampire Diaries\",\r\n"
								+ "    \"authorName\": \"Petre Malan\",\r\n" + "    \"userID\": \"444\"\r\n" + "}"))
				.andReturn();
		// assertEquals("Hello Test", result.getResponse().getContentAsString());
	}

	@Test
	public void testRetrieveAllItems() throws Exception {
		List<Book> bookList = new ArrayList<Book>();
		bookList.add(new Book(2, "Ghost stories", "ghost", "124"));
		bookList.add(new Book(3, "Ghost stories2 ", "ghost", "1"));
		when(bookService.findAll()).thenReturn(bookList);

		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/users/books/findAllBooks")
				.accept(MediaType.APPLICATION_JSON);
		// call "/test"
		MvcResult result = mockMVC.perform(request).andExpect(status().isOk())
				.andExpect(content().json("[\r\n" + "    {\r\n" + "        \"id\": 2,\r\n"
						+ "        \"bookName\": \"Ghost stories\",\r\n" + "        \"authorName\": \"ghost\",\r\n"
						+ "        \"userID\": \"124\"\r\n" + "    },\r\n" + "    {\r\n" + "        \"id\": 3,\r\n"
						+ "        \"bookName\": \"Ghost stories2 \",\r\n" + "        \"authorName\": \"ghost\",\r\n"
						+ "        \"userID\": \"1\"\r\n" + "    }\r\n" + "]"))
				.andReturn();
		// assertEquals("Hello Test", result.getResponse().getContentAsString());
	}

}
