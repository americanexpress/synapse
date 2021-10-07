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
package io.americanexpress.synapse.archetype.config;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.americanexpress.synapse.archetype.model.ArchetypeCatalog;
import io.americanexpress.synapse.archetype.service.SurgeonService;
import io.americanexpress.synapse.framework.exception.ApplicationServerException;
import io.americanexpress.synapse.service.rest.config.ServiceRestConfig;
import io.americanexpress.synapse.service.rest.interceptor.MetricInterceptor;
import io.americanexpress.synapse.utilities.common.model.UtilitiesCommonObjectFactory;
import io.americanexpress.synapse.utilities.common.serialization.TitleCaseStringSerializer;

/**
 * {@code SurgeonServiceConfig} class sets the configurations
 * for the {@link SurgeonService}.
 * @author Paolo Claudio
 *
 */
@PropertySource("classpath:surgeon-service-application.properties")
@ComponentScan("io.americanexpress.synapse.archetype")
@Configuration
public class SurgeonServiceConfig extends ServiceRestConfig {

	/**
	 * Argument constructor creates a new instance of SurgeonServiceConfig with given values.
	 * @param defaultObjectMapper the object mapper
	 * @param metricInterceptor the metric interceptor
	 */
	@Autowired
	public SurgeonServiceConfig(ObjectMapper defaultObjectMapper, MetricInterceptor metricInterceptor) {
		super(defaultObjectMapper, metricInterceptor);
	}

	/**
	 * Get the archetype catalog.
	 * @param archetypeCatalogLocation location of the archetype-catalog.xml set in the surgeon-service-application.properties
	 * @return the archetype catalog
	 * @throws JsonParseException whenever a parsing exception occurs of the archetype-catalog.xml file
	 * @throws JsonMappingException whenever a mapping exception occurs trying to set values from the archetype catalog to the service models
	 * @throws IOException whenever an input/output exception occurs trying to access the archetype-catalog.xml file
	 */
	@Bean
	@Value("${archetype-catalog-file}")
	public ArchetypeCatalog archetypeCatalog(String archetypeCatalogLocation) throws JsonParseException, JsonMappingException, IOException {
		
		if(archetypeCatalogLocation == null || !archetypeCatalogLocation.endsWith("archetype-catalog.xml")) {
			throw new ApplicationServerException(new NullPointerException("Please specify the location of 'archetype-catalog.xml' in archetype-catalog-file= in src/main/resources/surgeon-service-application.properties"));
		}
		
		File archetypeCatalogFile = new File(archetypeCatalogLocation);
		if(!archetypeCatalogFile.exists()) {
			throw new ApplicationServerException(new FileNotFoundException("File not found. Please specify an existing archetype-catalog.xml in archetype-catalog-file= in src/main/resources/surgeon-service-application.properties"));
		}
		
		ObjectMapper objectMapper = UtilitiesCommonObjectFactory.XML_OBJECT_MAPPER;
		return objectMapper.readValue(archetypeCatalogFile, ArchetypeCatalog.class);
	}
	
	/**
	 * Get the title case string serializer.
	 * @return the title case string serializer
	 */
	@Bean
	public TitleCaseStringSerializer titleCaseStringSerializer() {
		return new TitleCaseStringSerializer();
	}
}
