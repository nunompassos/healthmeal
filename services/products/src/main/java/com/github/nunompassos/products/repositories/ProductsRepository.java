package com.github.nunompassos.products.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.nunompassos.products.entities.Product;

@Repository
public interface ProductsRepository extends JpaRepository<Product, String> {
}
