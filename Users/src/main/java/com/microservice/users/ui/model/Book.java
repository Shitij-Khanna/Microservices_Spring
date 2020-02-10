package com.microservice.users.ui.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;

public class Book {

	@Id
	private int id;

	private String bookName;

	private String authorName;

	private String userID;
	
	private Integer price; 

	private Integer quantity;
	
	@Transient
	private Integer value;
	
	public Book() {
		
	}
	
	public Book(int id, String bookName, String authorName, String userID) {
		this.id = id;
		this.bookName = bookName;
		this.authorName = authorName;
		this.userID = userID;
	}
	
	public Book(int id, String bookName, String authorName, String userID, Integer price, Integer quantity) {
		this.id = id;
		this.bookName = bookName;
		this.authorName = authorName;
		this.userID = userID;
		this.price = price;
		this.quantity = quantity;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}
}
