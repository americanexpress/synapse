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
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * {@code FeatureFlagsManager} used to retrieve all feature flags from application-features.properties file and
 * contains methods on managing feature flags such as get, getAll, and update.
 */
@Component
public class FeatureFlagsManager {
    private Map<String, FeatureFlag> featureFlagsMap;

    /**
     * Instantiates a new Feature flag manager.
     *
     * @throws IOException the io exception
     */
    public FeatureFlagsManager() throws IOException {
        initialize();
    }

    private void initialize() throws IOException {
        featureFlagsMap = new HashMap<>();
        var resource = new ClassPathResource("/application-features.properties");
        var props = PropertiesLoaderUtils.loadProperties(resource);
        props.forEach((key, value) -> {
            if (key.toString().startsWith("feature.")) {
                var featureFlag = new FeatureFlag();
                featureFlag.setName(key.toString());
                featureFlag.setValue((String) value);
                featureFlagsMap.put(featureFlag.getName(), featureFlag);
            }
        });
    }

    /**
     * Gets all.
     *
     * @return mapping of all the feature flags
     */
    public Map<String, FeatureFlag> getAll() {
        return featureFlagsMap;
    }

    /**
     * Get feature flag.
     *
     * @param name the name
     * @return the feature flag
     */
    public FeatureFlag get(String name) {
        return featureFlagsMap.get(name);
    }

    /**
     * Update feature flag.
     *
     * @param name  the name
     * @param value the value
     * @return the feature flag or null
     */
    public FeatureFlag update(String name, String value) {
        var featureFlag = get(name);
        if(featureFlag != null) {
            featureFlag.setValue(value);
        }
        return featureFlag;
    }

}
