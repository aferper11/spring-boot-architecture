package com.hexagonal.architecture.infrastructure.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "product")
public class ProductEntity {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private Integer size;

    @OneToMany(mappedBy = "product", fetch = FetchType.EAGER)
    private List<PriceEntity> price = new ArrayList<>();
}
