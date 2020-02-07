package com.appsdeveloperblog.photoapp.api.albums.repository;



import org.springframework.data.mongodb.repository.MongoRepository;

import com.appsdeveloperblog.photoapp.api.albums.ui.model.Book;

public interface BookRepository extends MongoRepository<Book, Integer> {

}
