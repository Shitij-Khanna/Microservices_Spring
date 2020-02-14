package com.microservice.users;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.microservice.users.ui.model.Book;

@FeignClient(name="books-ws" )//Service Id of user books service
public interface UserBooksFeignClient {

	@RequestMapping("/users/books/test")
	   public String test();
	
	@RequestMapping("users/books/dummyBook")
	public Book getDummyBook();
	
	@RequestMapping(value = "users/books/addBook", method = RequestMethod.POST)
	public String saveBook(@RequestBody final Book album);
}
