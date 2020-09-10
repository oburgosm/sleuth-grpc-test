package com.bracso.demo.grpc.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bracso.demo.grpc.generated.HelloRequest;
import com.bracso.demo.grpc.generated.HelloResponse;
import com.bracso.demo.grpc.generated.ReactorGreeterGrpc;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@RestController
public class MyController {


    @Autowired
    private ReactorGreeterGrpc.ReactorGreeterStub greeterService;

    @RequestMapping(value = "/hello/{name}")
    public Mono<String> hello(@PathVariable("name") String name) {
        final HelloRequest request = HelloRequest.newBuilder()
                .setName(name)
                .build();

        return this.greeterService.sayHello(request)
                // NOTE The problem only occur if we call subscribeOn
                
//                .map(HelloResponse::getMessage);
                .map(HelloResponse::getMessage)
                .subscribeOn(Schedulers.boundedElastic());
    }

}
