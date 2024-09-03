package io.americanexpress.synapse.utilities.common.serialization;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.Instant;
import java.time.format.DateTimeParseException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class InstantSerializerTest extends BaseTestSerializer {

    @BeforeEach
    @Override
    public void initializeModel() {
        super.initializeModel();
        setTestField("currentDateTime");
    }

    @Override
    public void arrangeNullField() {
        model.setCurrentDateTime(null);
    }

    @Override
    public void arrangeEmptyValue() {
        model.setCurrentDateTime(Instant.parse(" "));
    }

    @Override
    public String getExpectedEmptyValue() {
        return "";
    }

    @Test
    @Override
    public void serialize_clean() throws Exception {
        var currentDateTime = Instant.now();
        model.setCurrentDateTime(currentDateTime);
        var expected = "{\"" + testField + "\":\"" + currentDateTime + "\"}";
        var actual = mapper.writeValueAsString(model);
        assertEquals(expected, actual, SERIALIZATION_FAILED);
    }

    /**
     * Overriding the emptyValue test because you cannot set an empty value for an Instant class
     * from the model. The test will not even run and a DateTimeParseException is thrown when the model value is set
     * so we check for the exception thrown during arrangeEmptyValue().
     */
    @Test
    @Override
    public void serialize_emptyValue() {
        assertThrows(DateTimeParseException.class, this::arrangeEmptyValue);
    }
}
