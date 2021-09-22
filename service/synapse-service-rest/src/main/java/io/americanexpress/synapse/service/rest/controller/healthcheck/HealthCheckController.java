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
package io.americanexpress.synapse.service.rest.controller.healthcheck;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <code>HealthCheckController</code> class specifies health check endpoint URIs to ensure that requests are being received by the application.
 *
 * @author Paolo Claudio
 */
@RestController
public class HealthCheckController {

    public static final String HEALTH_CHECK_ENDPOINT = "/health";

    /**
     * Health message used for the health check URIs.
     */
    static String HEALTH_MESSAGE = "App is healthy!";

    /**
     * Default health check URI used by all services.
     *
     * @return a constant message to notify that the service is receiving a request
     */
    @GetMapping(HEALTH_CHECK_ENDPOINT)
    public String healthCheck() {
        return HEALTH_MESSAGE;
    }
}
