package com.microservice.bookservice.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.bookservice.model.Book;
import com.microservice.bookservice.repository.BookRepository;

@RestController
public class BookResource {

	@Autowired
	private BookRepository repository;

	@RequestMapping(value = "/addBook", method = RequestMethod.POST)
	public String saveBook(@RequestBody final Book book) {
		repository.save(book);
		return "Added book with id : " + book.getId();
	}
	
	@GetMapping("/test")
	public String getStringForTest() {
		return "Hello Test";
	}

	@GetMapping("/findAllBooks")
	public List<Book> getBooks() {
		return repository.findAll();
	}

	@GetMapping("/findAllBooks/{id}")
	public Optional<Book> getBookByID(@PathVariable final int id) {
		return repository.findById(id);
	}

	@DeleteMapping("/delete/{id}")
	public String deleteBook(@PathVariable final int id) {
		repository.deleteById(id);
		return "Book deleted with id : " + id;
	}
}
