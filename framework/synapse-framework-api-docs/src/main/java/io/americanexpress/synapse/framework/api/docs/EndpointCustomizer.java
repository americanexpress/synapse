package io.americanexpress.synapse.framework.api.docs;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.Operation;
import org.springdoc.core.customizers.OperationCustomizer;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;

@Component
public class EndpointCustomizer implements OperationCustomizer {

    @Override
    public Operation customize(Operation operation, HandlerMethod handlerMethod) {
        // Will add the externalDocs to all the endpoints
        operation.externalDocs(new ExternalDocumentation().url("/v1/**").description("Link to resource"));

        return operation;
    }
}