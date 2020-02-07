package com.microservice.bookservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.microservice.bookservice.model.Book;

public interface BookRepository extends MongoRepository<Book, Integer> {

}
