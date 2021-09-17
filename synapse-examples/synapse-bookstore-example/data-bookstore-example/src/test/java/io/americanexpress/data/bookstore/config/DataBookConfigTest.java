package io.americanexpress.data.bookstore.config;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class DataBookConfigTest {

    @InjectMocks
    private DataBookConfig underTest;

    /**
     * Data sample config given good environment expected not null.
     */
    @Test
    void dataSampleConfig_givenGoodEnvironment_expectedNotNull() {
        Assertions.assertNotNull(underTest);
    }
}
