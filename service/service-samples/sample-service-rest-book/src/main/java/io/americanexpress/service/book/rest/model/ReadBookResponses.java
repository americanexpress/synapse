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

import java.util.List;
import java.util.Objects;

/**
 * {@code ReadBookResponses} encapsulates a list of ReadBookResponse for Flux samples.
 */
public class ReadBookResponses extends BaseServiceResponse {

    List<ReadBookResponse> readBookResponseList;

    public ReadBookResponses(List<ReadBookResponse> readBookResponseList) {
        this.readBookResponseList = readBookResponseList;
    }

    public List<ReadBookResponse> getReadBookResponseList() {
        return readBookResponseList;
    }

    public void setReadBookResponseList(List<ReadBookResponse> readBookResponseList) {
        this.readBookResponseList = readBookResponseList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReadBookResponses that = (ReadBookResponses) o;
        return Objects.equals(readBookResponseList, that.readBookResponseList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(readBookResponseList);
    }

    @Override
    public String toString() {
        return "ReadBookResponses{" +
                "readBookResponseList=" + readBookResponseList +
                '}';
    }
}
