package com.github.nunompassos.products.integrator.dto;

public record CreateProductRequestDto(String name, DishType dishType, int calories) {
    public enum DishType {
        ENTRY,
        MAIN_COURSE,
        BEVERAGE
    }
}
