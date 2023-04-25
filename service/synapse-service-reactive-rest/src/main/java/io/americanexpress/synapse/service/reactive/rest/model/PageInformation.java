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
package io.americanexpress.synapse.service.reactive.rest.model;

/**
 * {@code PageInformation} class specifies the parameters for a service request,
 * limiting the results to a subset of how many (size) and on which page (page).
 *
 */
public class PageInformation {

    /**
     * The page requested of the results.
     */
    private int page;

    /**
     * The number of results per page.
     */
    private int size;

    /**
     * Get the page.
     *
     * @return the page
     */
    public int getPage() {
        return page;
    }

    /**
     * Set the page.
     *
     * @param page the page to set
     */
    public void setPage(int page) {
        this.page = page;
    }

    /**
     * Get the size.
     *
     * @return the size
     */
    public int getSize() {
        return size;
    }

    /**
     * Set the size.
     *
     * @param size the size to set
     */
    public void setSize(int size) {
        this.size = size;
    }
}
