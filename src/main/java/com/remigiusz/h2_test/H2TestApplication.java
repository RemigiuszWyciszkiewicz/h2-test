package com.remigiusz.h2_test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.remigiusz.h2_test")
public class H2TestApplication {

    public static void main(String[] args) {
        SpringApplication.run(H2TestApplication.class, args);
    }

}
