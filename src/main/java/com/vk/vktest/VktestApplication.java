package com.vk.vktest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class VktestApplication {

    public static void main(String[] args) {
        SpringApplication.run(VktestApplication.class, args);
    }



}
