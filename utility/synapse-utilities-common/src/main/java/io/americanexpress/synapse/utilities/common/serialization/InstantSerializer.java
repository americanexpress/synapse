package io.americanexpress.synapse.utilities.common.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

/**
 * {@code InstantSerializer} serializes an {@link Instant} object into a string representing that single point on the timeline in ISO-8601 format in UTC.
 */
public class InstantSerializer extends JsonSerializer<Instant> {

    private static final DateTimeFormatter ISO_INSTANT_OPTIONAL_SECOND_DATE_TIME_FORMATTER = new DateTimeFormatterBuilder()
            .parseCaseInsensitive()
            .append(DateTimeFormatter.ISO_LOCAL_DATE)
            .appendLiteral('T')
            .append(DateTimeFormatter.ISO_LOCAL_TIME)
            .appendLiteral('Z')
            .toFormatter()
            .withZone(ZoneOffset.UTC);

    @Override
    public void serialize(Instant instant, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeString(ISO_INSTANT_OPTIONAL_SECOND_DATE_TIME_FORMATTER.format(instant));
    }
}
