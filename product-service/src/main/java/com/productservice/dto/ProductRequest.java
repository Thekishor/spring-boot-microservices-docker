package com.productservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ProductRequest {

    @NotBlank(message = "Product name cannot be null")
    @Size(min = 2, max = 100, message = "Product name must be between 2 and 100 characters")
    private String name;

    @NotBlank(message = "Product description cannot be null")
    @Size(min = 5, max = 100, message = "Product description must be between 5 and 100 characters")
    private String description;

    @Positive(message = "Available quantity must be greater than 0")
    private BigDecimal price;

    @Positive(message = "Category Id must be positive")
    private Integer categoryId;

}