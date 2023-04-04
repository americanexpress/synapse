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
package io.americanexpress.synapse.utility.featureflags;

import io.americanexpress.synapse.utility.featureflags.model.FeatureFlag;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.Selector;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * {@code FeatureFlagsEndpoint} adds custom actuator endpoints that can be used to manage feature flags for an application
 * GET /actuator/features
 * GET /actuator/features/{feature.name}
 * POST /actuator/features/{feature.name}
 */
@Component
@Endpoint(id = "features")
public class FeatureFlagsEndpoint {

    /**
     * Used to manage the feature flags in the application.
     */
    private final FeatureFlagsManager featureFlagsManager;

    /**
     * Instantiates a new Feature flags endpoint.
     *
     * @param featureFlagsManager the feature flag manager
     */
    public FeatureFlagsEndpoint(FeatureFlagsManager featureFlagsManager) {
        this.featureFlagsManager = featureFlagsManager;
    }

    /**
     * Retrieves all of the feature flags available.
     *
     * @return the map
     */
    @ReadOperation
    public Map<String, FeatureFlag> features() {
        return featureFlagsManager.getAll();
    }

    /**
     * Retrieves the specific feature flag.
     *
     * @param name the name
     * @return the feature flag
     */
    @ReadOperation
    public FeatureFlag feature(@Selector String name) {
        return featureFlagsManager.get(name);
    }

    /**
     * Configure feature.
     *
     * @param name  the name
     * @param value the value
     */
    @WriteOperation
    public void configureFeature(@Selector String name, String value) {
        featureFlagsManager.update(name, value);
    }

}
