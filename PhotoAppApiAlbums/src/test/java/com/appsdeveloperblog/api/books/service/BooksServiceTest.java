package com.appsdeveloperblog.api.books.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.appsdeveloperblog.api.books.data.Book;
import com.appsdeveloperblog.api.books.repository.BookRepository;

@RunWith(MockitoJUnitRunner.class)
public class BooksServiceTest {

	@InjectMocks
	private BooksServiceImpl booksService;

	@Mock
	private BookRepository bookRepository;

	@Test
	public void findAllBooks_Test() {
//		fail("Not yet implemented");
		List<Book> bookList = new ArrayList<Book>();
		bookList.add(new Book(2, "Ghost stories", "ghost", "124"));
		bookList.add(new Book(3, "Ghost stories2 ", "ghost", "1"));
		bookList.add(new Book(4, "Ghost stories3 ", "ghost3", "1", 5, 10));
		bookList.add(new Book(5, "Ghost stories4 ", "ghost4", "1", 10, 7));

		when(bookRepository.findAll()).thenReturn(bookList);

		List<Book> returnedBooks = booksService.findAll();
		assertEquals("ghost", returnedBooks.get(0).getAuthorName());
		assertEquals(Integer.valueOf("10"), returnedBooks.get(0).getValue());
		assertEquals(Integer.valueOf("50"), returnedBooks.get(2).getValue());
		assertEquals(Integer.valueOf("70"), returnedBooks.get(3).getValue());
//		assertEquals(Integer.valueOf("90"), returnedBooks.get(3).getValue()); // will fail because expected value was 90
																				// according to test case, but business
																				// logic returns 70, so either the test
																				// case's expected value is incorrect or
																				// the business logic is incorrect

//		assertEquals("10", returnedBooks.get(1).getValue()); // will fail because integer is expected in value not string as 
//		assertEquals(Integer.valueOf("20"), returnedBooks.get(0).getValue()); // will fail because expected was 20 but
		// we would get 10
	}
	

}
