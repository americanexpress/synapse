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
package io.americanexpress.sample.client.reactive.football.config;

import io.americanexpress.synapse.utilities.common.config.UtilitiesCommonConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * {@code FootballReactiveClientTestConfig} contains configurations for tests.
 *
 * @author eperjust
 */
@Configuration
@Import({FootballReactiveClientConfig.class, UtilitiesCommonConfig.class})
public class FootballReactiveClientTestConfig {
}
