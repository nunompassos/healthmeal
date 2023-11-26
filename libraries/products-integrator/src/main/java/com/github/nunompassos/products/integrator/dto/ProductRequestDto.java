package com.github.nunompassos.products.integrator.dto;

import static com.github.nunompassos.products.integrator.dto.ProductDto.DishType;

import java.util.Objects;

public record ProductRequestDto(String name, DishType dishType, int calories) {
    public ProductRequestDto {
        if (Objects.isNull(name) || name.isBlank()) {
            throw new IllegalArgumentException("name cannot be empty");
        }
        Objects.requireNonNull(dishType);
    }
}
