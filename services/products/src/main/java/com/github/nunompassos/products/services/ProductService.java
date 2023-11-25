package com.github.nunompassos.products.services;

import org.springframework.stereotype.Service;

import com.github.nunompassos.products.entities.Product;
import com.github.nunompassos.products.integrator.dto.CreateProductRequestDto;
import com.github.nunompassos.products.integrator.dto.ProductDto;
import com.github.nunompassos.products.repositories.ProductRepository;

@Service
public class ProductService {

    private final ProductRepository productsRepository;

    public ProductService(final ProductRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    public ProductDto createProduct(final CreateProductRequestDto requestDto) {
        final Product product = Product.builder()
                .name(requestDto.name())
                .type(Product.DishType.valueOf(requestDto.dishType().name()))
                .calories(requestDto.calories())
                .build();

        final Product savedProduct = productsRepository.save(product);

        return new ProductDto(
            savedProduct.getId(),
            savedProduct.getName(),
            ProductDto.DishType.valueOf(savedProduct.getType().name()),
            savedProduct.getCalories()
        );
    }

    public void updateProduct() {

    }

    public void deleteProduct() {

    }

    public void listProducts() {

    }

    public void getProduct() {

    }

}
