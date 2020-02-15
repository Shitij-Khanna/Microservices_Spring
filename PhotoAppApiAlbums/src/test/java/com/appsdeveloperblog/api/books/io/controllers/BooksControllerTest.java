package com.appsdeveloperblog.api.books.io.controllers;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.endsWith;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.MediaType;

import org.aopalliance.intercept.Joinpoint;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.JsonPathResultMatchers;

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
	public void testDummyBookFromService() throws Exception {
		Book bookRO = new Book(12345, "Vampire Diaries", "Petre Malan", "444");
		when(bookService.getDummyBook()).thenReturn(bookRO);

		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/users/books/dummyBookFromService")
				.accept(MediaType.APPLICATION_JSON);
		// call "/test"
		MvcResult result = mockMVC.perform(request).andExpect(status().isOk())
				.andExpect(jsonPath("$.id", Matchers.is(bookRO.getId())))
				.andExpect(jsonPath("$.bookName", Matchers.is(bookRO.getBookName())))
				.andExpect(jsonPath("$.authorName", Matchers.is(bookRO.getAuthorName())))
				.andExpect(jsonPath("$.userID", Matchers.is(bookRO.getUserID()))).andReturn();

		Mockito.verify(bookService, Mockito.times(1)).getDummyBook();

	}

	@Test
	public void testDummyBookUsingJsonPath() throws Exception {

		Book bookRO = new Book(12345, "Vampire Diaries", "Petre Malan", "444");
		when(bookService.getDummyBook()).thenReturn(bookRO);

		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/users/books/dummyBookFromService")
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMVC.perform(request).andExpect(status().isOk())
				.andExpect(jsonPath("$.id", Matchers.is(bookRO.getId())))
				.andExpect(jsonPath("$.bookName", Matchers.is(bookRO.getBookName())))
				.andExpect(jsonPath("$.authorName", Matchers.is(bookRO.getAuthorName())))
				.andExpect(jsonPath("$.userID", Matchers.is(bookRO.getUserID()))).andReturn();

		Mockito.verify(bookService, Mockito.times(1)).getDummyBook();
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
		JsonPathResultMatchers a = jsonPath("$[?(@.id)]");
		System.out.println(a);
		mockMVC.perform(request).andExpect(status().isOk())
		 .andExpect(jsonPath("$", hasSize(2)))
		 .andExpect(jsonPath("$[?(@.id)]", greaterThan(0)));
		
	}

	@Test
	public void hamcrestMatchersTest() {
		List<Integer> list = new ArrayList<Integer>();
		list.add(10);
		list.add(26);
		list.add(34);
		list.add(49);
		org.hamcrest.MatcherAssert.assertThat(list, hasSize(4));
		org.hamcrest.MatcherAssert.assertThat(list, hasItem(26));
		org.hamcrest.MatcherAssert.assertThat(list,
				org.hamcrest.Matchers.everyItem(greaterThan(9)));
		org.hamcrest.MatcherAssert.assertThat(list,
				org.hamcrest.Matchers.everyItem(lessThan(70)));

		org.hamcrest.MatcherAssert.assertThat("Shitij", startsWith("Shit"));

		org.hamcrest.MatcherAssert.assertThat("Shitij", endsWith("ij"));

		org.hamcrest.MatcherAssert.assertThat("Shitij", containsString("Shit"));

		org.hamcrest.MatcherAssert.assertThat("Shitij", notNullValue());

		org.hamcrest.MatcherAssert.assertThat("Shitij", notNullValue());

	}

	@Test
	public void assertJTest() {
		List<Integer> list = new ArrayList<Integer>();
		list.add(10);
		list.add(26);
		list.add(34);
		list.add(49);

		org.assertj.core.api.Assertions.assertThat(list).hasSize(4).contains(10, 26).allMatch(num -> num > 9)
				.allMatch(x -> x < 100).noneMatch(a -> a < 0);

		org.assertj.core.api.Assertions.assertThat("Shitij").isNotEmpty().contains("iti").startsWith("Shi")
				.endsWith("tij").doesNotContain("Moti");

	}
	
}
