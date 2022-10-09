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
package io.americanexpress.function.greeting.reactive.model;

import io.americanexpress.synapse.function.reactive.model.BasePaginatedServiceRequest;

import java.util.Objects;

/**
 * ReadBookRequest is the model used on the request of the Read Poly Book Controller.
 */
public class ReadPolyBookRequest extends BasePaginatedServiceRequest {

    private String title;
    private String author;

    /**
     * Instantiates a new Read book request.
     */
    public ReadPolyBookRequest() {
    }

    /**
     * Instantiates a new Read book request.
     *
     * @param title  the title
     * @param author the author
     */
    public ReadPolyBookRequest(String title, String author) {
        this.title = title;
        this.author = author;
    }


    /**
     * Gets title.
     *
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets title.
     *
     * @param title the title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets author.
     *
     * @return the author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Sets author.
     *
     * @param author the author
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReadPolyBookRequest that = (ReadPolyBookRequest) o;
        return title.equals(that.title) && author.equals(that.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, author);
    }

    @Override
    public String toString() {
        return "ReadBookRequest{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                '}';
    }


}
