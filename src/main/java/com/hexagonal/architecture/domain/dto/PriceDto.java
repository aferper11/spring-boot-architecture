package com.hexagonal.architecture.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PriceDto {

  private Long id;
  private LocalDateTime startDate;
  private LocalDateTime endDate;
  private Integer priceList;
  private Double productPrice;
  private String curr;
  private BrandDto brand;
}
