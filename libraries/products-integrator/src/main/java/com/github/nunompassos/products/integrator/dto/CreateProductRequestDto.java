package com.github.nunompassos.products.integrator.dto;

import static com.github.nunompassos.products.integrator.dto.ProductDto.DishType;

public record CreateProductRequestDto(String name, DishType dishType, int calories) {
}
