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
package io.americanexpress.synapse.function.rest.handler;

import io.americanexpress.synapse.function.rest.model.BaseFunctionRequest;
import io.americanexpress.synapse.function.rest.model.BaseFunctionResponse;
import org.springframework.data.domain.Page;

public abstract class BaseReadPolyService<I extends BaseFunctionRequest, O extends BaseFunctionResponse> extends BaseHandler {

    public Page<O> read(final I request) {

        logger.entry(request);

        final Page<O> responses = executeRead(request);

        logger.exit(responses);

        return responses;
    }

    protected abstract Page<O> executeRead(I request);
}
