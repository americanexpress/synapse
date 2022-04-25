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
package io.americanexpress.service.book.graphql;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * {@code BookApplication} class starts the application.
 * To test this sample application, please refer to the instructions
 * in {@code /src/test/resources}.
 * @author Paolo Claudio
 *
 */
@SpringBootApplication
public class BookApplication {

	private static final XLogger LOGGER = XLoggerFactory.getXLogger(BookApplication.class);

	/**
	 * Run the application.
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(BookApplication.class, args);
		LOGGER.info("Graphql Book Application is up and running ...");
	}
}
