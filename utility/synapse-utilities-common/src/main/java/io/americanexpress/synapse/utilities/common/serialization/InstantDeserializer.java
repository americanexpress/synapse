package io.americanexpress.synapse.utilities.common.serialization;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

/**
 * {@code InstantDeserializer} deserializes a string in ISO-8601 format including any offset into an {@link Instant} representing a single point
 * on the timeline in UTC. The resulting instant looks is in the format "yyyy-MM-dd'T'HH:mm:ss.SSSSSSSSS'Z'". The seconds and fraction of seconds are optional.
 */
public class InstantDeserializer extends JsonDeserializer<Instant> {

    private static final DateTimeFormatter ISO_INSTANT_OPTIONAL_SECOND_DATE_TIME_FORMATTER = new DateTimeFormatterBuilder()
            .parseCaseInsensitive()
            .append(DateTimeFormatter.ISO_LOCAL_DATE)
            .appendLiteral('T')
            .append(DateTimeFormatter.ISO_LOCAL_TIME)
            .appendOffsetId()
            .toFormatter();

    @Override
    public Instant deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        return Instant.from(ISO_INSTANT_OPTIONAL_SECOND_DATE_TIME_FORMATTER.parse(jsonParser.getValueAsString()));
    }
}
