package com.hexagonal.architecture.infrastructure.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication(scanBasePackages = "com.hexagonal.architecture.infrastructure")
@EntityScan(basePackages = "com.hexagonal.architecture.domain")
public class SpringBootService {

  public static void main(String[] args) {
    SpringApplication.run(SpringBootService.class, args);
  }

}