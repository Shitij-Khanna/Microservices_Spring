/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appsdeveloperblog.photoapp.api.albums.io.controllers;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
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

import com.appsdeveloperblog.photoapp.api.albums.data.AlbumEntity;
import com.appsdeveloperblog.photoapp.api.albums.repository.BookRepository;
import com.appsdeveloperblog.photoapp.api.albums.service.AlbumsService;
import com.appsdeveloperblog.photoapp.api.albums.ui.model.AlbumResponseModel;
import com.appsdeveloperblog.photoapp.api.albums.ui.model.Book;

@RestController
@RequestMapping("/users/books")
public class BookController {

	@Autowired
	AlbumsService albumsService;

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	BookRepository bookRepository;

	@GetMapping("/test")
	public String getStringForTest() {
		return "Hello Test";
	}

	@RequestMapping(value = "/addBook", method = RequestMethod.POST)
	public String saveAlbum(@RequestBody final Book album) {
		bookRepository.save(album);
		return "Added book with id : " + album.getId();
	}

	@GetMapping("/findAllBooks")
	public List<Book> getBooks() {
		return bookRepository.findAll();
	}

	@GetMapping("/findAllBooks/{id}")
	public Optional<Book> getBookByID(@PathVariable final int id) {
		return bookRepository.findById(id);
	}

	@DeleteMapping("/delete/{id}")
	public String deleteBook(@PathVariable final int id) {
		bookRepository.deleteById(id);
		return "Book deleted with id : " + id;
	}

	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, })
	public List<AlbumResponseModel> userAlbums(@PathVariable String id) {

		List<AlbumResponseModel> returnValue = new ArrayList<>();

		List<AlbumEntity> albumsEntities = albumsService.getAlbums(id);

		if (albumsEntities == null || albumsEntities.isEmpty()) {
			return returnValue;
		}

		Type listType = new TypeToken<List<AlbumResponseModel>>() {
		}.getType();

		returnValue = new ModelMapper().map(albumsEntities, listType);
		logger.info("Returning " + returnValue.size() + " albums");
		return returnValue;
	}
}
