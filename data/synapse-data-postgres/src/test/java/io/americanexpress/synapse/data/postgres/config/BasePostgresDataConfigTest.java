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
package io.americanexpress.synapse.data.postgres.config;

import io.americanexpress.synapse.data.postgres.entity.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceUnit;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.stat.Statistics;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * Integration tests for verifying the second-level cache behavior in the Postgres data module.
 * <p>
 * Tests include cache put, hit, and eviction scenarios using the {@link Product} entity.
 * The tests are run with the {@link DataSampleConfig} configuration and use Hibernate statistics
 * to assert cache operations.
 * </p>
 *
 * @author Aziz Ali
 */
@SpringBootTest(classes = {DataSampleConfig.class})
class BasePostgresDataConfigTest {

    @PersistenceUnit
    private EntityManagerFactory emf;

    private Statistics stats;
    private Product product1, product2, product3;

    @BeforeEach
    void setUp() {
        SessionFactoryImplementor sfi = emf.unwrap(SessionFactoryImplementor.class);
        stats = sfi.getStatistics();
        stats.clear();
        stats.setStatisticsEnabled(true);

        EntityManager em = emf.createEntityManager();
        em.getTransaction()
                .begin();
        product1 = new Product();
        product1.setProductName("Cached Product1");
        product2 = new Product();
        product2.setProductName("Cached Product2");
        product3 = new Product();
        product3.setProductName("Cached Product3");
        em.persist(product1);
        em.persist(product2);
        em.persist(product3);
        em.flush();
        em.getTransaction()
                .commit();
        em.close();
    }

    @Test
    void testSecondLevelCachePutOnFind() {
        stats.clear();
        findAllProducts();
        assertThat(stats.getSecondLevelCachePutCount())
                .as("Expected three puts on first find")
                .isEqualTo(3);
    }

    @Test
    void testSecondLevelCacheHitOnFind() {
        // Populate cache
        findAllProducts();
        stats.clear();
        // Access again for hits
        findAllProducts();
        assertThat(stats.getSecondLevelCacheHitCount())
                .as("Expected three hits from 2LC")
                .isEqualTo(3);
        assertThat(stats.getSecondLevelCachePutCount())
                .as("No new puts expected on cache hit")
                .isZero();
    }

    @Test
    void testSecondLevelCacheMissAfterEviction() throws InterruptedException {
        // Populate cache
        findAllProducts();
        // Wait for TTL eviction (3 seconds as per ehcache.xml)
        Thread.sleep(5000);
        stats.clear();
        findAllProducts();
        assertThat(stats.getSecondLevelCacheMissCount())
                .as("Expected three misses after TTL eviction")
                .isEqualTo(3);
        assertThat(stats.getSecondLevelCachePutCount())
                .as("Expected three puts to reload into 2LC after eviction")
                .isEqualTo(3);
    }

    private void findAllProducts() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction()
                .begin();
        em.find(Product.class, product1.getId());
        em.find(Product.class, product2.getId());
        em.find(Product.class, product3.getId());
        em.getTransaction()
                .commit();
        em.close();
    }
}