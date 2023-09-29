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
package io.americanexpress.synapse.service.reactive.model;

/**
 * {@code BasePaginatedServiceRequest} should be used when pagination is needed in a Poly Controller.
 * @author Francois Gutt
 */
public abstract class BasePaginatedServiceRequest extends BaseServiceRequest {

    /**
     * Used for services that support pagination.
     */
    private PageInformation pageInformation;

    /**
     * Get the pageInformation.
     * @return the pageInformation
     */
    public PageInformation getPageInformation() {
        return pageInformation;
    }

    /**
     * Set the pageInformation.
     * @param pageInformation the pageInformation to set
     */
    public void setPageInformation(PageInformation pageInformation) {
        this.pageInformation = pageInformation;
    }
}
