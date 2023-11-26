package com.github.nunompassos.orders.integrator.dto;

public record OrderDto(
    String id,
    String userId,
    String userName,
    String entry,
    int entryCalories,
    String mainCourse,
    int mainCourseCalories,
    String beverage,
    int beverageCalories,
    int totalCalories
) {
    public OrderDto {
        totalCalories = entryCalories + mainCourseCalories + beverageCalories;
    }

    public OrderDto(
        String id,
        String userId,
        String userName,
        String entry,
        int entryCalories,
        String mainCourse,
        int mainCourseCalories,
        String beverage,
        int beverageCalories
    ) {
        this(
            id,
            userId,
            userName,
            entry,
            entryCalories,
            mainCourse,
            mainCourseCalories,
            beverage,
            beverageCalories,
            entryCalories + mainCourseCalories + beverageCalories
        );
    }
}
