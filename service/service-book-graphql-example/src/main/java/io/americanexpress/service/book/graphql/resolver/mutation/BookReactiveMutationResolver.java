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

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import graphql.kickstart.tools.GraphQLMutationResolver;
import io.americanexpress.service.book.graphql.model.Book;
import io.americanexpress.service.book.graphql.service.BookService;
import io.americanexpress.synapse.service.graphql.model.ReactiveResponseCreator;

/**
 * {@code BookReactiveMutationResolver} class resolves the GraphQL mutations for books
 * defined in {@code bookReactiveMutation.graphqls}.
 * <p>
 * Note: for example purposes, we have added the word "Reactively" to distinguish the reactive mutations
 * from the non-reactive mutations, but you do not need add this word in your implementation.
 * <p>
 * To see the non-reactive version of this class, please refer to {@link BookMutationResolver}.
 * @author Paolo Claudio
 *
 */
@Component
public class BookReactiveMutationResolver implements GraphQLMutationResolver {

	/**
	 * Used to create, update and delete the books.
	 */
	private final BookService bookService;
	
	/**
	 * Argument constructor creates a new instance of BookReactiveMutationResolver with given values.
	 * @param bookService used to create, update and delete the books
	 */
	@Autowired
	public BookReactiveMutationResolver(BookService bookService) {
		this.bookService = bookService;
	}
	
	/**
	 * Create a book.
	 * @param book to be created
	 * @return the created book
	 */
	public CompletableFuture<Book> createBookReactively(Book book) {
		return ReactiveResponseCreator.create(bookService.create(book));
	}
	
	/**
	 * Update an existing book.
	 * @param id of the book
	 * @param book to be updated
	 * @return the updated book if found; null otherwise
	 */
	public CompletableFuture<Book> updateBookReactively(UUID id, Book book) {
		return ReactiveResponseCreator.create(bookService.update(id, book));
	}
	
	/**
	 * Delete an existing book.
	 * @param id of the book
	 * @return the deleted book if found; null otherwise
	 */
	public CompletableFuture<Book> deleteBookReactively(UUID id) {
		return ReactiveResponseCreator.create(bookService.delete(id));
	}
}
