package com.productservice.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CategoryRequest {

    @NotNull(message = "Name Should not be blank")
    @Size(min = 2, max = 50, message = "Name should be between 2 and 50 characters")
    private String name;

    @NotNull(message = "Description Should not be blank")
    @Size(min = 5, max = 100, message = "Description should ne between 5 and 100 characters")
    private String description;
}
