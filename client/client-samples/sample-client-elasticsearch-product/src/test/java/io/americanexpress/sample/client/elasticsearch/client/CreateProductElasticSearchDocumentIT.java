package io.americanexpress.sample.client.elasticsearch.client;

import io.americanexpress.sample.client.elasticsearch.config.ProductElasticSearchConfigTest;
import io.americanexpress.sample.client.elasticsearch.product.client.CreateProductElasticSearchDocumentClient;
import io.americanexpress.sample.client.elasticsearch.product.model.Product;
import io.americanexpress.synapse.framework.exception.ApplicationClientException;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * {@code CreateProductElasticSearchDocumentIT} tests the {@link CreateProductElasticSearchDocumentClient} class.
 *
 * @author sshre31
 */
@ContextConfiguration(classes = ProductElasticSearchConfigTest.class)
@ExtendWith(SpringExtension.class)
class CreateProductElasticSearchDocumentIT {

    @Autowired
    CreateProductElasticSearchDocumentClient createProductElasticSearchDocument;

    @Test
    void save_providedValidProduct_expectedSuccess() {
        var product = new Product();
        product.setId(UUID.randomUUID().toString());
        product.setName("Ice Cream");
        product.setDescription("Fudge Ice Cream.");
        assertDoesNotThrow(() -> createProductElasticSearchDocument.save(product));
    }

    @Test
    void read_providedValidProduct_expectedException() {
        var product = new Product();
        product.setName("Ice Cream");
        product.setDescription("Fudge Ice Cream.");
        assertThrows(ApplicationClientException.class, () -> createProductElasticSearchDocument.save(product));
    }
}
