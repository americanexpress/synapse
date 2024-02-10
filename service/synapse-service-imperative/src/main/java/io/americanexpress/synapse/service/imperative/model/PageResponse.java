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
package io.americanexpress.synapse.service.imperative.model;

import java.util.List;


/**
 * {@code PageResponse} class performs the paging calculation of the results from the service response,
 * capturing the pages and the results per page.
 *
 * @param <O> output type (response type)
 */
public class PageResponse<O extends BaseServiceResponse> {

    /**
     * The default number of results to display per page if none was provided.
     */
    private static final int DEFAULT_PAGE_SIZE = 10;

    /**
     * Collection received as a response from the service.
     */
    private List<O> responses;

    /**
     * Total number of results from the service.
     */
    private int totalResultsCount;

    /**
     * The number of results per page.
     */
    private int pageSize;

    /**
     * The page index.
     */
    private int page;

    /**
     * The starting range of the results.
     */
    private int startingIndex;

    /**
     * The ending range of the results.
     */
    private int endingIndex;

    /**
     * The maximum number of pages.
     */
    private int maxPages;

    /**
     * Argument constructor creates a new instance of PageResponse with given values.
     *
     * @param responses collection received as a response from the service
     */
    public PageResponse(List<O> responses) {
        this.responses = responses;
    }

    /**
     * Argument constructor creates a new instance of PageResponse with given values.
     *
     * @param responses       collection received as a response from the service
     * @param pageInformation containing the requested page and size from the request to the service
     */
    public PageResponse(List<O> responses, PageInformation pageInformation) {
        this.responses = responses;
        this.page = pageInformation.getPage();
        this.pageSize = pageInformation.getSize();
        if (pageSize < 0) {
            pageSize = DEFAULT_PAGE_SIZE;
        }
        this.totalResultsCount = responses.size();
        if (pageSize > this.totalResultsCount) {
            pageSize = this.totalResultsCount;
        }
        calculatePages();
        setPage(this.page);
        this.responses = responses.subList(this.startingIndex, this.endingIndex);
    }

    /**
     * Get the responses.
     *
     * @return the responses
     */
    public List<O> getResponses() {
        return responses;
    }

    /**
     * Set the responses.
     *
     * @param responses the responses to set
     */
    public void setResponses(List<O> responses) {
        this.responses = responses;
    }

    /**
     * Get the totalResultsCount.
     *
     * @return the totalResultsCount
     */
    public int getTotalResultsCount() {
        return totalResultsCount;
    }

    /**
     * Set the totalResultsCount.
     *
     * @param totalResultsCount the totalResultsCount to set
     */
    public void setTotalResultsCount(int totalResultsCount) {
        this.totalResultsCount = totalResultsCount;
    }

    /**
     * Get the pageSize.
     *
     * @return the pageSize
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * Set the pageSize.
     *
     * @param pageSize the pageSize to set
     */
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
        calculatePages();
    }

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
     * @param p the page to set
     */
    public void setPage(int p) {
        if (p >= maxPages) {
            this.page = maxPages;
        } else if (p <= 1) {
            this.page = 1;
        } else {
            this.page = p;
        }

        // Determine where the sublist starts and ends
        startingIndex = pageSize * (page - 1);
        if (startingIndex < 0) {
            startingIndex = 0;
        }

        endingIndex = startingIndex + pageSize;
        if (endingIndex > responses.size()) {
            endingIndex = responses.size();
        }
    }

    /**
     * Gets the maxPages.
     *
     * @return the maxPages
     */
    public int getMaxPages() {
        return maxPages;
    }

    /**
     * Calculate the number of max pages from the responses.
     */
    private void calculatePages() {
        if (pageSize > 0) {
            if (responses.size() % pageSize == 0) {
                maxPages = responses.size() / pageSize;
            } else {
                maxPages = (responses.size() / pageSize) + 1;
            }
        }
    }

    /**
     * Get a subset of all of the responses.
     *
     * @return a subset of all of the responses
     */
    public List<O> getResponsesForPage() {
        return responses.subList(startingIndex, endingIndex);
    }
}
