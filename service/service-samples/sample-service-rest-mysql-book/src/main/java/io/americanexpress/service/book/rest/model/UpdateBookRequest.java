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
 * {@code UpdateBookRequest} Request given to update book resource.
 */
public class UpdateBookRequest extends BookRequest {

    /**
     * The default constructor.
     */
    public UpdateBookRequest(){}

    /**
     * Constructor takes in title as string & author as string.
     * @param title of the book.
     * @param author of the book.
     */
    public UpdateBookRequest(String title, String author) {
        super(title, author);
    }

    /**
     * Constructor takes in title as string, author as string, and createdBy as string.
     * @param title Title of the book.
     * @param author Author of the book.
     * @param createdBy When the BookEntity was created.
     */
    public UpdateBookRequest(String title, String author, String createdBy) {
        super(title, author, createdBy);
    }

}
