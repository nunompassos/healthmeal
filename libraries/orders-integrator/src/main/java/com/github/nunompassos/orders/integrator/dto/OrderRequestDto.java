package com.github.nunompassos.orders.integrator.dto;

public record OrderRequestDto(
    String userName,
    String entry,
    String mainCourse,
    String beverage
) {
}
