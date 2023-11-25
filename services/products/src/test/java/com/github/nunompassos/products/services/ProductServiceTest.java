package com.github.nunompassos.products.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.github.nunompassos.products.entities.Product;
import com.github.nunompassos.products.integrator.dto.ProductRequestDto;
import com.github.nunompassos.products.integrator.dto.ProductDto;
import com.github.nunompassos.products.repositories.ProductRepository;

import jakarta.persistence.EntityNotFoundException;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @InjectMocks private ProductService underTest;

    @Mock private ProductRepository productRepository;

    @Test
    public void createProduct_happyPath() {
        final ProductRequestDto requestDto = new ProductRequestDto(
            "product-entry-name",
            ProductDto.DishType.ENTRY,
            100
        );

        when(productRepository.save(any(Product.class))).thenAnswer(invocation -> invocation.getArgument(0));

        final ProductDto result = underTest.createProduct(requestDto);

        assertEquals(requestDto.name(), result.name(), "Resulting name should be " + requestDto.name());
        assertEquals(requestDto.dishType(), result.dishType(), "Resulting dish type should be " + requestDto.dishType());
        assertEquals(requestDto.calories(), result.calories(), "Resulting calories should be " + requestDto.calories());

        verify(productRepository, times(1)).save(any(Product.class));
    }

    @Test
    public void updateProduct_happyPath() {
        final ProductRequestDto requestDto = new ProductRequestDto(
            "product-entry-name",
            ProductDto.DishType.ENTRY,
            100
        );

        final Product outdatedProduct = new Product(
            "old-product",
            "old-product-entry-name",
            Product.DishType.BEVERAGE,
            20
        );

        when(productRepository.findById("old-product")).thenReturn(Optional.of(outdatedProduct));
        when(productRepository.save(any(Product.class))).thenAnswer(invocation -> invocation.getArgument(0));

        final ProductDto result = underTest.updateProduct("old-product", requestDto);

        assertEquals(requestDto.name(), result.name(), "Resulting name should be " + requestDto.name());
        assertEquals(requestDto.dishType(), result.dishType(), "Resulting dish type should be " + requestDto.dishType());
        assertEquals(requestDto.calories(), result.calories(), "Resulting calories should be " + requestDto.calories());

        verify(productRepository, times(1)).findById("old-product");
        verify(productRepository, times(1)).save(any(Product.class));
    }

    @Test
    public void updateProduct_productNotFound() {
        final ProductRequestDto requestDto = new ProductRequestDto(
            "product-entry-name",
            ProductDto.DishType.ENTRY,
            100
        );

        when(productRepository.findById("old-product")).thenReturn(Optional.empty());

        final Exception ex = assertThrows(EntityNotFoundException.class, () -> underTest.updateProduct("old-product", requestDto), "");

        assertEquals("old-product", ex.getMessage(), "Exception message should be the old id");
    }
}