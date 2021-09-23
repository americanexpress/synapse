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
package io.americanexpress.service.book.graphql.resolver.query;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import graphql.kickstart.tools.GraphQLQueryResolver;
import graphql.relay.Connection;
import io.americanexpress.service.book.graphql.model.Book;
import io.americanexpress.service.book.graphql.service.BookService;
import io.americanexpress.synapse.service.graphql.model.Pageable;
import io.americanexpress.synapse.service.graphql.pagination.ConnectionUtil;
import io.americanexpress.synapse.service.graphql.pagination.UUIDUtil;

/**
 * {@code BookQueryResolver} class resolves the GraphQL queries for books
 * defined in {@code bookQuery.graphqls}.
 * <p>
 * To see the reactive version of this class, please refer to {@link BookReactiveQueryResolver}.
 * @author Paolo Claudio
 *
 */
@Component
public class BookQueryResolver implements GraphQLQueryResolver, Pageable<Book> {

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
	 * @return the book if found; null otherwise
	 */
	public Book getBook(UUID id) {
		return bookService.get(id);
	}
	
	/**
	 * Get the {@code first} number of paginated elements
	 * {@code after} this opaque cursor.
	 * @param first number of elements
	 * @param after the opaque cursor
	 * @return the paginated elements
	 */
	@Override
	public Connection<Book> getPaginatedElements(int first, String after) {
		List<Book> books = bookService.getAll();
		
		// For example purposes, we filter the books after this opaque cursor in-memory
		// but in your own implementation, please consider filtering at the database
		if(after != null && !after.isBlank()) {
			books = bookService.getAllAfter(UUIDUtil.toUUID(after));
		}
		
		return ConnectionUtil.create(books, first, after);
	}
}
