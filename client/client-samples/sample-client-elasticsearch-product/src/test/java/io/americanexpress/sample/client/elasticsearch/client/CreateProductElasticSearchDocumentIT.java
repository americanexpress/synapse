package io.americanexpress.sample.client.elasticsearch.client;

import io.americanexpress.sample.client.elasticsearch.config.ProductElasticSearchConfigTest;
import io.americanexpress.sample.client.elasticsearch.product.client.CreateProductElasticSearchDocumentClient;
import io.americanexpress.sample.client.elasticsearch.product.model.Product;
import java.io.IOException;
import java.util.Collections;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * {@code CreateProductElasticSearchDocumentIT}
 *
 * @author sshre31
 */
@ContextConfiguration(classes = ProductElasticSearchConfigTest.class)
@ExtendWith(SpringExtension.class)
public class CreateProductElasticSearchDocumentIT {

    @Autowired
    CreateProductElasticSearchDocumentClient createProductElasticSearchDocument;

    @Test
    void save_providedValidProduct_expectedSuccess() throws IOException {
        var product = new Product(UUID.randomUUID(), "Product 1", "Product 1 description", Collections.emptyList());

        createProductElasticSearchDocument.save(product.getId().toString(), product);
    }
}
