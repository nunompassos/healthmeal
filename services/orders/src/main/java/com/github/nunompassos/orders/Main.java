package com.github.nunompassos.orders;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.github.nunompassos")
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
