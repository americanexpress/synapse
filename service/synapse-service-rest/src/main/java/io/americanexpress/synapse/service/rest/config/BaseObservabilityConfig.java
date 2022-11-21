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
package io.americanexpress.synapse.service.rest.config;

import org.springframework.core.env.Environment;

/**
 * {@code ObservabilityConfig} class sets the configuration setting up Tanzu Wavefront.
 * The service utilizing this config must set the properties defined in this class configure Tanzu Wavefront.
 *
 * @author Gabriel Jimenez
 */
public abstract class BaseObservabilityConfig {

    /**
     * Environment
     */
    private final Environment environment;

    /**
     * Constructor taking in environment
     * @param environment environment being wired.
     */
    public BaseObservabilityConfig(Environment environment) {
        this.environment = environment;
    }

    /**
     * Gets Wavefront application name
     * @return string value of application name for wavefront.
     */
    public String getWavefrontApplicationName() {
        return environment.getRequiredProperty("wavefront.application.name");
    }

    /**
     * Gets Wavefront application service
     * @return string value for service name of application for wavefront.
     */
    public String getWavefrontApplicationService() {
        return environment.getRequiredProperty("wavefront.application.service");
    }

}
