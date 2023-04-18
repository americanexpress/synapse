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
package io.americanexpress.synapse.service.reactive.rest.controller;

import io.americanexpress.synapse.service.reactive.rest.service.BaseService;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * {@code BaseController} The base controller every child controller should extend this parent controller
 * @param <S> an object extending the {@link BaseService}
 */
public abstract class BaseController<S extends BaseService> {

    /**
     * Service that will be called to get a single resource or multiple resources.
     */
    @Autowired
    protected S service;

    /**
     * Used for logging.
     */
    protected final XLogger logger = XLoggerFactory.getXLogger(this.getClass());

}
