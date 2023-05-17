package com.hexagonal.architecture.infrastructure.mapper;

import org.mapstruct.Mapper;

import com.hexagonal.architecture.domain.model.Product;
import com.hexagonal.architecture.infrastructure.entity.ProductEntity;

@Mapper(componentModel = "spring")
public interface ProductEntityMapper {

  Product toDomain(ProductEntity productEntity);

}
