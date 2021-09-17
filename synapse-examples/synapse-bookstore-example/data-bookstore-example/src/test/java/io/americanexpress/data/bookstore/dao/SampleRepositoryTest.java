package io.americanexpress.data.bookstore.dao;

import com.zenith.data.postgresbasetest.dao.BasePostgresRepositoryTest;
import io.americanexpress.data.bookstore.config.DataBookConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.zenith.data.sample.config.DataSampleConfig;
import org.zenith.data.sample.entity.SampleEntity;

import java.util.Optional;

/**
 * The type Sample repository test.
 */
@ContextConfiguration(classes = DataBookConfig.class)
class SampleRepositoryTest extends BasePostgresRepositoryTest {

    @Autowired
    private SampleRepository sampleRepository;

    /**
     * Find by id given good repo expected success.
     */
    @Test
    void findById_givenGoodRepo_expectedSuccess() {
        Optional<SampleEntity> sampleEntityOptional = sampleRepository.findById(1L);
        if (sampleEntityOptional.isPresent()) {
            SampleEntity sampleEntity = sampleEntityOptional.get();
            Assertions.assertEquals(1, sampleEntity.getId());
        }

    }
}
