package com.appsdeveloperblog.api.books.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.appsdeveloperblog.api.books.data.Book;
import com.appsdeveloperblog.api.books.repository.BookRepository;
import com.appsdeveloperblog.api.books.repository.Books_RedisRepositoryImpl;

@Service
public class BooksServiceImpl implements BooksService {

	private BookRepository bookRepository;

	@Autowired
	private Books_RedisRepositoryImpl redisRepository;

	public BooksServiceImpl() {
	}

	@Autowired
	public BooksServiceImpl(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}

	@Override
	@Cacheable("bookForUser")
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
			Book addedBook = bookRepository.save(book);
			// update the book into Redis cache
			redisRepository.saveBook(addedBook);
			return "Added book with id : " + book.getId();
		} else {
			return "Cannot add empty object";
		}
	}

	@Override
	public List<Book> findAll() {
		Map<String, Book> booksFromCache = redisRepository.findAll();
		List<Book> books = booksFromCache.values().stream().collect(Collectors.toList());
		if (!books.isEmpty()) {
			bookRepository.findAll();
			// update the newly found list in redis cache
		}
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
		Optional<Book> book = redisRepository.getBookByID(id);
		if (book.isPresent()) {
			return book;
		} else {
			return bookRepository.findById(id);
		}
	}

	@Override
	public String deleteBook(final int id) {
		bookRepository.deleteById(id);
		redisRepository.deleteBook(id);
		return "Book deleted with id : " + id;
	}

}
