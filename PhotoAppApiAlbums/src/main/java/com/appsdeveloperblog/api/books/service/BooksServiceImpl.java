package com.appsdeveloperblog.api.books.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appsdeveloperblog.api.books.data.Book;
import com.appsdeveloperblog.api.books.repository.BookRepository;

@Service
public class BooksServiceImpl implements BooksService {

	private BookRepository bookRepository;
	
	public BooksServiceImpl() {
	}

	@Autowired
	public BooksServiceImpl(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}

	@Override
	public List<Book> findByUser(String userID) {
		return bookRepository.findByUserID(userID);
	}

	@Override
	public Book getDummyBook() {
		return new Book(12345, "Vampire Diaries", "Petre Malan", "444");
	}

	@Override
	public String saveBook(final Book book) {
		if (book != null) {
			bookRepository.save(book);
			return "Added book with id : " + book.getId();
		} else {
			return "Cannot add empty object";
		}
	}

	@Override
	public List<Book> findAll() {
		List<Book> books = bookRepository.findAll();
		for (Book book : books) {
			if (book.getPrice() == null) {
				book.setPrice(10);
			}
			if (book.getQuantity() == null) {
				book.setQuantity(1);
			}
			book.setValue(book.getPrice() * book.getQuantity());
		}
		return books;
	}

	@Override
	public Optional<Book> getBookByID(final int id) {
		return bookRepository.findById(id);
	}

	@Override
	public String deleteBook(final int id) {
		bookRepository.deleteById(id);
		return "Book deleted with id : " + id;
	}

}
