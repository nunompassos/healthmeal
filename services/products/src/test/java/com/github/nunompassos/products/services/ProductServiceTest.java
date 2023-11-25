package com.github.nunompassos.products.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.github.nunompassos.products.entities.Product;
import com.github.nunompassos.products.integrator.dto.CreateProductRequestDto;
import com.github.nunompassos.products.integrator.dto.ProductDto;
import com.github.nunompassos.products.repositories.ProductRepository;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @InjectMocks private ProductService underTest;

    @Mock private ProductRepository productRepository;

    @Test
    public void createProduct_happyPath() {
        final CreateProductRequestDto requestDto = new CreateProductRequestDto(
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
}
