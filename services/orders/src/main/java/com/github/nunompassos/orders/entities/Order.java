package com.github.nunompassos.orders.entities;

import com.github.nunompassos.orders.integrator.dto.OrderDto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "order_table")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String userId;

    private String userName;

    private String entry;

    private int entryCalories;

    private String mainCourse;

    private int mainCourseCalories;

    private String beverage;

    private int beverageCalories;

    public OrderDto toDto() {
        return new OrderDto(
            id,
            userId,
            userName,
            entry,
            entryCalories,
            mainCourse,
            mainCourseCalories,
            beverage,
            beverageCalories
        );
    }
}
