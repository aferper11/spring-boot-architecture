package com.hexagonal.architecture.infrastructure.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Digits;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "price")
public class PriceEntity {

  @Id
  @GeneratedValue
  private Long id;

  @Column(name = "start_date")
  private LocalDateTime startDate;

  @Column(name = "end_date")
  private LocalDateTime endDate;

  @Column(name = "price_list")
  private Integer priceList;

  private Integer priority;

  @Column(name = "price")
  @Digits(integer = 2, fraction = 2, message = "The price must have a maximum of 5 integer digits and 2 decimal places")
  private Double productPrice;
  private String curr;

  @ManyToOne
  private BrandEntity brand;

  @ManyToOne
  private ProductEntity product;

}
