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
 * {@code ExposedHeaders} enum has the names of the exposed HTTP headers that can be discovered in addition to the standard response HTTP headers that are exposed by Spring.
 *
 * @author Paolo Claudio
 */
public enum ExposedHeaders {

    PAGE("Page"), SIZE("Size"), TOTAL_RESULTS_COUNT("Total-Results-Count");

    /**
     * Name of the exposed header.
     */
    private String name;

    /**
     * Argument constructor creates a new instance of ExposedHeaders with given values.
     *
     * @param name of the exposed header
     */
    ExposedHeaders(String name) {
        setName(name);
    }

    /**
     * Get the name.
     *
     * @return the name.
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name.
     *
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
}
