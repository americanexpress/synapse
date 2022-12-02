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
package io.americanexpress.synapse.client.rest.model;

import io.americanexpress.synapse.utilities.common.model.BaseModel;
import com.openpojo.business.annotation.BusinessKey;

/**
 * {@code CommonErrorResponse} can be used to create an error response.
 */
public class CommonErrorResponse extends BaseModel {

    @BusinessKey
    private Integer statusCode;

    @BusinessKey
    private String errorCode;

    @BusinessKey
    private String developerMessage;

    @BusinessKey
    private String userMessage;

    @BusinessKey
    private String moreInfo;

    @BusinessKey
    private String fault;

    /**
     * Gets status code.
     *
     * @return the status code
     */
    public Integer getStatusCode() {
        return statusCode;
    }

    /**
     * Sets status code.
     *
     * @param statusCode the status code
     */
    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    /**
     * Gets error code.
     *
     * @return the error code
     */
    public String getErrorCode() {
        return errorCode;
    }

    /**
     * Sets error code.
     *
     * @param errorCode the error code
     */
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * Gets developer message.
     *
     * @return the developer message
     */
    public String getDeveloperMessage() {
        return developerMessage;
    }

    /**
     * Sets developer message.
     *
     * @param developerMessage the developer message
     */
    public void setDeveloperMessage(String developerMessage) {
        this.developerMessage = developerMessage;
    }

    /**
     * Gets user message.
     *
     * @return the user message
     */
    public String getUserMessage() {
        return userMessage;
    }

    /**
     * Sets user message.
     *
     * @param userMessage the user message
     */
    public void setUserMessage(String userMessage) {
        this.userMessage = userMessage;
    }

    /**
     * Gets more info.
     *
     * @return the more info
     */
    public String getMoreInfo() {
        return moreInfo;
    }

    /**
     * Sets more info.
     *
     * @param moreInfo the more info
     */
    public void setMoreInfo(String moreInfo) {
        this.moreInfo = moreInfo;
    }

    /**
     * Gets fault.
     *
     * @return the fault
     */
    public String getFault() {
        return fault;
    }

    /**
     * Sets fault.
     *
     * @param fault the fault
     */
    public void setFault(String fault) {
        this.fault = fault;
    }
}
