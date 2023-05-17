package com.hexagonal.architecture.infrastructure.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "brand")
public class BrandEntity {

    @Id
    @GeneratedValue
    private Integer id;
    @Column(updatable = false)
    private String name;
    @Column(updatable = false)
    @NotNull
    private String code;
}
