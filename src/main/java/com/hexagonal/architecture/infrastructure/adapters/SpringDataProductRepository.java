package com.hexagonal.architecture.infrastructure.adapters;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hexagonal.architecture.infrastructure.entity.ProductEntity;

@Repository
public interface SpringDataProductRepository extends JpaRepository<ProductEntity, Long> {

    Optional<ProductEntity> findAllByIdAndPriceBrandId(@Param("id") Long id, @Param("brandId") Integer brandId);

}
