package com.appsdeveloperblog.api.books.io.controllers;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.json.JSONException;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.appsdeveloperblog.api.books.data.Book;
import com.appsdeveloperblog.api.books.repository.BookRepository;
import com.appsdeveloperblog.api.books.service.BooksService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class BooksControllerIntegrationTest {

	@Autowired
	private TestRestTemplate restTemplate;

	@MockBean
	private BookRepository bookRepository;

	@MockBean
	private BooksService bookService;

//	@Test
	public void findAllBooksTest() throws JSONException {

		List<Book> bookList = new ArrayList<Book>();
		bookList.add(new Book(2, "Ghost stories", "ghost", "124"));
		bookList.add(new Book(3, "Ghost stories2 ", "ghost", "1"));
		bookList.add(new Book(4, "Ghost stories3 ", "ghost3", "1", 5, 10));
		bookList.add(new Book(5, "Ghost stories4 ", "ghost4", "1", 10, 7));

		when(bookRepository.findAll()).thenReturn(bookList);

		String response = this.restTemplate.getForObject("/users/books/findAllBooks", String.class);
		JSONAssert.assertEquals("[{id:2},{id:3},{id:4},{id:5}]", response, false);

		//verify what URL was used
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        Mockito.verify(restTemplate).getForObject(captor.capture(), Mockito.any(), (Object) Mockito.any());
		// strict is false, means all attributes of json wont be compared
	}

//	@Test
	public void findBookByIDTest() throws JSONException {

		for (int i = 0; i < 4; i++) {
			int randomNo = i;
			when(bookRepository.findById(randomNo))
					.thenReturn(Optional.of(new Book(randomNo, "Ghost stories " + randomNo, "ghost", "1")));
			String response = this.restTemplate.getForObject("/users/books/findAllBooks/" + randomNo, String.class);
			JSONAssert.assertEquals("{id:" + randomNo + "}", response, false);

		}

//		when(bookRepository.findById(anyInt())).thenReturn(Optional.of(new Book(2, "Ghost stories2 ", "ghost", "1")));
//		String response = this.restTemplate.getForObject("/users/books/findAllBooks/10", String.class);
//		JSONAssert.assertEquals("{id:2}", response, false);
		// strict is false, means all attributes of json wont be compared
	}
	
}
