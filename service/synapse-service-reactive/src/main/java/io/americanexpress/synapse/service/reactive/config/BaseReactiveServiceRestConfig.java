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
package io.americanexpress.synapse.service.reactive.config;

import io.americanexpress.synapse.framework.exception.config.ExceptionConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * {@code BaseServiceReactiveRestConfig} sets common configurations for the service layer.
 *
 * @author Francois Gutt
 */
@ComponentScan(basePackages = "io.americanexpress.synapse.service.reactive")
@Configuration
@Import({ExceptionConfig.class})
//@Import({ExceptionConfig.class, UtilitiesCommonConfig.class})
public class BaseReactiveServiceRestConfig {

}
