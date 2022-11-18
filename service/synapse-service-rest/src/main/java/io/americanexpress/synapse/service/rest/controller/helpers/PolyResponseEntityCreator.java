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
package io.americanexpress.synapse.service.rest.controller.helpers;

import io.americanexpress.synapse.service.rest.model.BaseServiceResponse;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * {@code PolyResponseEntityCreator} creates ResponseEntity for poly responses
 * @param <O>
 */
public class PolyResponseEntityCreator<O extends BaseServiceResponse> {

    /**
     * Creates a Poly ResponseEntity with pagination
     * @param page
     * @param httpServletResponse
     * @return ResponseEntity
     * @param <O>
     */
    public static <O extends BaseServiceResponse> ResponseEntity<List<O>> create(Page<O> page, HttpServletResponse httpServletResponse) {
        final ResponseEntity<List<O>> responseEntity;
        List<O> pageContent = null;
        if (page != null) {
            pageContent = page.getContent();
        }
        if (page == null || CollectionUtils.isEmpty(pageContent)) {
            responseEntity = new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            setHeadersInResponse(page, httpServletResponse);
            responseEntity = new ResponseEntity<>(pageContent, HttpStatus.OK);
        }
        return responseEntity;
    }

    /**
     * Creates pagination header
     * @param page
     * @param httpServletResponse
     * @param <O>
     */
    private static <O extends BaseServiceResponse> void setHeadersInResponse(final Page<O> page, final HttpServletResponse httpServletResponse) {
        if (page != null && !CollectionUtils.isEmpty(page.getContent())) {
            httpServletResponse.setHeader("size", String.valueOf(page.getSize()));
            httpServletResponse.setHeader("page", String.valueOf(page.getNumber()));
            httpServletResponse.setHeader("total_results_count", String.valueOf(page.getNumberOfElements()));
        }
    }
}
