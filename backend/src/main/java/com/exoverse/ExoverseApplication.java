package com.exoverse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class ExoverseApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExoverseApplication.class, args);
    }
}
