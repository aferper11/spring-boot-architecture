package com.hexagonal.architecture.infrastructure.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hexagonal.architecture.domain.port.ProductRepository;
import com.hexagonal.architecture.application.service.ProductService;

@Configuration
public class SpringBootServiceConfig {

  @Bean
  public ProductService priceService(ProductRepository productRepository) {
    return new ProductService(productRepository);
  }
}