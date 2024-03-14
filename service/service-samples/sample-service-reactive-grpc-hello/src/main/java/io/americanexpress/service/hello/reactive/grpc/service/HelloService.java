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

package io.americanexpress.service.hello.reactive.grpc.service;

import io.americanexpress.service.hello.reactive.grpc.HelloRequest;
import io.americanexpress.service.hello.reactive.grpc.HelloResponse;
import io.americanexpress.service.hello.reactive.grpc.ReactorHelloServiceGrpc;
import net.devh.boot.grpc.server.service.GrpcService;
import reactor.core.publisher.Mono;

@GrpcService
public class HelloService extends ReactorHelloServiceGrpc.HelloServiceImplBase {

    @Override
    public Mono<HelloResponse> sayHello(Mono<HelloRequest> request) {
        return request
                .map(name -> "Hello " + name.getName())
                .map(message -> HelloResponse.newBuilder().setMessage(message).build());
    }

}
