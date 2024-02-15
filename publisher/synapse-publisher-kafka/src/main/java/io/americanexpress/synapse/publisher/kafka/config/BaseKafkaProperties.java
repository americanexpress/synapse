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
package io.americanexpress.synapse.publisher.kafka.config;

import io.americanexpress.synapse.framework.exception.ApplicationServerException;
import org.apache.kafka.common.security.auth.SecurityProtocol;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.boot.ssl.SslBundles;
import org.springframework.core.env.Environment;
import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.FileNotFoundException;
import java.nio.file.FileSystemNotFoundException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <code>BaseKafkaProperties</code> is the parent class for all the properties related to a kafka.
 *
 * @author Krishna Kuchikulla
 */
public abstract class BaseKafkaProperties<T extends BaseKafkaProperties.BaseKafkaTemplate, P extends BaseKafkaProperties.BaseKafkaProducer<S>, S extends BaseKafkaProperties.BaseKafkaSsl> extends KafkaProperties {

	/**
	 * BaseKafkaTemplateConfiguration.
	 */
	private final T template;

	/**
	 * BaseKafkaProducerConfiguration.
	 */
	private final P producer;

	/**
	 * BaseKafkaSslConfiguration.
	 */
	private final S ssl;

	/**
	 * Constructor for BaseKafkaProperties.
	 *
	 * @param template BaseKafkaTemplateConfiguration
	 * @param producer BaseKafkaProducerConfiguration
	 * @param ssl      BaseKafkaSslConfiguration
	 */
	protected BaseKafkaProperties(T template, P producer, S ssl) {
		this.template = template;
		this.producer = producer;
		this.ssl = ssl;
	}

	/**
	 * This method is used to get producer properties object.
	 */
	@Override
	public P getProducer() {
		return this.producer;
	}

	/**
	 * This method is used to get template properties object.
	 */
	@Override
	public T getTemplate() {
		return this.template;
	}

	/**
	 * This method is used to get ssl properties object.
	 */
	@Override
	public S getSsl() {
		return this.ssl;
	}

	/**
	 * This method is used to get bootstrapServers.
	 */
	@Override
	public List<String> getBootstrapServers() {
		return getProducer().getBootstrapServers();
	}

	/**
	 * This method is used to build a HashMap of producer properties.
	 * Added security.protocol property here as there is no support provided in kafkaProperties class
	 */
	@Override
	public Map<String, Object> buildProducerProperties() {
		Map<String, Object> properties = new HashMap<>();
		properties.put("security.protocol", SecurityProtocol.SSL.name);
		properties.putAll(this.getSsl().buildProperties());
		properties.putAll(this.getProducer().buildProperties());
		return properties;
	}

	/**
	 * <code>BaseKafkaProducer</code> class contains the properties related to a kafka producer.
	 */
	public abstract static class BaseKafkaProducer<S extends BaseKafkaSsl> extends KafkaProperties.Producer {

		/**
		 * Environment.
		 */
		private final Environment environment;

		/**
		 * BaseKafkaSSL.
		 */
		private final S ssl;

		/**
		 * Constructor for BaseKafkaProducer.
		 *
		 * @param environment environment
		 * @param ssl         baseKafkaSSLConfig
		 */
		protected BaseKafkaProducer(Environment environment, S ssl) {
			this.environment = environment;
			this.ssl = ssl;
		}

		/**
		 * This method is used to get Ssl properties.
		 */
		@Override
		public S getSsl() {
			return this.ssl;
		}

		/**
		 * This method is used to get kafka bootstrap servers.
		 */
		@Override
		public List<String> getBootstrapServers() {
			return Collections.singletonList(environment.getRequiredProperty("bootstrap.servers"));
		}

		/**
		 * This method is used to get serializer used for key of the message.
		 */
		@Override
		public Class<?> getKeySerializer() {
			return StringSerializer.class;
		}

		/**
		 * This method is used to get serializer used for value of the message.
		 */
		@Override
		public Class<?> getValueSerializer() {
			return StringSerializer.class;
		}
	}

	/**
	 * <code>BaseKafkaTemplate</code> class contains the properties related to a kafka template.
	 */
	public abstract static class BaseKafkaTemplate extends KafkaProperties.Template {

		/**
		 * Environment.
		 */
		private final Environment environment;

		/**
		 * Constructor for BaseKafkaTemplate.
		 *
		 * @param environment environment
		 */
		protected BaseKafkaTemplate(Environment environment) {
			this.environment = environment;
		}

		/**
		 * This method is get name of kafka topic.
		 */
		@Override
		public String getDefaultTopic() {
			return environment.getRequiredProperty("topic.name");
		}
	}

	/**
	 * <code>BaseKafkaSSL</code> class contains the properties related to a ssl configuration.
	 */
	public abstract static class BaseKafkaSsl extends KafkaProperties.Ssl {

		/**
		 * Environment.
		 */
		private final Environment environment;

		/**
		 * ResourceLoader.
		 */
		private final ResourceLoader resourceLoader;

		/**
		 * Constructor for BaseKafkaSSL.
		 *
		 * @param environment environment
		 */
		protected BaseKafkaSsl(Environment environment) {
			this.environment = environment;
			this.resourceLoader = new FileSystemResourceLoader();
		}

		/**
		 * This method is used to get key password.
		 */
		@Override
		public String getKeyPassword() {
			return environment.getRequiredProperty("key.password");
		}

		/**
		 * This method is used to get keyStore password.
		 */
		@Override
		public String getKeyStorePassword() {
			return environment.getRequiredProperty("kafka.keyStore.password");
		}

		/**
		 * This method is used to get keyStore type.
		 */
		@Override
		public String getKeyStoreType() {
			return environment.getRequiredProperty("keyStore.type");
		}

		/**
		 * This method is used to get keyStore location.
		 *
		 * @throws FileSystemNotFoundException if the keyStore is not found in a given location
		 */
		@Override
		public Resource getKeyStoreLocation() {
			Resource resource = resourceLoader.getResource(environment.getRequiredProperty("keyStore.location"));
			if (!resource.exists()) {
				throw new ApplicationServerException(new FileNotFoundException(resource.getDescription() + " not found"));
			}
			return resource;
		}

		/**
		 * This method is used to get trustStore location.
		 *
		 * @throws FileSystemNotFoundException if the trustStore is not found in a given location
		 */
		@Override
		public Resource getTrustStoreLocation() {
			Resource resource = resourceLoader.getResource(environment.getRequiredProperty("kafka.trustStore.location"));
			if (!resource.exists()) {
				throw new ApplicationServerException(new FileNotFoundException(resource.getDescription() + " not found"));
			}
			return resource;
		}

		/**
		 * This method is used to get trustStore password.
		 */
		@Override
		public String getTrustStorePassword() {
			return environment.getRequiredProperty("kafka.trustStore.password");
		}

		/**
		 * This method is used to get trustStore type.
		 */
		@Override
		public String getTrustStoreType() {
			return environment.getRequiredProperty("kafka.trustStore.type");
		}

		/**
		 * This method is used to get ssl protocol.
		 */
		@Override
		public String getProtocol() {
			return environment.getRequiredProperty("protocol");
		}
	}
}
