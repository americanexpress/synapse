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
package ${package};

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * ${className}Application starts the Spring Boot Application for the ${apiName} API.
 * @author ${author}
 */
@OpenAPIDefinition(info = @Info(
        title = "${className} API",
        version = "v1.0.0",
        description = "Rest API that provides information."))
@SpringBootApplication
public class ${className}Application {

    private static final XLogger LOGGER = XLoggerFactory.getXLogger(${className}Application.class);

    /**
     * Main method to run the Spring Boot ${className} Application.
     *
     * @param args the args
     */
    public static void main(String[] args) {
        SpringApplication.run(${className}Application.class, args);
        LOGGER.info("${className} Application is up and running ...");
    }
}
