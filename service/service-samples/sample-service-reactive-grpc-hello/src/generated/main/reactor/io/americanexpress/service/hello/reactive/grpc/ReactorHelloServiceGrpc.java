package io.americanexpress.service.hello.reactive.grpc;

import static io.americanexpress.service.hello.reactive.grpc.HelloServiceGrpc.getServiceDescriptor;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;


@javax.annotation.Generated(
value = "by ReactorGrpc generator",
comments = "Source: helloworld.proto")
public final class ReactorHelloServiceGrpc {
    private ReactorHelloServiceGrpc() {}

    public static ReactorHelloServiceStub newReactorStub(io.grpc.Channel channel) {
        return new ReactorHelloServiceStub(channel);
    }

    public static final class ReactorHelloServiceStub extends io.grpc.stub.AbstractStub<ReactorHelloServiceStub> {
        private HelloServiceGrpc.HelloServiceStub delegateStub;

        private ReactorHelloServiceStub(io.grpc.Channel channel) {
            super(channel);
            delegateStub = HelloServiceGrpc.newStub(channel);
        }

        private ReactorHelloServiceStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            super(channel, callOptions);
            delegateStub = HelloServiceGrpc.newStub(channel).build(channel, callOptions);
        }

        @Override
        protected ReactorHelloServiceStub build(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            return new ReactorHelloServiceStub(channel, callOptions);
        }

        public reactor.core.publisher.Mono<io.americanexpress.service.hello.reactive.grpc.HelloResponse> sayHello(reactor.core.publisher.Mono<io.americanexpress.service.hello.reactive.grpc.HelloRequest> reactorRequest) {
            return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(reactorRequest, delegateStub::sayHello);
        }

        public reactor.core.publisher.Mono<io.americanexpress.service.hello.reactive.grpc.HelloResponse> sayHello(io.americanexpress.service.hello.reactive.grpc.HelloRequest reactorRequest) {
           return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(reactor.core.publisher.Mono.just(reactorRequest), delegateStub::sayHello);
        }

    }

    public static abstract class HelloServiceImplBase implements io.grpc.BindableService {

        public reactor.core.publisher.Mono<io.americanexpress.service.hello.reactive.grpc.HelloResponse> sayHello(reactor.core.publisher.Mono<io.americanexpress.service.hello.reactive.grpc.HelloRequest> request) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }

        @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
            return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
                    .addMethod(
                            io.americanexpress.service.hello.reactive.grpc.HelloServiceGrpc.getSayHelloMethod(),
                            asyncUnaryCall(
                                    new MethodHandlers<
                                            io.americanexpress.service.hello.reactive.grpc.HelloRequest,
                                            io.americanexpress.service.hello.reactive.grpc.HelloResponse>(
                                            this, METHODID_SAY_HELLO)))
                    .build();
        }
    }

    private static final int METHODID_SAY_HELLO = 0;

    private static final class MethodHandlers<Req, Resp> implements
            io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
            io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
            io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
            io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
        private final HelloServiceImplBase serviceImpl;
        private final int methodId;

        MethodHandlers(HelloServiceImplBase serviceImpl, int methodId) {
            this.serviceImpl = serviceImpl;
            this.methodId = methodId;
        }

        @java.lang.Override
        @java.lang.SuppressWarnings("unchecked")
        public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
            switch (methodId) {
                case METHODID_SAY_HELLO:
                    com.salesforce.reactorgrpc.stub.ServerCalls.oneToOne((io.americanexpress.service.hello.reactive.grpc.HelloRequest) request,
                            (io.grpc.stub.StreamObserver<io.americanexpress.service.hello.reactive.grpc.HelloResponse>) responseObserver,
                            serviceImpl::sayHello);
                    break;
                default:
                    throw new java.lang.AssertionError();
            }
        }

        @java.lang.Override
        @java.lang.SuppressWarnings("unchecked")
        public io.grpc.stub.StreamObserver<Req> invoke(io.grpc.stub.StreamObserver<Resp> responseObserver) {
            switch (methodId) {
                default:
                    throw new java.lang.AssertionError();
            }
        }
    }

}
