package com.github.nunompassos.products.integrator.dto;

import static com.github.nunompassos.products.integrator.dto.ProductDto.DishType;

public record ProductRequestDto(String name, DishType dishType, int calories) {
}
