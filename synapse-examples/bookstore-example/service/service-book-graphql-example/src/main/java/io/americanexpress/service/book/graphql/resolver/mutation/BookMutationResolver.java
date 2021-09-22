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
package io.americanexpress.service.book.graphql.resolver.mutation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import graphql.kickstart.tools.GraphQLMutationResolver;
import io.americanexpress.service.book.graphql.model.Book;
import io.americanexpress.service.book.graphql.service.BookService;

/**
 * {@code BookMutationResolver} class resolves the GraphQL mutations for books
 * defined in {@code bookMutation.graphqls}.
 * <p>
 * To see the reactive version of this class, please refer to {@link }.
 * @author Paolo Claudio
 *
 */
@Component
public class BookMutationResolver implements GraphQLMutationResolver {

	/**
	 * Used to create, update and delete the books.
	 */
	private final BookService bookService;
	
	/**
	 * Argument constructor creates a new instance of BookMutationResolver with given values.
	 * @param bookService used to create, update and delete the books
	 */
	@Autowired
	public BookMutationResolver(BookService bookService) {
		this.bookService = bookService;
	}
	
	/**
	 * Create a book.
	 * @param book to be created
	 * @return the created book
	 */
	public Book createBook(Book book) {
		return bookService.add(book);
	}
}
