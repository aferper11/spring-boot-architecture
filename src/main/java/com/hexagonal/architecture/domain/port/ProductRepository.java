package com.hexagonal.architecture.domain.port;

import java.time.LocalDateTime;

import com.hexagonal.architecture.domain.model.Product;

public interface ProductRepository {

   Product findAllByIdAndBrandId(Long id, Integer brandId, LocalDateTime applicationDate);
}
