package com.github.nunompassos.products.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.github.nunompassos.products.integrator.dto.ProductDto;
import com.github.nunompassos.products.integrator.dto.ProductRequestDto;
import com.github.nunompassos.products.services.ProductService;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(
        final ProductService productService
    ) {
        this.productService = productService;
    }

    @PostMapping
    public ProductDto createProduct(
        @RequestBody final ProductRequestDto requestDto
    ) {
        return productService.createProduct(requestDto);
    }

    @PutMapping("/{id}")
    public ProductDto updateProduct(
        @PathVariable final String id,
        @RequestBody final ProductRequestDto requestDto
    ) {
        return productService.updateProduct(id, requestDto);
    }

    @DeleteMapping("/{id}")
    public ProductDto deleteProduct(
        @PathVariable final String id
    ) {
        return productService.deleteProduct(id);
    }

    @GetMapping
    public List<ProductDto> listProducts() {
        return productService.listProducts();
    }

    @GetMapping("/{id}")
    public ProductDto getProduct(
        @PathVariable final String id
    ) {
        return productService.getProduct(id);
    }

}
