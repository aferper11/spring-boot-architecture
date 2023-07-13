package com.hexagonal.architecture.infrastructure.adapters;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.hexagonal.architecture.domain.port.ProductRepository;
import com.hexagonal.architecture.domain.model.Price;
import com.hexagonal.architecture.domain.model.Product;
import com.hexagonal.architecture.infrastructure.entity.ProductEntity;
import com.hexagonal.architecture.infrastructure.mapper.ProductEntityMapper;

@RequiredArgsConstructor
@Service
@Slf4j
public class ProductDboRepository implements ProductRepository {

  private final SpringDataProductRepository productRepository;

  private final ProductEntityMapper productMapper;

  @Override
  public Product findAllByIdAndBrandId(Long id, Integer brandId, LocalDateTime applicationDate) {
    log.debug("Executing findAllByIdAndBrandIdAndDateApplicationBetweenStartAndEnd method. Parameters: id={}, brandId={}", id, brandId);

    ProductEntity productEntities = productRepository.findAllByIdAndPriceBrandId(id, brandId).orElseThrow(NoSuchElementException::new);
    Product product = productMapper.toDomain(productEntities);

    List<Price> filteredPrices = product.getPrice().stream().filter(price -> applicationDate.isAfter(price.getStartDate()) && applicationDate.isBefore(price.getEndDate())).collect(Collectors.toList());
    if (filteredPrices.isEmpty()) {
      throw new NoSuchElementException();
    }

    product.setPrice(filteredPrices);

    log.debug("Found a product for id={}, name={}", product.getId(), product.getName());
    return product;
  }
}
