package io.americanexpress.sample.client.elasticsearch.config;

import io.americanexpress.sample.client.elasticsearch.product.config.ProductElasticSearchConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * {@code ProductElasticSearchConfigTest}
 *
 * @author sshre31
 */
@Configuration
@Import(ProductElasticSearchConfig.class)
public class ProductElasticSearchConfigTest {

}
