package com.hexagona.architecture.infrastructure.adapters;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.hexagonal.architecture.domain.model.Brand;
import com.hexagonal.architecture.domain.model.Price;
import com.hexagonal.architecture.domain.model.Product;
import com.hexagonal.architecture.infrastructure.adapters.ProductDboRepository;
import com.hexagonal.architecture.infrastructure.adapters.SpringDataProductRepository;
import com.hexagonal.architecture.infrastructure.entity.ProductEntity;
import com.hexagonal.architecture.infrastructure.mapper.ProductEntityMapper;

@ExtendWith(MockitoExtension.class)
class ProductDboRepositoryTest {

    @InjectMocks
    private ProductDboRepository productDboRepository;

    @Mock
    private SpringDataProductRepository productRepository;

    @Mock
    private ProductEntityMapper productMapper;

    @BeforeEach
    public void setUp() {
    }

    @Test
    void testFindAllByIdAndBrandId() {
        Long id = 1L;
        Integer brandId = 2;
        LocalDateTime applicationDate = LocalDateTime.of(2020, 7, 14, 10, 0 ,0);
        ProductEntity productEntity = new ProductEntity();
        Product product = new Product();
        List<Price> prices = new ArrayList<>();
        Brand brand = new Brand(1, "ZA", "ZARA");
        prices.add(new Price(1L, LocalDateTime.of(2020, 6, 14, 10, 0 ,0), LocalDateTime.of(2020, 8, 14, 10, 0 ,0), 1, 35455, 0.0, "EUR", brand));
        product.setPrice(prices);

        when(productRepository.findAllByIdAndPriceBrandId(id, brandId)).thenReturn(Optional.of(productEntity));
        when(productMapper.toDomain(productEntity)).thenReturn(product);

        Product result = productDboRepository.findAllByIdAndBrandId(id, brandId, applicationDate);

        assertEquals(product, result);
        verify(productRepository).findAllByIdAndPriceBrandId(id, brandId);
        verify(productMapper).toDomain(productEntity);
    }
}
