package com.gmail.kharchenko55.vlad;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class AutoRiaApplication {
    public static void main(String[] args) {
        SpringApplication.run(AutoRiaApplication.class, args);
    }
}
