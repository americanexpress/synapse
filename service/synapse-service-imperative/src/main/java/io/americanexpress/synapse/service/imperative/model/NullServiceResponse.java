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

/**
 * {@code NullServiceResponse} is used whenever a null response is required from a service. The service can just return
 * NullServiceResponse.INSTANCE rather than just returning null directly as this class also extends {@code BaseServiceResponse}
 * adhering to the prototype contracts.
 */
public class NullServiceResponse implements BaseServiceResponse {

    /**
     * Returns a null instance as a singleton pattern for when a null response is required from the service.
     */
    public static final NullServiceResponse INSTANCE = null;

    /**
     * Private constructor to prevent multiple instances being made.
     */
    private NullServiceResponse() {

    }
}
