package io.americanexpress.synapse.utility.io.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationModule;
import io.americanexpress.synapse.framework.exception.config.ExceptionConfig;
import io.americanexpress.synapse.utility.io.serialization.CurrencySerializer;
import io.americanexpress.synapse.utility.io.serialization.DateIsoDeserializer;
import io.americanexpress.synapse.utility.io.serialization.DateIsoSerializer;
import io.americanexpress.synapse.utility.io.serialization.DateTimeDeserializer;
import io.americanexpress.synapse.utility.io.serialization.DateTimeSerializer;
import io.americanexpress.synapse.utility.io.serialization.DecimalSerializer;
import io.americanexpress.synapse.utility.io.serialization.MoneyDeserializer;
import io.americanexpress.synapse.utility.io.serialization.StringSerializerModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * {@code UtilityIOConfig} class is used to initialize the configurations for utility-io.
 */
@Configuration
@ComponentScan(basePackages = "io.americanexpress.synapse.utility.io")
@Import(ExceptionConfig.class)
public class UtilityIOConfig {

    public static final String SYNAPSE_OBJECT_MAPPER = "synapseObjectMapper";
    /**
     * Used to retrieve the ObjectMapper that provides serialization and deserialization
     * for camelCase and non-empty fields.
     */
    public static final String SYNAPSE_CAMEL_CASE_OBJECT_MAPPER = "synapseCamelCaseObjectMapper";

    public static final String SYNAPSE_INCLUDE_EMPTY_OBJECT_MAPPER = "synapseIncludeEmptyObjectMapper";

    public static final String SYNAPSE_XML_OBJECT_MAPPER = "synapseXmlObjectMapper";

    /**
     * Create the initial ObjectMapper.
     *
     * @return the initial ObjectMapper
     */
    private ObjectMapper getInitialObjectMapper() {
        JavaTimeModule javaTimeDeserializerModule = new JavaTimeModule();
        javaTimeDeserializerModule.addDeserializer(LocalDate.class, new DateIsoDeserializer());
        javaTimeDeserializerModule.addDeserializer(LocalDateTime.class, new DateTimeDeserializer());

        JavaTimeModule javaTimeSerializerModule = new JavaTimeModule();
        javaTimeDeserializerModule.addSerializer(LocalDate.class, new DateIsoSerializer());
        javaTimeDeserializerModule.addSerializer(LocalDateTime.class, new DateTimeSerializer());


        SimpleModule moneySerializer = new SimpleModule();
        moneySerializer.addSerializer(BigDecimal.class, new CurrencySerializer());

        SimpleModule moneyDeserializer = new SimpleModule();
        moneyDeserializer.addDeserializer(BigDecimal.class, new MoneyDeserializer());

        SimpleModule decimalSerializer = new SimpleModule();
        decimalSerializer.addSerializer(Double.class, new DecimalSerializer());

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new StringSerializerModule());//This is to take as empty the blank String also.
        mapper.registerModule(moneySerializer);
        mapper.registerModule(moneyDeserializer);//This one could be avoided if used the USE_BIG_...
        mapper.enable(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS);

        mapper.registerModule(javaTimeDeserializerModule);//This is to register the local date deserializer
        mapper.registerModule(javaTimeSerializerModule);//Not needed with the below serialization feature.
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        mapper.registerModule(decimalSerializer); //This is to add commas and decimals to all Doubles
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.setSerializationInclusion(JsonInclude.Include.ALWAYS);
        mapper.enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS);
        mapper.enable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING);
        mapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);

        return mapper;
    }

    /**
     * Get the default ObjectMapper used for the majority of the application.
     * The default implementation provides serialization and deserialization
     * for snake_case and non-empty fields.
     *
     * @return the default object mapper
     */
    @Bean(SYNAPSE_OBJECT_MAPPER)
    public ObjectMapper defaultObjectMapper() {
        final ObjectMapper mapper = getInitialObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        return mapper;
    }

    /**
     * Get the ObjectMapper that provides serialization and deserialization
     * for camelCase and non-empty fields.
     *
     * @return the ObjectMapper that uses camelCase
     */
    @Bean(SYNAPSE_CAMEL_CASE_OBJECT_MAPPER)
    @Primary
    public ObjectMapper camelCaseObjectMapper() {
        final ObjectMapper mapper = getInitialObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        mapper.setPropertyNamingStrategy(PropertyNamingStrategies.LOWER_CAMEL_CASE);
        return mapper;
    }

    @Bean(SYNAPSE_XML_OBJECT_MAPPER)
    public ObjectMapper xmlObjectMapper() {
        final ObjectMapper objectMapper = new XmlMapper();
        objectMapper.registerModule(new JaxbAnnotationModule());
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return objectMapper;
    }

    @Bean(SYNAPSE_INCLUDE_EMPTY_OBJECT_MAPPER)
    public ObjectMapper includeEmptyObjectMapper() {
        return getInitialObjectMapper();
    }
}