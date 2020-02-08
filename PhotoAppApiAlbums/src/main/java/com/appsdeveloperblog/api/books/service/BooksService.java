package com.appsdeveloperblog.api.books.service;

import java.util.List;
import java.util.Optional;

import com.appsdeveloperblog.api.books.data.Book;

public interface BooksService {

	public List<Book> findByUser(String userID);

	public Book getDummyBook();

	public String saveBook(Book book);

	public List<Book> findAll();

	public Optional<Book> getBookByID(int id);

	public String deleteBook(int id);

}
