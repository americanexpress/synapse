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
package io.americanexpress.synapse.service.rest.model;

/**
 * {@code BaseServiceResponse} class specifies the prototypes for all service responses.
 *
 * @author Gabriel Jimenez
 */
public abstract class BaseServiceResponse {

    /**
     * Id used to uniquely get, update or remove a resource.
     */
    protected String id;

    /**
     * Get the id.
     *
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Set the id.
     *
     * @param id the identifier to set
     */
    public void setId(String id) {
        this.id = id;
    }
}
