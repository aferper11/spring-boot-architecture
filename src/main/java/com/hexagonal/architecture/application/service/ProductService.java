package com.hexagonal.architecture.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

import com.hexagonal.architecture.domain.port.ProductRepository;
import com.hexagonal.architecture.domain.model.Product;

@RequiredArgsConstructor
@Slf4j
public class ProductService implements IProductService {

  private final ProductRepository productRepository;

  public Product getProducts(Long id, Integer brandId, LocalDateTime applicationDate) {
    log.debug("Fetching products with id {}, brandId {}, and applicationDate {}", id, brandId, applicationDate);
    return productRepository.findAllByIdAndBrandId(id, brandId, applicationDate);
  }

}
