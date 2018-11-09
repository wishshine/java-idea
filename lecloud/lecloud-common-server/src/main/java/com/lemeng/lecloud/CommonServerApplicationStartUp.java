package com.lemeng.lecloud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@SpringBootApplication
@MapperScan("com.lemeng.lecloud.common.*.dao")
@EnableEurekaClient
public class CommonServerApplicationStartUp {

    public static void main(String[] args) {
        SpringApplication.run(CommonServerApplicationStartUp.class, args);
    }
}
