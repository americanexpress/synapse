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
package ${package}.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ${package}.config.${className}DeleteServiceTestConfig;

/**
 * {@code ${className}DeleteServiceIT} class performs integration tests
 * for the {@link ${className}DeleteService}.
 * <p>
 * @author ${author}
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes=${className}DeleteServiceTestConfig.class)
class ${className}DeleteServiceIT {

    @Autowired
    private ${className}DeleteService restService;

    @Test
    void delete_givenValidRequest_expectedValidResponse() throws Exception {
        String id = "";
        restService.delete(id);
        // TODO: add assertion for success of deletion
    }
}
