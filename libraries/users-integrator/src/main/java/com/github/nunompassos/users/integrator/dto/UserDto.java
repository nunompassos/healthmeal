package com.github.nunompassos.users.integrator.dto;

public record UserDto(
    String id,
    String name,
    int totalMeals,
    int totalCalories
) {
}
