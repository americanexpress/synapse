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
package io.americanexpress.synapse.service.rest.service.reactive;

import io.americanexpress.synapse.service.rest.model.BaseServiceResponse;
import io.americanexpress.synapse.service.rest.service.BaseService;
import reactor.core.publisher.Flux;

/**
 * {@code BaseGetPolyReactiveService} class specifies the prototypes for performing business logic.
 * @param <O>
 */
public abstract class BaseGetPolyReactiveService<O extends BaseServiceResponse> extends BaseService {

    /**
     * Retrieves multiple resource.
     * @return
     */
    public Flux<O> read() {
        logger.entry();
        var response = executeRead();
        logger.exit();
        return response;
    }

    protected abstract Flux<O> executeRead();
}
