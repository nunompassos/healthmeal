package com.github.nunompassos.products.entities;

import com.github.nunompassos.products.integrator.dto.ProductDto;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;

    @Enumerated(EnumType.STRING)
    private DishType type;

    private int calories;

    public enum DishType {
        ENTRY,

        MAIN_COURSE,

        BEVERAGE
    }

    public ProductDto toDto() {
        return new ProductDto(
            id,
            name,
            ProductDto.DishType.valueOf(type.name()),
            calories
        );
    }

}
