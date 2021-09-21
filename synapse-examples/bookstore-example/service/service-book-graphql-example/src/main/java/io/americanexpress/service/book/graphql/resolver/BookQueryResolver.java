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
package io.americanexpress.service.book.graphql.resolver;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;

import io.americanexpress.service.book.graphql.BookService;
import io.americanexpress.service.book.graphql.model.Book;

/**
 * {@code BookQueryResolver} class resolves the GraphQL queries for books
 * defined in {@code book.graphqls}.
 * @author Paolo Claudio
 *
 */
@Component
public class BookQueryResolver implements GraphQLQueryResolver {

	/**
	 * Used to get the books.
	 */
	private final BookService bookService;
	
	/**
	 * Argument constructor creates a new instance of BookQueryResolver with given values.
	 * @param bookService used to get the books
	 */
	@Autowired
	public BookQueryResolver(BookService bookService) {
		this.bookService = bookService;
	}
	
	/**
	 * Get the books.
	 * @return the books
	 */
	public List<Book> getBooks() {
		return bookService.getAll();
	}
	
	/**
	 * Get the book.
	 * @param id of the book
	 * @return the book if found by its ID; null otherwise
	 */
	public Book getBook(String id) {
		return bookService.get(id);
	}
}
