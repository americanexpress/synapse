/*
 * Copyright 2020 American Express Travel Related Services Company, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package io.americanexpress.function.book.handler;

import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.HttpMethod;
import com.microsoft.azure.functions.HttpRequestMessage;
import com.microsoft.azure.functions.HttpResponseMessage;
import com.microsoft.azure.functions.HttpStatus;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;
import io.americanexpress.function.book.model.CreateBookRequest;
import io.americanexpress.function.book.model.CreateBookResponse;
import org.springframework.cloud.function.adapter.azure.FunctionInvoker;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

/**
 * {@code CreateBookHandler} is the entry point for the create function.
 */
public class CreateBookHandler extends FunctionInvoker<Message<CreateBookRequest>, CreateBookResponse> {

    /**
     * Executes create function upon request.
     *
     * @param request the request
     * @param context the context
     * @return the http response message
     */
    @FunctionName("create")
    public HttpResponseMessage execute(
            @HttpTrigger(name = "request", methods = {HttpMethod.GET, HttpMethod.POST}, authLevel = AuthorizationLevel.ANONYMOUS) HttpRequestMessage<CreateBookRequest> request,
            ExecutionContext context) {
        Message<CreateBookRequest> message = MessageBuilder.withPayload(request.getBody())
                .copyHeaders(request.getHeaders()).build();
        return request
                .createResponseBuilder(HttpStatus.OK)
                .body(handleRequest(message, context))
                .build();
    }

}
