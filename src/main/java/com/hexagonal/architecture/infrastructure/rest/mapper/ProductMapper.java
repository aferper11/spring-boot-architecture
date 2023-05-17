package com.hexagonal.architecture.infrastructure.rest.mapper;

import org.mapstruct.Mapper;

import com.hexagonal.architecture.domain.model.Product;
import com.hexagonal.architecture.domain.dto.ProductResponse;

@Mapper(componentModel = "spring")
public interface ProductMapper {
  ProductResponse toResponse(Product product);
}
