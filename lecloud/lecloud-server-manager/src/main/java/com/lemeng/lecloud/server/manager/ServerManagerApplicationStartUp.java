package com.lemeng.lecloud.server.manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;


@SpringBootApplication
@EnableEurekaServer
public class ServerManagerApplicationStartUp {

    public static void main(String[] args) {
        SpringApplication.run(ServerManagerApplicationStartUp.class, args);
    }

}
