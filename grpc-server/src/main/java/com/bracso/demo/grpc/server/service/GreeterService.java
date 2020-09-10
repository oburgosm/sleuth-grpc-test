package com.bracso.demo.grpc.server.service;


import com.bracso.demo.grpc.generated.HelloRequest;
import com.bracso.demo.grpc.generated.HelloResponse;
import com.bracso.demo.grpc.generated.ReactorGreeterGrpc;
import org.lognet.springboot.grpc.GRpcService;
import reactor.core.publisher.Mono;



@GRpcService
public class GreeterService extends ReactorGreeterGrpc.GreeterImplBase {



    @Override
    public Mono<HelloResponse> sayHello(final Mono<HelloRequest> request) {
        return request.map(req -> HelloResponse.newBuilder().setMessage("Hello reactive " + req.getName()).build());
    }


}
