package com.appsdeveloperblog.api.books.repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.appsdeveloperblog.api.books.data.Book;

public interface Books_RedisRepository {

	public String saveBook(Book book);

	public Map<String, Book> findAll();
	
	public Optional<Book> getBookByID(final int id);
	
	public String deleteBook(final int id);
}
