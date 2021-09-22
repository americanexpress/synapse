/*
 * Copyright 2020 American Express Travel Related Services Company, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package io.americanexpress.service.book.graphql.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import io.americanexpress.service.book.graphql.model.Book;

/**
 * {@code BookService} class services {@link Book} objects.
 * @author Paolo Claudio
 *
 */
@Service
public class BookService {

	/**
	 * For example purposes, we create a list of books in-memory. 
	 * In your real implementation, please consider using another data source.
	 */
	private static final List<Book> BOOKS = initialize();
	
	/**
	 * Initialize the sample books.
	 * @return the list of books
	 */
	private static final List<Book> initialize() {
		List<Book> books = new ArrayList<>();
		books.add(new Book(UUID.fromString("c2ab9a0c-e5d8-4271-a377-a23250ee3a9e"), "Alice's Allegories", "Alice Doe"));
		books.add(new Book(UUID.fromString("d8fad5e7-d7d4-44ed-8758-2bad187a75ce"), "Bob's Biography", "Bob Doe"));
		books.add(new Book(UUID.fromString("2b0c31de-2258-442a-8b93-20f8358ff0a7"), "Catie's Comics", "Catie Doe"));
		books.add(new Book(UUID.fromString("36d779f8-6c38-441f-b564-8313925a4bc1"), "David's Dictionary", "David Doe"));
		books.add(new Book(UUID.fromString("5d910000-6e39-496b-9004-ab7df33e6323"), "Emma's Encyclopedia", "Emma Doe"));
		return books;
	}
	
	/**
	 * Get all of the books.
	 * @return the books
	 */
	public List<Book> getAll() {
		
		// For example purposes, we sort the sample in-memory books
		// in alphabetical order by ID.
		// In your real implementation, please consider
		// performing this operation in the database.
		return BOOKS.stream()
			.sorted((book1, book2) -> book1.getId().compareTo(book2.getId()))
			.collect(Collectors.toList());
	}
	
	/**
	 * Get the book.
	 * @param id of the book
	 * @return the book if found by its ID; null otherwise
	 */
	public Book get(UUID id) {
		return BOOKS.stream()
			.filter(book -> book.getId().equals(id))
			.findFirst()
			.orElse(null);
	}
	
	/**
	 * Get all books after this given book's ID.
	 * @param after id of this book
	 * @return the books after this given book's ID
	 */
	public List<Book> getAllAfter(UUID after) {
		
		// For example purposes, since the sample in-memory books
		// are in sorted alphabetical order by ID,
		// we get the elements after this given ID.
		// In your real implementation, please consider
		// performing this operation in the database.
		return getAll().stream()
			.filter(book -> book.getId().compareTo(after) > 0)
			.collect(Collectors.toList());
	}
	
	/**
	 * Create a new book.
	 * @param book to be added
	 * @return the new book
	 */
	public Book create(Book book) {
		
		// For example purposes, since the sample in-memory books
		// have a randomly generated ID,
		// we will create one here.
		// In your real implementation, please consider
		// performing this operation in the database.
		book.setId(UUID.randomUUID());
		BOOKS.add(book);
		return book;
	}
	
	/**
	 * Update an existing book.
	 * @param id of the book to be updated
	 * @param book to be updated
	 * @return the updated book
	 */
	public Book update(UUID id, Book book) {
		
		// For example purposes, we will update the book in-memory.
		// In your real implementation, please consider
		// performing this operation in the database.
		Book existingBook = get(id);
		if(existingBook != null) {
			existingBook.setTitle(book.getTitle());
			existingBook.setAuthor(book.getAuthor());
		}
		
		return existingBook;
	}
}
