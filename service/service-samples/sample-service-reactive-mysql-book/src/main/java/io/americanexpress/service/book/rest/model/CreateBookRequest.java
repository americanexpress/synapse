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
     * Constructor taking in title as string and author as string.
     * @param title string value of title of book.
     * @param author string value of author of book.
     */
    public CreateBookRequest(String title, String author) {
        super(title, author);
    }

    /**
     * The default constructor.
     */
    public CreateBookRequest(){}
}
