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
package io.americanexpress.synapse.client.test.handler;

import io.americanexpress.synapse.client.rest.handler.BaseRestResponseErrorHandler;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * {@code BaseRestResponseErrorHandlerTest} is for testing the {@link BaseRestResponseErrorHandler}
 *
 * @param <E> the type parameter
 */
@ExtendWith(SpringExtension.class)
public abstract class BaseRestResponseErrorHandlerTest<E extends BaseRestResponseErrorHandler> {

    /**
     * The Logger.
     */
    protected final XLogger logger = XLoggerFactory.getXLogger(this.getClass());

    /**
     * The Error handler.
     */
    protected E errorHandler;

    /**
     * Handle error clean.
     */
    protected abstract void handleError_clean();
}
