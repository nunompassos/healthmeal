package com.github.nunompassos.products.controllers;

import java.util.List;
import java.util.Objects;

import org.springframework.web.bind.annotation.*;

import com.github.nunompassos.products.entities.Product;
import com.github.nunompassos.products.integrator.dto.ProductDto;
import com.github.nunompassos.products.integrator.dto.ProductRequestDto;
import com.github.nunompassos.products.services.ProductService;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService service;

    public ProductController(
        final ProductService service
    ) {
        this.service = service;
    }

    @PostMapping
    public @ResponseBody ProductDto createProduct(
        @RequestBody final ProductRequestDto requestDto
    ) {
        return service.createProduct(requestDto);
    }

    @PutMapping("/{id}")
    public @ResponseBody ProductDto updateProduct(
        @PathVariable final String id,
        @RequestBody final ProductRequestDto requestDto
    ) {
        return service.updateProduct(id, requestDto);
    }

    @DeleteMapping("/{id}")
    public @ResponseBody ProductDto deleteProduct(
        @PathVariable final String id
    ) {
        return service.deleteProduct(id);
    }

    @GetMapping
    public @ResponseBody List<ProductDto> listProducts(
        @RequestParam(required = false) final String name,
        @RequestParam(required = false) final Product.DishType type
    ) {
        return Objects.isNull(name) ? service.listProducts() : List.of(service.getProductByNameAndType(name, type));
    }

    @GetMapping("/{id}")
    public @ResponseBody ProductDto getProduct(
        @PathVariable final String id
    ) {
        return service.getProduct(id);
    }

}
