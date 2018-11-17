package com.endava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.contract.stubrunner.server.EnableStubRunnerServer;

@SpringBootApplication
@EnableStubRunnerServer
public class StubrunnerApplication {

    public static void main(String[] args) {
        SpringApplication.run(StubrunnerApplication.class, args);
    }
}
