package com.apprh.apprh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.server.servlet.context.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan // Detecta o AuthFilter
public class ApprhApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApprhApplication.class, args);
    }
}
