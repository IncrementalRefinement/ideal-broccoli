package com.example.flinksparkcompdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class FlinkSparkCompDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(FlinkSparkCompDemoApplication.class, args);
    }

}
