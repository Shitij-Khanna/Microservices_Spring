
package com.appsdeveloperblog.api.books.io.controllers;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.appsdeveloperblog.api.books.data.Book;
import com.appsdeveloperblog.api.books.service.BooksService;

@RestController
@RequestMapping("/users/books")
public class BookController {

//	@Autowired
//	AlbumsService albumsService;

	@Autowired
	BooksService bookService;

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@GetMapping("/test")
	public String getStringForTest() {
		return "Hello Test";
	}

	@GetMapping(value = "/dummyBook", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public Book getDummyBook() {
		return new Book(12345, "Vampire Diaries", "Petre Malan", "444");
	}

	@GetMapping(value = "/dummyBookFromService", produces = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE })
	public Book getDummyBookFromService() {
		Book book = bookService.getDummyBook();
		return book;
	}

	@RequestMapping(value = "/addBook", method = RequestMethod.POST)
	public String saveBook(@RequestBody final Book album) {
		return bookService.saveBook(album);
	}

//
	@GetMapping("/findAllBooks")
	public List<Book> getBooks() {
		return bookService.findAll();
	}

	@GetMapping("/findBooksByUser/{userID}")
	public List<Book> getBooksByUser(@PathVariable final String userID) {
		return bookService.findByUser(userID);
	}

	@GetMapping("/findAllBooks/{id}")
	public Optional<Book> getBookByID(@PathVariable final int id) {
		return bookService.getBookByID(id);
	}

//
	@DeleteMapping("/delete/{id}")
	public String deleteBook(@PathVariable final int id) {
		bookService.deleteBook(id);
		return "Book deleted with id : " + id;
	}

//	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, })
//	public List<AlbumResponseModel> userAlbums(@PathVariable String id) {
//
//		List<AlbumResponseModel> returnValue = new ArrayList<>();
//
//		List<AlbumEntity> albumsEntities = albumsService.getAlbums(id);
//
//		if (albumsEntities == null || albumsEntities.isEmpty()) {
//			return returnValue;
//		}
//
//		Type listType = new TypeToken<List<AlbumResponseModel>>() {
//		}.getType();
//
//		returnValue = new ModelMapper().map(albumsEntities, listType);
//		logger.info("Returning " + returnValue.size() + " albums");
//		return returnValue;
//	}
}
