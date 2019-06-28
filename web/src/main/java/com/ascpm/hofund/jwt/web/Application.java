package com.ascpm.hofund.jwt.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
        "com.ascpm.hofund.jwt.comm",
        "com.ascpm.hofund.jwt.data",
        "com.ascpm.hofund.jwt.client",
        "com.ascpm.hofund.jwt.business",
        "com.ascpm.hofund.jwt.web",
})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(com.ascpm.hofund.jwt.data.Application.class, args);
    }
}
