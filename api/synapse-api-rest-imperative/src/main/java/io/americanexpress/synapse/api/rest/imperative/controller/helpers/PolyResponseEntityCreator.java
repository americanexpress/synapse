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
package io.americanexpress.synapse.api.rest.imperative.controller.helpers;

import io.americanexpress.synapse.service.imperative.model.BaseServiceResponse;
import io.americanexpress.synapse.service.imperative.model.PageResponse;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.http.ResponseEntity.HeadersBuilder;
import org.springframework.util.CollectionUtils;

/**
 * {@code PolyResponseEntityCreator} creates ResponseEntity for poly responses.
 * @param <O> a response extending {@link BaseServiceResponse}
 *
 * @author Francois Gutt
 */
public class PolyResponseEntityCreator {

    /**
     * Creates a Poly ResponseEntity with pagination.
     * @param page will be used for pagination.
     * @return ResponseEntity that will have {@link BaseServiceResponse}
     */
    public static <O extends BaseServiceResponse> ResponseEntity<List<O>> create(PageResponse<O> page) {
        final ResponseEntity<List<O>> responseEntity;
        List<O> pageContent = null;
        if (page != null) {
            pageContent = page.getResponsesForPage();
        }
        if (page == null || CollectionUtils.isEmpty(pageContent)) {
            responseEntity = new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            BodyBuilder bodyBuilder = ResponseEntity.ok();
            setHeadersInResponse(page, bodyBuilder);
            responseEntity = bodyBuilder.body(pageContent);
        }
        return responseEntity;
    }

    /**
     * Creates pagination header
     * @param page pagination
     * @param headersBuilder response headers
     */
    private static <O extends BaseServiceResponse> void setHeadersInResponse(final PageResponse<O> page, final HeadersBuilder headersBuilder) {
        headersBuilder.header("size", String.valueOf(page.getPageSize()));
        headersBuilder.header("page", String.valueOf(page.getPage()));
        headersBuilder.header("total_results_count", String.valueOf(page.getTotalResultsCount()));
    }
}
