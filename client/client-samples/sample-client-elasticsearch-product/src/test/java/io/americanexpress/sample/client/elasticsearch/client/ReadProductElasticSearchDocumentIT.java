package io.americanexpress.sample.client.elasticsearch.client;

import io.americanexpress.sample.client.elasticsearch.config.ProductElasticSearchConfigTest;
import io.americanexpress.sample.client.elasticsearch.product.client.ReadProductElasticSearchDocumentClient;
import java.io.IOException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * {@code ReadProductElasticSearchDocumentIT}
 *
 * @author sshre31
 */
@ContextConfiguration(classes = ProductElasticSearchConfigTest.class)
@ExtendWith(SpringExtension.class)
class ReadProductElasticSearchDocumentIT {


    @Autowired
    ReadProductElasticSearchDocumentClient readProductElasticSearchDocumentClient;

    @Test
    void findById_providedValidId_expectedSuccess() throws IOException {
        var product = readProductElasticSearchDocumentClient.findById("{id}");
        assertNotNull(product);
    }

    @Test
    void findAll_providedValidRequest_expectedSuccess() throws IOException {
        var allProducts = readProductElasticSearchDocumentClient.findAll();
        assertNotNull(allProducts);
    }

    @Test
    void searchByKey_providedValidKeyword_expectedSuccess() throws IOException {
        var products = readProductElasticSearchDocumentClient.searchByKey("name", "Ice Cream");
        assertNotNull(products);
    }

    @Test
    void doesExists_providedValidId_expectedSuccess() throws IOException {
        var products = readProductElasticSearchDocumentClient.doesExists("{id}");
        assertTrue(products);
    }
}
