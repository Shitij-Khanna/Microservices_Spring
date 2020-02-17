package com.appsdeveloperblog.api.books.repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.appsdeveloperblog.api.books.data.Book;

/**
 * @author nlshikha Docker command to pull and run redis docker run -it -p
 *         6379:6379 redis
 */
@Repository
public class Books_RedisRepositoryImpl implements Books_RedisRepository {

	private RedisTemplate<String, Book> redisTemplate;
	private HashOperations hashOperations;

	public Books_RedisRepositoryImpl(RedisTemplate<String, Book> redisTemplate) {
		this.redisTemplate = redisTemplate;
		hashOperations = redisTemplate.opsForHash();
	}

	private static final String KEY_BOOKS_CACHE = "BOOKS";

	@Override
	public String saveBook(final Book book) {
		hashOperations.put(KEY_BOOKS_CACHE, book.getId(), book);
		return "Added book to Redis cache : " + String.valueOf(book.getId());
	}

	@Override
	public Map<String, Book> findAll() {
		return hashOperations.entries(KEY_BOOKS_CACHE);
	}

	@Override
	public Optional<Book> getBookByID(int id) {
		Book book = (Book) hashOperations.get(KEY_BOOKS_CACHE, id);
		return Optional.ofNullable(book);
	}

	@Override
	public String deleteBook(int id) {
		hashOperations.delete(KEY_BOOKS_CACHE, id);
		return "deleted : " + id + "from :" + KEY_BOOKS_CACHE;
	}

}
