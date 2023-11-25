package com.github.nunompassos.products.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import java.util.List;
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

        final Exception ex = assertThrows(
            EntityNotFoundException.class,
            () -> underTest.updateProduct("old-product", requestDto),
            "Exception should be EntityNotFound"
        );

        assertEquals("old-product", ex.getMessage(), "Exception message should be the old id");
    }

    @Test
    public void deleteProduct_happyPath() {
        final Product product = new Product(
            "old-product",
            "old-product-entry-name",
            Product.DishType.BEVERAGE,
            20
        );

        when(productRepository.findById("old-product")).thenReturn(Optional.of(product));

        final ProductDto result = underTest.deleteProduct("old-product");

        assertEquals(product.getName(), result.name(), "Resulting name should be " + product.getName());
        assertEquals(product.getType(), Product.DishType.valueOf(result.dishType().name()), "Resulting dish type should be " + product.getType());
        assertEquals(product.getCalories(), result.calories(), "Resulting calories should be " + product.getCalories());
        verify(productRepository, times(1)).findById("old-product");
        verify(productRepository, times(1)).delete(product);
    }

    @Test
    public void deleteProduct_productNotFound() {
        when(productRepository.findById("old-product")).thenReturn(Optional.empty());

        final Exception ex = assertThrows(
            EntityNotFoundException.class,
            () -> underTest.deleteProduct("old-product"),
            "Exception should be EntityNotFound"
        );

        assertEquals("old-product", ex.getMessage(), "Exception message should be the old id");
        verify(productRepository, never()).delete(any(Product.class));
    }

    @Test
    public void getProduct_happyPath() {
        final Product product = new Product(
            "old-product",
            "old-product-entry-name",
            Product.DishType.BEVERAGE,
            20
        );

        when(productRepository.findById("old-product")).thenReturn(Optional.of(product));

        final ProductDto result = underTest.getProduct("old-product");

        assertEquals(product.getName(), result.name(), "Resulting name should be " + product.getName());
        assertEquals(product.getType(), Product.DishType.valueOf(result.dishType().name()), "Resulting dish type should be " + product.getType());
        assertEquals(product.getCalories(), result.calories(), "Resulting calories should be " + product.getCalories());
        verify(productRepository, times(1)).findById("old-product");
    }

    @Test
    public void getProduct_productNotFound() {
        when(productRepository.findById("old-product")).thenReturn(Optional.empty());

        final Exception ex = assertThrows(
            EntityNotFoundException.class,
            () -> underTest.getProduct("old-product"),
            "Exception should be EntityNotFound"
        );

        assertEquals("old-product", ex.getMessage(), "Exception message should be the old id");
    }

    @Test
    public void listProducts_happyPath() {
        final Product product = new Product(
            "old-product",
            "old-product-entry-name",
            Product.DishType.BEVERAGE,
            20
        );

        when(productRepository.findAll()).thenReturn(List.of(product));

        final List<ProductDto> result = underTest.listProducts();

        assertEquals(1, result.size(), "Resulting list should have 1 item");
        assertEquals(product.getName(), result.get(0).name(), "Resulting name should be " + product.getName());
        assertEquals(product.getType(), Product.DishType.valueOf(result.get(0).dishType().name()), "Resulting dish type should be " + product.getType());
        assertEquals(product.getCalories(), result.get(0).calories(), "Resulting calories should be " + product.getCalories());
        verify(productRepository, times(1)).findAll();
    }

    @Test
    public void listProducts_noProducts() {
        when(productRepository.findAll()).thenReturn(List.of());

        final List<ProductDto> result = underTest.listProducts();

        assertEquals(0, result.size(), "Resulting list should have 0 item");
        verify(productRepository, times(1)).findAll();
    }

}
