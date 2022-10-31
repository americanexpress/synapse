package io.americanexpress.function.greeting.rest.handler;

import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.HttpMethod;
import com.microsoft.azure.functions.HttpRequestMessage;
import com.microsoft.azure.functions.HttpResponseMessage;
import com.microsoft.azure.functions.HttpStatus;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;

import org.springframework.cloud.function.adapter.azure.FunctionInvoker;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

public class GreeterHandler extends FunctionInvoker<Message<String>, String> {

    @FunctionName("greeter")
    public HttpResponseMessage execute(
            @HttpTrigger(name = "request", methods = {HttpMethod.GET, HttpMethod.POST}, authLevel = AuthorizationLevel.ANONYMOUS) HttpRequestMessage<String> request,
            ExecutionContext context) {
        Message<String> message = MessageBuilder.withPayload(request.getBody())
                .copyHeaders(request.getHeaders()).build();
        return request
                .createResponseBuilder(HttpStatus.OK)
                .body(handleRequest(message, context))
                .build();
    }
}