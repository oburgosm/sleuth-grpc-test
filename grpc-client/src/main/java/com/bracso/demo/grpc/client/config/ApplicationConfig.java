package com.bracso.demo.grpc.client.config;

import org.springframework.cloud.sleuth.instrument.grpc.SpringAwareManagedChannelBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.grpc.ManagedChannel;
import com.bracso.demo.grpc.generated.ReactorGreeterGrpc;


@Configuration
public class ApplicationConfig {
    
    private final SpringAwareManagedChannelBuilder springAwareManagedChannelBuilder;

    public ApplicationConfig(SpringAwareManagedChannelBuilder springAwareManagedChannelBuilder) {
        this.springAwareManagedChannelBuilder = springAwareManagedChannelBuilder;
    }

    @Bean
    public ReactorGreeterGrpc.ReactorGreeterStub productServiceGrpcClient() {
        ManagedChannel channel = this.springAwareManagedChannelBuilder.forAddress("localhost", 6565).usePlaintext().build();
        return ReactorGreeterGrpc.newReactorStub(channel);
    }

}
