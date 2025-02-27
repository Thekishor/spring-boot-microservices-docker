package com.productservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ProductResponse {

    private Integer id;

    private String name;

    private String description;

    private BigDecimal price;

    private Integer categoryId;

    private String categoryName;

    private String categoryDescription;

    private Integer userId;
}
