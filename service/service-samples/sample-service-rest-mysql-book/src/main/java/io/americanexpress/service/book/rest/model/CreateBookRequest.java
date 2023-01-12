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
package io.americanexpress.service.book.rest.model;

/**
 * {@code CreateBookRequest} Child of BookRequest that will be used for creating new book records.
 */
public class CreateBookRequest extends BookRequest {

    /**
     * {@link CreateBookRequest} default constructor.
     */
    public CreateBookRequest(){}

    /**
     * {@link CreateBookRequest} overloaded constructor.
     * @param title the title
     * @param author the author
     */
    public CreateBookRequest(String title, String author) {
        super(title, author);
    }
}
