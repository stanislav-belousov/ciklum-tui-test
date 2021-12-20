package com.example.ciklumtuitest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class CiklumTuiTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(CiklumTuiTestApplication.class, args);
    }

}
