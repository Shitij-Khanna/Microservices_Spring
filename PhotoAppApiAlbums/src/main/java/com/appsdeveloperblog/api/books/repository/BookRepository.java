package com.appsdeveloperblog.api.books.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.appsdeveloperblog.api.books.data.Book;

public interface BookRepository extends MongoRepository<Book, Integer> {

	public List<Book> findByUserID(String userID);

}
