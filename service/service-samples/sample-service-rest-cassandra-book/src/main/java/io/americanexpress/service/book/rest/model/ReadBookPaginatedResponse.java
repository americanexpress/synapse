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

import io.americanexpress.synapse.service.rest.model.BaseServiceResponse;

import java.nio.ByteBuffer;
import java.util.List;

/**
 * {@code ReadBookPaginatedResponse} is the response object for reading book paginated.
 */
public class ReadBookPaginatedResponse extends BaseServiceResponse {
    private ByteBuffer nextPageState;
    private List<ReadBookResponse> readBookResponses;
    public ByteBuffer getNextPageState() {
        return nextPageState;
    }
    public void setNextPageState(ByteBuffer nextPageState) {
        this.nextPageState = nextPageState;
    }
    public List<ReadBookResponse> getReadBookResponses() {
        return readBookResponses;
    }

    public void setReadBookResponses(List<ReadBookResponse> readBookResponses) {
        this.readBookResponses = readBookResponses;
    }
}
