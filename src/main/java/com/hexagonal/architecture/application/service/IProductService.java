package com.hexagonal.architecture.application.service;

import java.time.LocalDateTime;

import com.hexagonal.architecture.domain.model.Product;

public interface IProductService {

    Product getProducts(Long id, Integer brandId, LocalDateTime applicationDate);
}
