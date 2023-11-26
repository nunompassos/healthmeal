package com.github.nunompassos.users.integrator.dto;

import java.util.Objects;

public record UserRequestDto(String name) {
    public UserRequestDto {
        if (Objects.isNull(name) || name.isBlank()) {
            throw new IllegalArgumentException("name is required");
        }
    }
}
