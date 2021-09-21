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
package io.americanexpress.service.book.graphql;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import io.americanexpress.service.book.graphql.model.Book;

/**
 * {@code BookService} class interacts with {@link Book} objects.
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
	private static List<Book> initialize() {
		List<Book> books = new ArrayList<>();
		books.add(new Book("John's Story", "John Doe"));
		books.add(new Book("Jane's Story", "Jane Doe"));
		return books;
	}
	
	/**
	 * Get all of the books.
	 * @return the books
	 */
	public List<Book> getAll() {
		return BOOKS;
	}
}
