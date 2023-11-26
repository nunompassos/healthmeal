package com.github.nunompassos.products.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.github.nunompassos.products.entities.Product;
import com.github.nunompassos.products.integrator.dto.ProductDto;
import com.github.nunompassos.products.integrator.dto.ProductRequestDto;
import com.github.nunompassos.products.repositories.ProductRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProductService {

    private final ProductRepository repository;

    public ProductService(final ProductRepository repository) {
        this.repository = repository;
    }

    public ProductDto createProduct(final ProductRequestDto requestDto) {
        final Product product = Product.builder()
                .name(requestDto.name())
                .type(Product.DishType.valueOf(requestDto.dishType().name()))
                .calories(requestDto.calories())
                .build();

        return repository.save(product).toDto();
    }

    public ProductDto updateProduct(
        final String id,
        final ProductRequestDto requestDto
    ) {
        final Product product = repository.findById(id).orElseThrow(() -> new EntityNotFoundException(id));

        product.setName(requestDto.name());
        product.setType(Product.DishType.valueOf(requestDto.dishType().name()));
        product.setCalories(requestDto.calories());

        return repository.save(product).toDto();
    }

    public ProductDto deleteProduct(
        final String id
    ) {
        final Product product = repository.findById(id).orElseThrow(() -> new EntityNotFoundException(id));

        repository.delete(product);

        return product.toDto();
    }

    public List<ProductDto> listProducts() {
        return repository
            .findAll()
            .stream()
            .map(Product::toDto)
            .collect(Collectors.toList());
    }

    public ProductDto getProduct(
        final String id
    ) {
        return repository
            .findById(id)
            .orElseThrow(() -> new EntityNotFoundException(id))
            .toDto();
    }

    public ProductDto getProductByNameAndType(
        final String name,
        final Product.DishType type
    ) {
        return repository
            .findByNameAndType(name, type)
            .orElseThrow(() -> new EntityNotFoundException(name))
            .toDto();
    }

}
