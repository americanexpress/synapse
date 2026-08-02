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
package io.americanexpress.api.sample.imperativebook.config;

/**
 * Configuration class for defining API endpoints related to books.
 * <p>
 * This class provides a centralized location for managing endpoint paths used in the application. It is designed to
 * prevent instantiation.
 *
 * @author Aziz Ali
 */
public class BookEndpoint {

    /**
     * The base endpoint for book-related API operations.
     */
    public static final String BOOKS_ENDPOINT = "/api/v1/books";

    private BookEndpoint() {
    }
}
