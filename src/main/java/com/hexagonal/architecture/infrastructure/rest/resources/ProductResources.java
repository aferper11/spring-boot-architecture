package com.hexagonal.architecture.infrastructure.rest.resources;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hexagonal.architecture.application.service.IProductService;
import com.hexagonal.architecture.application.service.ProductService;
import com.hexagonal.architecture.domain.dto.ProductResponse;
import com.hexagonal.architecture.infrastructure.rest.mapper.ProductMapper;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
@Slf4j
public class ProductResources {

  private final IProductService productService;
  private final ProductMapper productMapper;

  /**
   * Retrieves a list of products by their ID and brand ID with the given application date.
   *
   * @param id              The ID of the product.
   * @param brandId         The ID of the brand.
   * @param applicationDate The application date.
   * @return the {@link ResponseEntity} with status {@code 200 (Ok)} containing the product response or with
   * status {@code 404 (Not found)} if the product or price does not exist.
   */
  @GetMapping("products/{id}/brands/{brandId}")
  public ResponseEntity<ProductResponse> getProductsByIdAndBrandIdAndApplicationDate(@PathVariable Long id,
                                                                                     @PathVariable Integer brandId,
                                                                                     @RequestParam("applicationDate") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime applicationDate) {
    log.debug("REST request to get a list of products by its id and brand");
    return new ResponseEntity<>(productMapper.toResponse(productService.getProducts(id, brandId, applicationDate)), HttpStatus.OK);
  }
}
