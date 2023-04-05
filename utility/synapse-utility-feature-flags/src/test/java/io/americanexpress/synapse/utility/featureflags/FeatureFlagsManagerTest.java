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

import io.americanexpress.synapse.utility.featureflags.config.FeatureFlagsTestConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ContextConfiguration;

import java.io.IOException;

/**
 * {@code FeatureFlagsManagerTest} tests the {@link FeatureFlagsManager}.
 */
@ContextConfiguration(classes = FeatureFlagsTestConfig.class)
class FeatureFlagsManagerTest {

    private FeatureFlagsManager featureFlagsManager;

    @BeforeEach
    void init() throws IOException {
        featureFlagsManager = new FeatureFlagsManager();
    }

    @Test
    void getAll_givenFeatureFlags_expectedMappingOfFeatureFlags() {
        var map = featureFlagsManager.getAll();
        Assertions.assertAll(
                () -> Assertions.assertNotNull(map),
                () -> Assertions.assertEquals(3, map.size())
        );
    }

    @Test
    void get_givenValidName_expectedFeatureFlag() {
        var featureFlag = featureFlagsManager.get("feature.a");
        Assertions.assertAll(
                () -> Assertions.assertNotNull(featureFlag),
                () -> Assertions.assertEquals("true", featureFlag.getValue())
        );
    }

    @Test
    void get_givenInvalidName_expectedNull() {
        Assertions.assertNull(featureFlagsManager.get("feature.d"));
    }

    @Test
    void update_givenInvalidName_expectedNull() {
        Assertions.assertNull(featureFlagsManager.update("feature.d", "false"));
    }

    @Test
    void update_givenValidNameAndValue_expectedFeatureFlag() {
        var updatedFeatureFlag = featureFlagsManager.update("feature.a", "false");
        Assertions.assertAll(
                () -> Assertions.assertNotNull(updatedFeatureFlag),
                () -> Assertions.assertEquals("false", updatedFeatureFlag.getValue())
        );
    }
}
