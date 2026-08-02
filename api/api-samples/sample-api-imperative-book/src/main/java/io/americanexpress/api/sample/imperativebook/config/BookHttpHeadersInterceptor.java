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
package io.americanexpress.api.sample.imperativebook.config;

import io.americanexpress.synapse.api.rest.imperative.interceptor.BaseHttpInterceptor;
import org.springframework.stereotype.Component;
import java.util.List;

/**
 * Intercepts HTTP requests to validate required headers for book-related APIs.
 *
 * @author Aziz Ali
 */
@Component
public class BookHttpHeadersInterceptor extends BaseHttpInterceptor {

    /**
     * Provides the list of required HTTP header names.
     *
     * @return list of required header names
     */
    @Override
    protected List<String> getRequiredHttpHeaderNames() {
        return requiredHttpHeaderNames.stream().toList();
    }
}
