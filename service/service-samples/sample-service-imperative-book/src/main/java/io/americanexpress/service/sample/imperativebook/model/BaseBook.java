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
package io.americanexpress.service.sample.imperativebook.model;

/**
 * BaseBook class is responsible for creating the base book.
 *
 * @author Francois Gutt
 */
public class BaseBook {

    /**
     * Title of the book.
     */
    private String title;

    /**
     * Author of the book.
     */
    private String author;

    /**
     * Year the book was published.
     */
    private int year;

    /**
     * Publisher of the book.
     */
    private String publisher;

    /**
     * Cost of the book.
     */
    private double cost;

    /**
     * Get the title of the book.
     *
     * @return the title of the book
     */
    public String getTitle() {
        return title;
    }

    /**
     * Set the title of the book.
     *
     * @param title the title of the book
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Get the author of the book.
     *
     * @return the author of the book
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Set the author of the book.
     *
     * @param author the author of the book
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * Get the year the book was published.
     *
     * @return the year the book was published
     */
    public int getYear() {
        return year;
    }

    /**
     * Set the year the book was published.
     *
     * @param year the year the book was published
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * Get the publisher of the book.
     *
     * @return the publisher of the book
     */
    public String getPublisher() {
        return publisher;
    }

    /**
     * Set the publisher of the book.
     *
     * @param publisher the publisher of the book
     */
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    /**
     * Get the cost of the book.
     *
     * @return the cost of the book
     */
    public double getCost() {
        return cost;
    }

    /**
     * Set the cost of the book.
     *
     * @param cost the cost of the book
     */
    public void setCost(double cost) {
        this.cost = cost;
    }
}
