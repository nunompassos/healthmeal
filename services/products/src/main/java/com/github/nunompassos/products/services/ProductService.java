package com.github.nunompassos.products.services;

import org.springframework.stereotype.Service;

import com.github.nunompassos.products.entities.Product;
import com.github.nunompassos.products.integrator.dto.ProductDto;
import com.github.nunompassos.products.integrator.dto.ProductRequestDto;
import com.github.nunompassos.products.repositories.ProductRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(final ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductDto createProduct(final ProductRequestDto requestDto) {
        final Product product = Product.builder()
                .name(requestDto.name())
                .type(Product.DishType.valueOf(requestDto.dishType().name()))
                .calories(requestDto.calories())
                .build();

        final Product savedProduct = productRepository.save(product);

        return new ProductDto(
            savedProduct.getId(),
            savedProduct.getName(),
            ProductDto.DishType.valueOf(savedProduct.getType().name()),
            savedProduct.getCalories()
        );
    }

    public ProductDto updateProduct(
        final String id,
        final ProductRequestDto requestDto
    ) {
        final Product product = productRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id));

        product.setName(requestDto.name());
        product.setType(Product.DishType.valueOf(requestDto.dishType().name()));
        product.setCalories(requestDto.calories());

        final Product savedProduct = productRepository.save(product);

        return new ProductDto(
            savedProduct.getId(),
            savedProduct.getName(),
            ProductDto.DishType.valueOf(savedProduct.getType().name()),
            savedProduct.getCalories()
        );
    }

    public void deleteProduct() {

    }

    public void listProducts() {

    }

    public void getProduct() {

    }

}
