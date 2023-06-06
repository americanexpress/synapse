package io.americanexpress.service.hello.reactive.grpc;

import net.devh.boot.grpc.server.service.GrpcService;
import reactor.core.publisher.Mono;

@GrpcService
public class HelloServiceImpl extends ReactorHelloServiceGrpc.HelloServiceImplBase {

    @Override
    public Mono<HelloResponse> sayHello(Mono<HelloRequest> request) {
        return request
                .map(name -> "Hello " + name.getName())
                .map(message -> HelloResponse.newBuilder().setMessage(message).build());
    }

}
