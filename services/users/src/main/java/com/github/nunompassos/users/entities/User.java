package com.github.nunompassos.users.entities;

import com.github.nunompassos.users.integrator.dto.UserDto;

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
@Entity(name = "user_table")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;

    private int totalMeals;

    private int totalCalories;

    public UserDto toDto() {
        return new UserDto(
            id,
            name,
            totalMeals,
            totalCalories
        );
    }

}
