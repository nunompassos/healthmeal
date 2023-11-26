package com.github.nunompassos.products.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.nunompassos.products.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
    Optional<Product> findByNameAndType(
        final String name,
        final Product.DishType type
    );
}
