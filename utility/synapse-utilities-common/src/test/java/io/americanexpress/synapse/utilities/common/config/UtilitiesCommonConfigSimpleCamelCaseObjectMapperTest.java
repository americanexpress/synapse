package io.americanexpress.synapse.utilities.common.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.americanexpress.synapse.utilities.common.io.IOUtils;
import io.americanexpress.synapse.utilities.common.model.SampleDeserializedObject;
import io.americanexpress.synapse.utilities.common.model.SampleNestedCollectionsObject;
import io.americanexpress.synapse.utilities.common.model.SampleNestedObject;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.Collections;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * {@code UtilitiesCommonConfigSimpleCamelCaseObjectMapperTest} tests the
 * {@link UtilitiesCommonConfig#simpleCamelCaseObjectMapper} object mapper.
 */
public class UtilitiesCommonConfigSimpleCamelCaseObjectMapperTest {

    private static final String EMPTY_STRING = "";
    private static final String ONE_WHITESPACE_STRING = " ";
    private static final ObjectMapper objectMapper = new UtilitiesCommonConfig().simpleCamelCaseObjectMapper();

    @Test
    void readValue_givenEmptyStringsAndCollections_expectedToDeserialize() throws JsonProcessingException {
        SampleDeserializedObject actualObject = objectMapper.readValue(
                readTestDataJsonAsString("empty-strings-collections-valid.json"),
                    SampleDeserializedObject.class);
        assertThat(actualObject)
                .isNotNull()
                .extracting(
                        SampleDeserializedObject::getSomeName,
                        SampleDeserializedObject::getSomeNumber,
                        SampleDeserializedObject::getSomeLocalDate)
                .containsExactly(
                        "Stephen Strange",
                        123,
                        LocalDate.of(2018, 4, 1));
        assertThat(actualObject.getSampleNestedObject())
                .isNotNull()
                .extracting(
                        SampleNestedObject::getSomeText1,
                        SampleNestedObject::getSomeText2)
                .containsExactly(
                        EMPTY_STRING,
                        ONE_WHITESPACE_STRING);
        assertThat(actualObject.getSampleNestedCollectionsObject()).isNotNull();
        assertThat(actualObject.getSampleNestedCollectionsObject().getSomeStringCollection())
                .isEmpty();
        assertThat(actualObject.getSampleNestedCollectionsObject().getSomeStringArray())
                .isEmpty();

    }

    @Test
    void writeValue_givenEmptyStrings_expectedToSerialize() throws JsonProcessingException {
        String json = objectMapper.writeValueAsString(sampleDeserializedObjectWithEmptyStringsAndCollections());
        String expectedJson = "{\"someName\":\"Stephen Strange\",\"someNumber\":123," +
                              "\"sampleNestedObject\":{\"someText1\":\"\",\"someText2\":\" \"}," +
                              "\"sampleNestedCollectionsObject\":{\"someStringCollection\":[]," +
                              "\"someStringArray\":[]},\"someLocalDate\":\"2018-04-01\"}";
        assertThat(json).isEqualTo(expectedJson);
    }

    @Test
    void readValue_givenSnakeCasePropertyNames_expectedToThrowException() {
        assertThatThrownBy(() -> objectMapper.readValue(
                readTestDataJsonAsString("snakecase-attribute-names-invalid.json"),
                    SampleDeserializedObject.class))
                .isInstanceOf(JsonProcessingException.class)
                .hasMessageContaining("Unrecognized field");
    }

    @Test
    void readValue_givenUnknownProperty_expectedToThrowException() {
        assertThatThrownBy(() -> objectMapper.readValue(
                readTestDataJsonAsString("unknown-property-invalid.json"),
                    SampleDeserializedObject.class))
                .isInstanceOf(JsonProcessingException.class)
                .hasMessageContaining("Unrecognized field \"someUnknownProperty\"");
    }

    private SampleDeserializedObject sampleDeserializedObjectWithEmptyStringsAndCollections() {
        SampleDeserializedObject sampleDeserializedObject = new SampleDeserializedObject();
        sampleDeserializedObject.setSomeName("Stephen Strange");
        sampleDeserializedObject.setSomeNumber(123);
        sampleDeserializedObject.setSomeLocalDate(LocalDate.of(2018, 4, 1));

        SampleNestedObject sampleNestedObject = new SampleNestedObject();
        sampleNestedObject.setSomeText1("");
        sampleNestedObject.setSomeText2(" ");
        sampleDeserializedObject.setSampleNestedObject(sampleNestedObject);

        SampleNestedCollectionsObject sampleNestedCollectionsObject = new SampleNestedCollectionsObject();
        sampleNestedCollectionsObject.setSomeStringCollection(Collections.emptyList());
        sampleNestedCollectionsObject.setSomeStringArray(new String[]{});

        sampleDeserializedObject.setSampleNestedCollectionsObject(sampleNestedCollectionsObject);

        return sampleDeserializedObject;
    }

    private String readTestDataJsonAsString(String fileName) {
        return IOUtils.readFileToAString("simple-camel-case-object-mapper-test-data/" + fileName);
    }
}
