package io.americanexpress.synapse.utilities.common.serialization;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.americanexpress.synapse.utilities.common.config.UtilitiesCommonConfig;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import java.time.Instant;
import java.time.format.DateTimeParseException;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class InstantDeserializerTest {

    private static ObjectMapper objectMapper;

    @BeforeAll
    public static void setUp() {
        objectMapper = new UtilitiesCommonConfig().camelCaseObjectMapper();
    }

    @Test
    void deserialize_givenValidString_expectedSuccess() throws JsonProcessingException {
        var json = "\"" + Instant.now().toString() + "\"";
        var actual = objectMapper.readValue(json, Instant.class);
        assertNotNull(actual);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "2024-08-30T11:15:43.345903",
            "2024-08-30T11:15:43",
            "2024-08-30T11:15",
            "2024-08-30T11Z",
    })
    void deserialize_givenStringInvalidString_expectedDateTimeParseException(String invalidDateTime) {
        var json = "\"" + invalidDateTime + "\"";
        assertThrows(DateTimeParseException.class, () -> objectMapper.readValue(json, Instant.class));
    }
}
